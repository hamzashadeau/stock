package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.bean.Commande;
import com.example.stock.bean.CommandeItem;
import com.example.stock.dao.CommandeItemRepository;
import com.example.stock.service.facade.CommandeItemService;

@Service
public class CommandeItemServiceImpl implements CommandeItemService{

	@Autowired
	private CommandeItemRepository commandeItemRepository;

	@Override
	public int deleteByCommandeReference(String reference) {
		return commandeItemRepository.deleteByCommandeReference(reference);
	}

	@Override
	public List<CommandeItem> findByCommandeReference(String reference) {
		return commandeItemRepository.findByCommandeReference(reference);
	}

	@Override
	public int save(Commande commande, List<CommandeItem> commandeItems) {
		for (CommandeItem commandeItem : commandeItems) {
			commandeItem.setCommande(commande);
			commandeItemRepository.save(commandeItem);
		}
		return 1;
	}

}
