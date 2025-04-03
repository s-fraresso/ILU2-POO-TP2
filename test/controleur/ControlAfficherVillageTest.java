package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {

	private ControlAfficherVillage controlAfficherVillage;
	private Village village;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlAfficherVillage = new ControlAfficherVillage(village);
	}
	
	@Test
	void testControlAfficherVillage() {
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		String[] nomsVillageois1 = new String[] {"Abraracourcix"};
		assertArrayEquals(nomsVillageois1, controlAfficherVillage.donnerNomsVillageois(), "Village vide donne le nom du chef");
		
		Gaulois asterix = new Gaulois("Asterix", 5);
		Gaulois obelix = new Gaulois("Obelix", 10);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		String[] nomsVillageois2 = new String[] {"Abraracourcix", "Asterix", "Obelix"};
		assertArrayEquals(nomsVillageois2, controlAfficherVillage.donnerNomsVillageois(), "Village avec gaulois donne chef et gaulois");
		
		Druide panoramix = new Druide("Panoramix", 3, 3, 5);
		village.ajouterHabitant(panoramix);
		String[] nomsVillageois3 = new String[] {"Abraracourcix", "Asterix", "Obelix", "le druide Panoramix"};
		assertArrayEquals(nomsVillageois3, controlAfficherVillage.donnerNomsVillageois(), "Village avec gaulois et druide donne chef et gaulois et druide");
	}

	@Test
	void testDonnerNomVillage() {
		assertEquals("Le village des irreductibles", controlAfficherVillage.donnerNomVillage(), "donne nom village");
	}

	@Test
	void testDonnerNbEtals() {
		assertEquals(5, controlAfficherVillage.donnerNbEtals(), "donne nombre etals");
	}

}
