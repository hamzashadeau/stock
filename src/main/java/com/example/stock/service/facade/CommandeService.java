package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Commande;

public interface CommandeService {

	public List<Commande>  findAll();
	public Commande  findByReference(String reference);

	public int  deleteByReference(String reference);
	public int save(Commande commande);

}
