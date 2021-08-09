package com.example.stock.ws.provided;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.service.facade.RoleService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("v1/stock-api/role")
public class RoleRest {
@Autowired
private RoleService roleService;

public List<com.example.stock.bean.Role> findAll() {
	return roleService.findAll();
}

public com.example.stock.bean.Role findByAuthority(String authority) {
	return roleService.findByAuthority(authority);
}

public com.example.stock.bean.Role findById(Long id) {
	return roleService.findById(id);
}

public void deleteById(Long id) {
	roleService.deleteById(id);
}

public List<com.example.stock.bean.Role> findByUpdatedByUsername(String username) {
	return roleService.findByUpdatedByUsername(username);
}

public int deleteByUpdatedByUsername(String username) {
	return roleService.deleteByUpdatedByUsername(username);
}

public List<com.example.stock.bean.Role> findByUpdatedById(Long id) {
	return roleService.findByUpdatedById(id);
}

public int deleteByUpdatedById(Long id) {
	return roleService.deleteByUpdatedById(id);
}

public List<com.example.stock.bean.Role> findByCreatedByUsername(String username) {
	return roleService.findByCreatedByUsername(username);
}

public int deleteByCreatedByUsername(String username) {
	return roleService.deleteByCreatedByUsername(username);
}

public List<com.example.stock.bean.Role> findByCreatedById(Long id) {
	return roleService.findByCreatedById(id);
}

public int deleteByCreatedById(Long id) {
	return roleService.deleteByCreatedById(id);
}

public com.example.stock.bean.Role save(com.example.stock.bean.Role role) {
	return roleService.save(role);
}

public List<com.example.stock.bean.Role> create(List<com.example.stock.bean.Role> roles) {
	return roleService.create(roles);
}

public com.example.stock.bean.Role update(com.example.stock.bean.Role role) {
	return roleService.update(role);
}

public int delete(com.example.stock.bean.Role role) {
	return roleService.delete(role);
}

public int deleteByAuthority(String authority) {
	return roleService.deleteByAuthority(authority);
}
}
