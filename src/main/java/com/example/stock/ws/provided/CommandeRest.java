package com.example.stock.ws.provided;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Commande;
import com.example.stock.service.facade.CommandeService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("v1/stock-api/commande")
public class CommandeRest {

	@Autowired
	private CommandeService commandeService;

	@GetMapping("/")
	public List<Commande> findAll() {
		return commandeService.findAll();
	}

	@GetMapping("reference/{reference}")
	public Commande findByReference(@PathVariable String reference) {
		return commandeService.findByReference(reference);
	}

	@DeleteMapping("/reference/{reference}")
	public int deleteByReference(@PathVariable String reference) {
		return commandeService.deleteByReference(reference);
	}

	@PostMapping("/save")
	public int save(@RequestBody Commande commande) {
		return commandeService.save(commande);
	}

}
