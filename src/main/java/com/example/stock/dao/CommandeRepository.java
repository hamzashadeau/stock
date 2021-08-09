package com.example.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

	public Commande  findByReference(String reference);
	public int  deleteByReference(String reference);

}
