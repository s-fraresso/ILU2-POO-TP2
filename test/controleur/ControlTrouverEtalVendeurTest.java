package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {

	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private Village village;
	private Chef abraracourcix;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("Le village des irreductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 5, village);
		village.setChef(abraracourcix);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
	}
	
	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(controlTrouverEtalVendeur, "Constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverEtalVendeur() {
		Gaulois asterix = new Gaulois("Asterix", 5);
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("Asterix"), "ne trouve pas etal etranger");
		
		village.ajouterHabitant(asterix);
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("Asterix"), "ne trouve pas etal habitant non vendeur");
		
		village.installerVendeur(asterix, "fleurs", 10);
		assertNotNull(controlTrouverEtalVendeur.trouverEtalVendeur("Asterix"), "trouve etal gaulois vendeur");
		
		village.installerVendeur(abraracourcix, "boucliers", 5);
		assertNotNull(controlTrouverEtalVendeur.trouverEtalVendeur("Abraracourcix"), "trouve etal chef vendeur");
		
		assertNotEquals(controlTrouverEtalVendeur.trouverEtalVendeur("Asterix"), controlTrouverEtalVendeur.trouverEtalVendeur("Abraracourcix"), "vendeurs differents trouve etals differents");
		
		village.partirVendeur(asterix);
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("Asterix"), "ne trouve pas vendeur parti");
	}

}
