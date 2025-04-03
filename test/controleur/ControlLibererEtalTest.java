package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlLibererEtalTest {
	
	private ControlLibererEtal controlLibererEtal;
	private Village village;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 2);
		village.setChef(new Chef("Abraracourcix", 5, village));
		controlLibererEtal = new ControlLibererEtal(new ControlTrouverEtalVendeur(village));
	}


	@Test
	void testControlLibererEtal() {
		assertNotNull(controlLibererEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testIsVendeur() {
		assertFalse(controlLibererEtal.isVendeur("Asterix"), "gaulois inexistant");
		
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(bonemine);
		assertFalse(controlLibererEtal.isVendeur("Bonemine"), "habitant du village non vendeur");
		
		village.installerVendeur(bonemine, "fleurs", 5);
		assertTrue(controlLibererEtal.isVendeur("Bonemine"), "vendeur installe");
		
		village.partirVendeur(bonemine);
		assertFalse(controlLibererEtal.isVendeur("Bonemine"), "vendeur parti");
	}

	@Test
	void testLibererEtal() {
		assertNull(controlLibererEtal.libererEtal("Asterix"), "gaulois inexistant");
		
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(bonemine);
		assertNull(controlLibererEtal.libererEtal("Bonemine"), "habitant non vendeur");
		
		village.installerVendeur(bonemine, "fleurs", 5);
		String[] infosCible = new String[] {"true", "Bonemine", "fleurs", "5", "0"};
		assertArrayEquals(infosCible, controlLibererEtal.libererEtal("Bonemine"), "0 vente");
		
		assertNull(controlLibererEtal.libererEtal("Bonemine"), "vendeur parti");
		
		village.installerVendeur(bonemine, "fleurs", 5);
		Etal etalBonemine = village.rechercherEtal(bonemine);
		etalBonemine.acheterProduit(3);
		infosCible = new String[] {"true", "Bonemine", "fleurs", "5", "3"};
		assertArrayEquals(infosCible, controlLibererEtal.libererEtal("Bonemine"), "1 vente");
	}

}
