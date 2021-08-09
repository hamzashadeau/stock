package com.example.stock.ws.provided;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("v1/stock-api/user")
public class UserRest {
	@Autowired
	private UserService userService;

	public List<com.example.stock.bean.User> findAll() {
		return userService.findAll();
	}

	public com.example.stock.bean.User findByUsername(String username) {
		return userService.findByUsername(username);
	}

	public int deleteByUsername(String username) {
		return userService.deleteByUsername(username);
	}

	public com.example.stock.bean.User findById(Long id) {
		return userService.findById(id);
	}

	public void deleteById(Long id) {
		userService.deleteById(id);
	}
	@GetMapping("/save")
	public com.example.stock.bean.User save() {
		return userService.save(new User("hamza@gmail.com","hamzaShadeau"));
	}

	public List<com.example.stock.bean.User> create(List<com.example.stock.bean.User> users) {
		return userService.create(users);
	}

	public com.example.stock.bean.User update(com.example.stock.bean.User user) {
		return userService.update(user);
	}

	public int delete(com.example.stock.bean.User user) {
		return userService.delete(user);
	}

}
