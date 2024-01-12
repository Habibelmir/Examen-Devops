package com.ProjetDevops;

import java.util.ArrayList;
import java.util.List;

public class ProduitService 
{
	private List<Produit> listProduit;
	
	public ProduitService() 
	{
       	this.listProduit = new ArrayList<>();
    }

	
	public void Createproduit(Produit p) throws ProduitNoExist, InfoInvalide
	{
		if(isExist(p.getId(),p.getNom()))
		{
				throw new ProduitNoExist("Ce produit exist deja");
		}
		ValideInfo(p.getPrix(),p.getQuantite());
		listProduit.add(p);
	}
	
	public boolean isExist(long id,String nom) 
	{
		for(int i=0;i<listProduit.size();i++) 
		{
			if(id == listProduit.get(i).getId() && nom.equals(listProduit.get(i).getNom())) 
			{
				return true;
			}
		}
		return false;
	}
	
	public void ValideInfo(double prix,int qt) throws InfoInvalide
	{
		if(prix <= 0 || qt <= 0) 
		{
			throw new InfoInvalide("Information non valider vous devez entrer des donnée positifs");
		}
	}
	
	public Produit ReadProduit(long id) throws IdNotFoundException
	{
		for(Produit produit: listProduit) 
		{
			if(produit.getId() == id) 
			{
				return produit;
			}
		}
		throw new IdNotFoundException("Aucun produit avec l'id : "+id);
	}

	public void UpdateProduit(Produit p) throws ProduitNoExist, InfoInvalide
	{
		if(isExist(p.getId(),p.getNom()))
		{
				throw new ProduitNoExist("Ce produit exist deja");
		}
		
		ValideInfo(p.getPrix(),p.getQuantite());
		
		for(int i=0;i<listProduit.size();i++) 
		{
			if(p.getId() == listProduit.get(i).getId()) 
			{
				listProduit.set(i, p);
			}
		}
	}

	


}
