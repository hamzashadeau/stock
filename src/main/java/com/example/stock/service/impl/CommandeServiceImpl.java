package com.example.stock.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.bean.Commande;
import com.example.stock.bean.CommandeItem;
import com.example.stock.dao.CommandeRepository;
import com.example.stock.service.facade.CommandeItemService;
import com.example.stock.service.facade.CommandeService;

@Service
public class CommandeServiceImpl implements CommandeService{

	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private CommandeItemService commandeItemService;

	@Override
	public Commande findByReference(String reference) {
		return commandeRepository.findByReference(reference);
	}

	@Override
	@Transactional
	public int deleteByReference(String reference) {
		int res1=commandeItemService.deleteByCommandeReference(reference);
		int res2= commandeRepository.deleteByReference(reference);
		return res1+res2;
	}

	@Override
	public int save(Commande commande) {
		Commande loadedCommande = findByReference(commande.getReference());
		if(loadedCommande!=null) {
			return -1;
		}else {
			calculerTotal(commande,commande.getCommandeItems());
			commandeRepository.save(commande);
			commandeItemService.save(commande, commande.getCommandeItems());
			return 1;
		}
		
	}

	private void calculerTotal(Commande commande, List<CommandeItem> commandeItems) {
		double total=0;
		for (CommandeItem commandeItem : commandeItems) {
			total+=commandeItem.getPrix()*commandeItem.getQte();
		}
		commande.setTotal(total);
		
	}

	@Override
	public List<Commande> findAll() {
		return commandeRepository.findAll();
	}
}
