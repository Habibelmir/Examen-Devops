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

	
	public void Addproduit(Produit p) throws ProduitExist, InfoInvalide
	{
		if(ProduitExist(p.getId(),p.getNom()))
		{
				throw new ProduitExist("Ce produit exist deja");
		}
		ValiderInfo(p.getPrix(),p.getQuantite());
		listProduit.add(p);
	}
	
	public boolean ProduitExist(long id,String nom) 
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
	
	public void ValiderInfo(double prix,int qt) throws InfoInvalide
	{
		if(prix <= 0 || qt <= 0) 
		{
			throw new InfoInvalide("Information non valider vous devez entrer des donnée positifs");
		}
	}

}
