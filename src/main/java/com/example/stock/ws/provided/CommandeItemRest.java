package com.example.stock.ws.provided;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.CommandeItem;
import com.example.stock.service.facade.CommandeItemService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("v1/stock-api/commande-item")
public class CommandeItemRest {

	@Autowired
	private CommandeItemService commandeItemService;

	@GetMapping("/commande/reference/{reference}")
	public List<CommandeItem> findByCommandeReference(@PathVariable String reference) {
		return commandeItemService.findByCommandeReference(reference);
	}

	
	
}
