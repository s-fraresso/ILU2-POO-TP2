package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	
	private ControlAcheterProduit controlAcheterProduit;
	private Village village;
	private Chef abraracourcix;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 5, village);
		village.setChef(abraracourcix);
		
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		
		Gaulois asterix = new Gaulois("Asterix", 5);
		Druide panoramix = new Druide("Panoramix", 3, 5, 10);
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(panoramix);
		village.ajouterHabitant(bonemine);
		
		village.installerVendeur(asterix, "fleurs", 10);
		village.installerVendeur(panoramix, "potions", 5);
		village.installerVendeur(bonemine, "fleurs", 15);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(controlAcheterProduit, "Constructeur ne renvoie pas null");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlAcheterProduit.verifierIdentite("Abraracourcix"), "reconnait chef");
		assertTrue(controlAcheterProduit.verifierIdentite("Asterix"), "reconnait villageois gaulois");
		assertTrue(controlAcheterProduit.verifierIdentite("Panoramix"), "reconnait villageois druide");
		assertFalse(controlAcheterProduit.verifierIdentite("Assurancetourix"), "ne reconnait pas etranger");
	}

	@Test
	void testRechercherVendeursProduit() {		
		String[] vendeursFleurs = new String[] {"Asterix", "Bonemine"};
		assertArrayEquals(vendeursFleurs, controlAcheterProduit.rechercherVendeursProduit("fleurs"), "trouve vendeurs multiples");
		
		String[] vendeursPotions = new String[] {"Panoramix"};
		assertArrayEquals(vendeursPotions, controlAcheterProduit.rechercherVendeursProduit("potions"), "trouve vendeur unique");
		
		String[] vendeursBoucliers = new String[0];
		assertArrayEquals(vendeursBoucliers, controlAcheterProduit.rechercherVendeursProduit("boucliers"), "ne trouve pas produit inexistant");
	}

	@Test
	void testAcheterProduit() {
		assertEquals(5, controlAcheterProduit.acheterProduit("Asterix", 5), "achete bonne quantite de produit si disponible");
		assertEquals(5, controlAcheterProduit.acheterProduit("Asterix", 6), "achete quantite de produit max si stock indisponible");
		assertEquals(0, controlAcheterProduit.acheterProduit("Asterix", 5), "achete zero produit si stock epuise");
	}

}
