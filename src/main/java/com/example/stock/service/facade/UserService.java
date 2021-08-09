package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.User;

public interface UserService {


	    /**
	     * find all User in database 
	     * @return List<User> , If database is empty return  null.
	     */
		List<User> findAll();
		   
		/**
	     * find User from database by username (reference)
	     * @param username - reference of User 
	     * @return the founded User , If no User were
	     *         found in database return  null.
	     */
		User findByUsername(String username);
		public int deleteByUsername(String username);
	    /**
	     * find User from database by id (id)
	     * @param id - id of User 
	     * @return the founded  User , If no User were
	     *         found in database return  null.
	     */
		User findById(Long id);
	     
	     /**
	     * delete User from database
	     * @param id - id of User to be deleted
	     * 
	     */
		void deleteById(Long id);

	
				 
	    /**
	     * create User in database
	     * @param user - User to be created 
	     * @return the created User, If the User can't be created return null.
	     */
		User save(User user);

		/**
	     * create list User in database
	     * @param users - list of User to be created 
	     * @return the created User list
	     */
		List<User> create(List<User> users);
	    
	     /**
	     * update User in database
	     * @param user - User to be updated
	     * @return the updated User, If the User can't be updated return null.
	     */
	    User update(User user);
	    
	       /**
	     * delete User from database
	     * @param user - User to be deleted
	     * @return 1 if User deleted successfully, If the User can't be deleted return negative int
	     */
		int delete(User user);


	    
	        /**
	     * delete User from database by username (reference)
	     * 
	     * @param username - reference of User to be deleted
	     * @return 1 if User deleted successfully
	     */

	     
		/**
	     * search for User in by some criteria
	     * @return the searhed list User 
	     */
//		List<User> findByCriteria( UserVo userVo);

	   // public int resetPassword(UserVo userVo);
	 //   public User initPassword(String username);

}
