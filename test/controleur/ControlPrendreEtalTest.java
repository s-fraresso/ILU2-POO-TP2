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
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irreductibles", 10, 2);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Gaulois asterix = new Gaulois("Asterix", 5);
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}
	
	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		assertTrue(controlPrendreEtal.resteEtals(), "reste etals marche vide");
		controlPrendreEtal.prendreEtal("Asterix", "fleurs", 5);
		assertTrue(controlPrendreEtal.resteEtals(), "reste etals marche non vide");
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 5);
		assertFalse(controlPrendreEtal.resteEtals(), "ne reste pas d etals marche plein");
	}

	@Test
	void testPrendreEtal() {
		assertEquals(0, controlPrendreEtal.prendreEtal("Asterix", "fleurs", 10), "installe premier vendeur sur etal 0");
		assertEquals(1, controlPrendreEtal.prendreEtal("Abraracourcix", "boucliers", 15), "installe deuxieme vendeur sur etal 1");
		assertEquals(-1, controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10), "n installe pas vendeur quand marche plein");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite("Abraracourcix"), "reconnait chef");
		assertTrue(controlPrendreEtal.verifierIdentite("Asterix"), "reconnait villageois gaulois");
		
		Druide panoramix = new Druide("Panoramix", 3, 5, 10);
		village.ajouterHabitant(panoramix);
		assertTrue(controlPrendreEtal.verifierIdentite("Panoramix"), "reconnait villageois druide");
		
		assertFalse(controlPrendreEtal.verifierIdentite("Assurancetourix"), "ne reconnait pas etranger");
	}

}
