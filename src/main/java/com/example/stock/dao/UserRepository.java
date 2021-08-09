package com.example.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


	User findByUsername(String username);
       int deleteByUsername(String username);

       //List<User> findByCreatedByUsername(String username);
       //int deleteByCreatedByUsername(String username);       
      // List<User> findByCreatedById(Long id);
      // int deleteByCreatedById(Long id);
     //  List<User> findByUpdatedByUsername(String username);
      // int deleteByUpdatedByUsername(String username);       
       //List<User> findByUpdatedById(Long id);
       //int deleteByUpdatedById(Long id);

       User findByEmail(String email);

     //  List<User> findByLeServiceId(Long id);


}
