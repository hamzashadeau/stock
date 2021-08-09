package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Commande;
import com.example.stock.bean.CommandeItem;

public interface CommandeItemService {

	public int  deleteByCommandeReference(String reference);
	public List<CommandeItem>  findByCommandeReference(String reference);
	public int save(Commande commande,List<CommandeItem> commandeItems);

}
