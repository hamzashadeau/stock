package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.stock.bean.Role;
import com.example.stock.bean.User;
import com.example.stock.dao.UserRepository;
import com.example.stock.service.facade.RoleService;
import com.example.stock.service.facade.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	// @Autowired
	  //  private LeServiceService leServiceService;

	    @Autowired
	    private UserRepository userDao;

	    @Autowired
	    private RoleService roleService;

	    @Autowired
	    private EntityManager entityManager;

	    @Autowired
	    BCryptPasswordEncoder bCryptPasswordEncoder;

	 
	    @Override
	    public List<User> findAll() {
	            return userDao.findAll();
	    }

	  	    @Override
	    public User findByUsername(String username) {
	        if (username == null)
	            return null;
	        return userDao.findByUsername(username);
	    }


	    @Override
	    @Transactional
	    public int deleteByUsername(String username) {
	        return userDao.deleteByUsername(username);
	    }

	    @Override
	    public User findById(Long id) {
	        if (id == null)
	            return null;
	        return userDao.getOne(id);
	    }

	    @Transactional
	    public void deleteById(Long id) {
	        userDao.deleteById(id);
	    }

	    @Override
	    public User save(User user) {

	        User foundedUserByUsername = findByUsername(user.getUsername());
	        User foundedUserByEmail = userDao.findByEmail(user.getEmail());
	        if (foundedUserByUsername != null || foundedUserByEmail != null)
	            return null;
	        else {
	            List<String> authorities = new ArrayList<>();
	            if(user.getPassword()==null || user.getPassword().isEmpty()) {
		            user.setPassword(user.getUsername());	            	
	            }
	            user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
	            user.setAccountNonExpired(true);
	            user.setAccountNonLocked(true);
	            user.setCredentialsNonExpired(true);
	            user.setEnabled(true);
	            user.setPasswordChanged(false);
	            user.setCreatedAt(new Date());
	            for (Role role : user.getAuthorities()) {
	                authorities.add(role.getAuthority());
	            }
	            user.getAuthorities().clear();
	            for (String authority : authorities) {
	                user.getAuthorities().add(roleService.findByAuthority(authority));
	            }

	          //  prepareSave(user);
	            User mySaved= userDao.save(user);

	          	            return mySaved;
	        }
	    }

	    @Override
	    public User update(User user) {
	        User foundedUser = findById(user.getId());
	        if (foundedUser == null)
	            return null;
	        else {
	            foundedUser.setEmail(user.getEmail());
	            foundedUser.setUsername(user.getUsername());
	            foundedUser.setEnabled(user.isEnabled());
	            foundedUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
	            foundedUser.setAccountNonLocked(user.isAccountNonLocked());
	            foundedUser.setAccountNonExpired(user.isAccountNonExpired());
	            foundedUser.setAuthorities(new ArrayList<>());

	            for (Role role : user.getAuthorities()) {
	                foundedUser.getAuthorities().add(roleService.findByAuthority(role.getAuthority()));
	            }

	    //        prepareUpdate(foundedUser);
	            return userDao.save(foundedUser);
	        }


	    }

	    @Override
	    @Transactional
	    public int delete(User user) {

	        if (user.getUsername() == null)
	            return -1;

	        User foundedUser = findByUsername(user.getUsername());
	        if (foundedUser == null)
	            return -1;
	        userDao.delete(foundedUser);
	        return 1;
	    }

/*
	    public List<User> findByCriteria(UserVo userVo) {
	        System.out.println(userVo.getCredentialsNonExpired());
	        String query = "SELECT o FROM User o where 1=1 ";
	        if (userVo.getCredentialsNonExpired() != null) {
	            query += addConstraint("o", "credentialsNonExpired", "=", userVo.getCredentialsNonExpired() ? 1 : 0);
	        }
	        if (userVo.getEnabled() != null) {
	            query += addConstraint("o", "enabled", "=", userVo.getEnabled() ? 1 : 0);
	        }
	        if (userVo.getAccountNonExpired() != null) {
	            query += addConstraint("o", "accountNonExpired", "=", userVo.getAccountNonExpired()? 1 : 0);
	        }
	        if (userVo.getAccountNonLocked() != null) {
	            query += addConstraint("o", "accountNonLocked", "=", userVo.getAccountNonLocked()? 1 : 0);
	        }
	        query += addConstraint("o", "username", "LIKE", userVo.getUsername());
	        query += addConstraint("o", "email", "LIKE", userVo.getEmail());
//	        query += addConstraintDate("o", "createdAt", "=", userVo.getCreatedAt());
//	        query += addConstraintDate("o", "updatedAt", "=", userVo.getUpdatedAt());

//	        query += addConstraint("o", "id", "=", userVo.getId());

//	        query += addConstraint("o", "password", "LIKE", userVo.getPassword());

//	        query += addConstraintMinMaxDate("o", "createdAt", userVo.getCreatedAtMin(), userVo.getCreatedAtMax());
//	        query += addConstraintMinMaxDate("o", "updatedAt", userVo.getUpdatedAtMin(), userVo.getUpdatedAtMax());
	        /*if (userVo.getCreatedByVo() != null) {
	            query += addConstraint("o", "createdBy.id", "=", userVo.getCreatedByVo().getId());
	            query += addConstraint("o", "createdBy.username", "LIKE", userVo.getCreatedByVo().getUsername());
	        }
	        if (userVo.getUpdatedByVo() != null) {
	            query += addConstraint("o", "updatedBy.id", "=", userVo.getUpdatedByVo().getId());
	            query += addConstraint("o", "updatedBy.username", "LIKE", userVo.getUpdatedByVo().getUsername());
	        }*/

	       // return entityManager.createQuery(query).getResultList();
	  //  }
/*
	    @Override
	    public int resetPassword(UserVo userVo) {
	        User userDB = findByUsername(userVo.getUsername());
	        String existingPassword = userVo.getPassword();
	        String dbPassword = userDB.getPassword();
	        if (userDB != null && bCryptPasswordEncoder.matches(existingPassword, dbPassword)) {
	            userDB.setPassword(bCryptPasswordEncoder.encode(userVo.getNewPassword()));
	            userDB.setPasswordChanged(true);
	            userDao.save(userDB);
	            return 1;
	        } else return -1;
	    }

	    @Override
	    public User initPassword(String username) {
	        User user = userDao.findByUsername(username);
	        if (user == null) {
	            return null;
	        } else {
	            user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
	            user.setPasswordChanged(false);
	            return userDao.save(user);
	        }
	    }*/

	

		@Override
		public List<User> create(List<User> users) {
			List<User> resultats=null;
			users.forEach(u->resultats.add(save(u)));
			return resultats;
		}

		   @Override
		    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		        User appUser=userDao.findByUsername(username);
		        if(appUser==null) throw new UsernameNotFoundException("invalid user");
		        Collection<GrantedAuthority> authorities=new ArrayList<>();
		        appUser.getAuthorities().forEach(r->{
		            authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
		        });
		        return appUser;
		    }


}
