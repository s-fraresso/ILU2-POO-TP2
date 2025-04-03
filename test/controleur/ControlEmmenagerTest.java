package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	private Village village;
	private Chef abraracourcix;
	private ControlEmmenager controlEmmenager;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlEmmenager = new ControlEmmenager(village);
	}

	@Test
	void testControlEmmenager() {
		assertNotNull(controlEmmenager, "Constructeur ne renvoie pas null");
	}

	@Test
	void testIsHabitant() {
		assertTrue(controlEmmenager.isHabitant("Abraracourcix"), "chef est habitant");
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"), "villageois gaulois est habitant");
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"), "villageois druide est habitant");
		assertFalse(controlEmmenager.isHabitant("Assurancetourix"), "etranger n est pas habitant");
	}

	@Test
	void testAjouterDruide() {
		assertFalse(controlEmmenager.isHabitant("Panoramix"));
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"), "ajout druide normal");
		assertThrows(IllegalArgumentException.class, () -> controlEmmenager.ajouterDruide("Panoramix2", -1, 1, 5), "force negative");
		assertThrows(IllegalArgumentException.class, () -> controlEmmenager.ajouterDruide("Panoramix2", 3, -2, 5), "effetPotionMin negative");
		assertThrows(IllegalArgumentException.class, () -> controlEmmenager.ajouterDruide("Panoramix2", 3, 5, 1), "min et max inverses");
	}

	@Test
	void testAjouterGaulois() {
		assertFalse(controlEmmenager.isHabitant("Bonemine"));
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"));
		assertFalse(controlEmmenager.isHabitant("PasBonemine"));
		assertThrows(IllegalArgumentException.class, () -> controlEmmenager.ajouterGaulois("Asterix", -1), "force negative");
	}

	@Test
	void testAjouterBeaucoupDeGaulois() {
		for (int i = 0; i < 100; i++) {
			controlEmmenager.ajouterGaulois("GAULOIS_" + i, 10);
		}
		for (int i = 0; i < 100; i++) {
			if (i < 10) {
				assertTrue(controlEmmenager.isHabitant("GAULOIS_" + i));
			} else {
				assertFalse(controlEmmenager.isHabitant("GAULOIS_" + i));
			}
		}
	}
}