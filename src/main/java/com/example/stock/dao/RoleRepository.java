package com.example.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{


	Role findByAuthority(String authority);
       int deleteByAuthority(String authority);

       List<Role> findByUpdatedByUsername(String username);
       int deleteByUpdatedByUsername(String username);       
       List<Role> findByUpdatedById(Long id);
       int deleteByUpdatedById(Long id);
       List<Role> findByCreatedByUsername(String username);
       int deleteByCreatedByUsername(String username);       
       List<Role> findByCreatedById(Long id);
       int deleteByCreatedById(Long id);
}
