package com.ProjetDevops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ProduitServiceTest 
{
	private ProduitService produitService;

    @Before
    public void Instansiation() 
    {
        produitService = new ProduitService();
    }
    
    @Test
    public void testCreateProduit() throws ProduitNoExist, InfoInvalide 
    {
        Produit Produit = new Produit(1L, "Nike", 1000.0, 5);
        produitService.Createproduit(Produit);
        assertTrue(produitService.isExist(1L,"Nike"));
    }
    @Test(expected = ProduitNoExist.class)
    public void testCreateProduitAvecMemeId() throws ProduitNoExist, InfoInvalide 
    {
        Produit premierProduit = new Produit(1L, "Adidas", 500.0, 10);
        Produit secondProduit = new Produit(1L, "Puma", 300.0, 8);
       
        
            produitService.Createproduit(premierProduit);
            produitService.Createproduit(secondProduit);
       
        
    }
   
    @Test(expected =ProduitNoExist.class )
    public void testCreateProduitAvecMemeNom() throws ProduitNoExist, InfoInvalide 
    {
        Produit premierProduit = new Produit(1L, "Nike", 1000.0, 5);
        Produit secondProduit = new Produit(2L, "Nike", 800.0, 7);
        
            produitService.Createproduit(premierProduit);
            produitService.Createproduit(secondProduit);
       
    }
    
    @Test(expected = InfoInvalide.class)
    public void testCreateProduitAvecDonneInvalide() 
    {
    	 Produit thirdProduit = new Produit(1L, "Supreme", -100, 5);
         Produit forthProduit = new Produit(1L, "Tomi", 1000, -24);
         assertThrows(InfoInvalide.class, () -> {
             produitService.ValideInfo(thirdProduit.getPrix(), thirdProduit.getQuantite());
             produitService.ValideInfo(forthProduit.getPrix(), forthProduit.getQuantite());
         });
    }
   
    @Test
    public void testSupprimerProduit() throws IdNotFoundException, ProduitNoExist, InfoInvalide {
        Produit produit = new Produit(1L, "Nike", 20.0, 15);
        produitService.Createproduit(produit);
        produitService.DeleteProduit(produit);
        assertFalse(produitService.isExist(1L,"Nike"));
    }
    @Test
    public void testMettreAJourProduitInvalid() throws ProduitNoExist, InfoInvalide, IdNotFoundException 
    {
        Produit produitInitial = new Produit(1L, "Adidas", 200.0, 3);
        Produit produitMaj = new Produit(1L, "Supreme", -250.0, 5);
        produitService.Createproduit(produitInitial);
        assertThrows(InfoInvalide.class, () -> {
        	produitService.UpdateProduit(produitMaj);
            
        });
    }
    @Test
    public void testSupprimerProduitInexistant() {
    	Produit p = new Produit(1L, "Nike", 20.0, 15);
        assertThrows(IdNotFoundException.class, () -> produitService.DeleteProduit(p));
    }
    
    
    @Test
    public void testMettreAJourProduitIdnotFound() throws ProduitNoExist, InfoInvalide, IdNotFoundException 
    {
        Produit produitInitial = new Produit(1L, "Adidas", 200.0, 3);
        Produit produitMaj = new Produit(2L, "Supreme", -250.0, 5);
        produitService.Createproduit(produitInitial);
        assertThrows(InfoInvalide.class, () -> {
        	produitService.UpdateProduit(produitMaj);
            
        });
    }
    @Test
    public void testSupprimerProduitIdnotFound() throws ProduitNoExist, InfoInvalide, IdNotFoundException 
    {
        Produit produitInitial = new Produit(1L, "Adidas", 200.0, 3);
        Produit produitMaj = new Produit(2L, "Supreme", -250.0, 5);
        produitService.Createproduit(produitInitial);
        
        assertThrows(IdNotFoundException.class, () -> 
        	produitService.DeleteProduit(produitMaj)
            
        );
    }
    @Test
    public void testReadProduitExistant() throws ProduitNoExist, InfoInvalide, IdNotFoundException {

        Produit produit = new Produit(1L, "chemise", 25.0, 3);
        produitService.Createproduit(produit);

        Produit produitR = produitService.ReadProduit(1L);
        assertEquals("chemise", produitR.getNom());
    }
    @Test
    public void testValiderDonneesProduit() {
        Produit produitNegatif = new Produit(1L, "adiddas", -30.0, -2);
        assertThrows(InfoInvalide.class, () -> produitService.ValideInfo(produitNegatif.getPrix(),produitNegatif.getQuantite()));
    }
    @Test
    public void testMettreAJourProduit() throws ProduitNoExist, InfoInvalide, IdNotFoundException 
    {
        Produit produitInitial = new Produit(1L, "Adidas", 200.0, 3);
        Produit produitMaj = new Produit(1L, "Supreme", 250.0, 5);
        produitService.Createproduit(produitInitial);
        produitService.UpdateProduit(produitMaj);
        assertEquals("Supreme", produitService.ReadProduit(1L).getNom());
    }


   
   
    
}
