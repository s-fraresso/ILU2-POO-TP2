package controleur;

import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

public class ControlEmmenager {
	private Village village;

	public ControlEmmenager(Village village) {
		this.village = village;
	}

	public boolean isHabitant(String nom) {
		return village.trouverHabitant(nom) != null;
	}

	public void ajouterDruide(String nom, int force, int effetPotionMin,
			int effetPotionMax) {
		if (force <= 0) {
			throw new IllegalArgumentException("force doit etre positive");
		}
		if (effetPotionMin <= 0 || effetPotionMax < effetPotionMin) {
			throw new IllegalArgumentException("0 < effetPotionMin <= effetPotionMax");
		}
		Druide druide = new Druide(nom, force, effetPotionMin, effetPotionMax);
		village.ajouterHabitant(druide);

	}

	public void ajouterGaulois(String nom, int force) {
		if (force <= 0) {
			throw new IllegalArgumentException("force doit etre positive");
		}
		Gaulois gaulois = new Gaulois(nom, force);
		village.ajouterHabitant(gaulois);
	}

}
