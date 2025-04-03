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
	private Gaulois asterix;
	private Chef abraracourcix;
	private ControlPrendreEtal controlPrendreEtal;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irreductibles", 10, 2);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		asterix = new Gaulois("Asterix", 5);
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(bonemine);
		controlPrendreEtal = new ControlPrendreEtal(new ControlVerifierIdentite(village), village);
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
		assertEquals(-1, controlPrendreEtal.prendreEtal("Assurancetourix", "menhirs", 10), "n installe pas vendeur etranger");
		assertEquals(0, controlPrendreEtal.prendreEtal("Asterix", "fleurs", 10), "installe premier vendeur sur etal 0");
		assertEquals(1, controlPrendreEtal.prendreEtal("Abraracourcix", "boucliers", 15), "installe deuxieme vendeur sur etal 1");
		assertEquals(-1, controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10), "n installe pas vendeur quand marche plein");
		
		village.partirVendeur(asterix);
		assertEquals(0, controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10), "installe vendeur etal liberee");
		village.partirVendeur(abraracourcix);
		assertEquals(1, controlPrendreEtal.prendreEtal("Bonemine", "menhirs", 5), "installe vendeurs 2 etals simultanees");
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
