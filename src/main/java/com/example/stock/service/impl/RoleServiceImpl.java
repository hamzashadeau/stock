package com.example.stock.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.bean.Role;
import com.example.stock.dao.RoleRepository;
import com.example.stock.service.facade.RoleService;
import com.example.stock.service.facade.UserService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleDao;

	

	    @Autowired
	    private UserService userService;

	    @Autowired
	    private EntityManager entityManager;

	    @Override
	    public List<Role> findAll() {
	        return roleDao.findAll();
	    }

	    @Override
	    public List<Role> findByUpdatedByUsername(String username) {
	        return roleDao.findByUpdatedByUsername(username);
	    }

	    @Override
	    @Transactional
	    public int deleteByUpdatedByUsername(String username) {
	        return roleDao.deleteByUpdatedByUsername(username);
	    }

	    @Override
	    public List<Role> findByUpdatedById(Long id) {
	        return roleDao.findByUpdatedById(id);

	    }

	    @Override
	    @Transactional
	    public int deleteByUpdatedById(Long id) {
	        return roleDao.deleteByUpdatedById(id);

	    }

	    @Override
	    public List<Role> findByCreatedByUsername(String username) {
	        return roleDao.findByCreatedByUsername(username);
	    }

	    @Override
	    @Transactional
	    public int deleteByCreatedByUsername(String username) {
	        return roleDao.deleteByCreatedByUsername(username);
	    }

	    @Override
	    public List<Role> findByCreatedById(Long id) {
	        return roleDao.findByCreatedById(id);

	    }

	    @Override
	    @Transactional
	    public int deleteByCreatedById(Long id) {
	        return roleDao.deleteByCreatedById(id);

	    }

	    @Override
	    public Role findByAuthority(String authority) {
	        if (authority == null)
	            return null;
	        return roleDao.findByAuthority(authority);
	    }


	    @Override
	    @Transactional
	    public int deleteByAuthority(String authority) {
	        return roleDao.deleteByAuthority(authority);
	    }

	    @Override
	    public Role findById(Long id) {
	        if (id == null)
	            return null;
	        return roleDao.getOne(id);
	    }

	    @Transactional
	    public void deleteById(Long id) {
	        roleDao.deleteById(id);
	    }

	    @Override
	    public Role save(Role role) {
	        Role foundedRole = findByAuthority(role.getAuthority());
	        if (foundedRole != null)
	            return null;

	        Role savedRole = roleDao.save(role);
	        return savedRole;
	    }

	    @Override
	    public Role update(Role role) {
	        Role foundedRole = findById(role.getId());
	        if (foundedRole == null)
	            return null;
	   //     prepareUpdate(role);
	        return roleDao.save(role);

	    }

	    @Override
	    @Transactional
	    public int delete(Role role) {

	        if (role.getAuthority() == null)
	            return -1;

	        Role foundedRole = findByAuthority(role.getAuthority());
	        if (foundedRole == null)
	            return -1;
	        roleDao.delete(foundedRole);
	        return 1;
	    }

		@Override
		public List<Role> create(List<Role> roles) {
			List<Role> resultats = null;
			roles.forEach(r->resultats.add(save(r)));
			return resultats;
		}



	/*    public List<Role> findByCriteria(RoleVo roleVo) {
	        String query = "SELECT o FROM Role o where 1=1 ";
	        query += addConstraintDate("o", "updatedAt", "=", roleVo.getUpdatedAt());
	        query += addConstraint("o", "authority", "LIKE", roleVo.getAuthority());

	        query += addConstraintDate("o", "createdAt", "=", roleVo.getCreatedAt());
	        query += addConstraint("o", "id", "=", roleVo.getId());
	        query += addConstraintMinMaxDate("o", "updatedAt", roleVo.getUpdatedAtMin(), roleVo.getUpdatedAtMax());
	        query += addConstraintMinMaxDate("o", "createdAt", roleVo.getCreatedAtMin(), roleVo.getCreatedAtMax());
	        if (roleVo.getUpdatedByVo() != null) {
	            query += addConstraint("o", "updatedBy.id", "=", roleVo.getUpdatedByVo().getId());
	            query += addConstraint("o", "updatedBy.username", "LIKE", roleVo.getUpdatedByVo().getUsername());
	        }

	        if (roleVo.getCreatedByVo() != null) {
	            query += addConstraint("o", "createdBy.id", "=", roleVo.getCreatedByVo().getId());
	            query += addConstraint("o", "createdBy.username", "LIKE", roleVo.getCreatedByVo().getUsername());
	        }

	        return entityManager.createQuery(query).getResultList();
	    }*/

}
