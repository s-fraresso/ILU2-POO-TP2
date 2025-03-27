package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	
	private ControlAcheterProduit controlAcheterProduit;
	private Village village;
	private Chef abraracourcix;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 5, village);
		village.setChef(abraracourcix);
		controlAcheterProduit = new ControlAcheterProduit(village);
	}

	@Test
	void testControlAcheterProduit() {
		fail("Not yet implemented");
	}

	@Test
	void testVerifierIdentite() {
		fail("Not yet implemented");
	}

	@Test
	void testRechercherVendeursProduit() {
		fail("Not yet implemented");
	}

	@Test
	void testAcheterProduit() {
		fail("Not yet implemented");
	}

}
