package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {

	private Village village;
	private Chef abraracourcix;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irreductibles", 10, 2);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}
	
	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		fail("Not yet implemented");
	}

	@Test
	void testPrendreEtal() {
		Gaulois asterix = new Gaulois("Asterix", 5);
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		
		assertEquals(0, controlPrendreEtal.prendreEtal("Asterix", "fleurs", 10), "installe premier vendeur sur etal 0");
		assertEquals(1, controlPrendreEtal.prendreEtal("Abraracourcix", "boucliers", 15), "installe deuxieme vendeur sur etal 1");
		assertEquals(-1, controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10), "n installe pas vendeur quand marche plein");
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
