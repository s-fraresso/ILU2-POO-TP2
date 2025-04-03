package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	
	private Village village;
	private ControlAfficherMarche controlAfficherMarche;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 2);
		Chef abraracourcix = new Chef("Abraracourcix", 5, village);
		village.setChef(abraracourcix);
		controlAfficherMarche = new ControlAfficherMarche(village);
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerInfosMarche() {
		assertArrayEquals(new String[0], controlAfficherMarche.donnerInfosMarche(), "infos marche vide");
		
		Gaulois asterix = new Gaulois("Asterix", 5);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, "fleurs", 5);
		String[] infosCible = new String[] {"Asterix", "5", "fleurs"};
		assertArrayEquals(infosCible, controlAfficherMarche.donnerInfosMarche(), "un etal occupe");
		
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "menhirs", 10);
		infosCible = new String[] {"Asterix", "5", "fleurs", "Bonemine", "10", "menhirs"};
		assertArrayEquals(infosCible, controlAfficherMarche.donnerInfosMarche(), "marche plein");
	}

}
