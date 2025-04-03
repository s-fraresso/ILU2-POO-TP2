package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 2);
		village.setChef(new Chef("Abraracourcix", 5, village));
		controlVerifierIdentite = new ControlVerifierIdentite(village);
	}

	@Test
	void testControlVerifierIdentite() {
		assertNotNull(controlVerifierIdentite, "Constructeur ne renvoie pas null");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlVerifierIdentite.verifierIdentite("Abraracourcix"), "reconnait chef");
				
		Gaulois asterix = new Gaulois("Asterix", 5);
		village.ajouterHabitant(asterix);
		assertTrue(controlVerifierIdentite.verifierIdentite("Asterix"), "reconnait villageois gaulois");
		
		Druide panoramix = new Druide("Panoramix", 3, 5, 10);
		village.ajouterHabitant(panoramix);
		assertTrue(controlVerifierIdentite.verifierIdentite("Panoramix"), "reconnait villageois druide");
		
		assertFalse(controlVerifierIdentite.verifierIdentite("Bonemine"), "ne reconnait pas etranger");
	}
}
