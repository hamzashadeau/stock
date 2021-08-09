package com.example.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.CommandeItem;

@Repository
public interface CommandeItemRepository extends JpaRepository<CommandeItem, Long>{

	public int  deleteByCommandeReference(String reference);
	public List<CommandeItem>  findByCommandeReference(String reference);

}
