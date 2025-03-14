package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean nomAcheteurConnu = controlAcheterProduit.verifierIdentite(nomAcheteur);
		if (!nomAcheteurConnu) {
			System.out.println("Je suis d�sol� " + nomAcheteur + " mais il faut �tre un habitant de notre village pour commercer ici.");
			return;
		}
		
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] nomVendeursPotentiels = controlAcheterProduit.rechercherVendeursProduit(produit);
		if (nomVendeursPotentiels.length == 0) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}
		
		StringBuilder question = new StringBuilder("Chez quel commerçant voulez-vous acheter des fleurs ?\n");
		for (int i = 0; i < nomVendeursPotentiels.length; i++) {
			question.append((i + 1) + " - " + nomVendeursPotentiels[i] + "\n");
		}
		
		int choixVendeur = -1;
		do {
			choixVendeur = Clavier.entrerEntier(question.toString());
			if (choixVendeur < 1 || choixVendeur > nomVendeursPotentiels.length) {
				System.out.println("Veuillez choisir un des vendeurs proposés.");
			}			
		} while (choixVendeur < 1 || choixVendeur > nomVendeursPotentiels.length);
		
		String nomVendeur = nomVendeursPotentiels[choixVendeur - 1];
		
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur + ".");
		System.out.println("Bonjour " + nomAcheteur + ".");
		int nbProduit = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int nbProduitVendu = controlAcheterProduit.acheterProduit(nomVendeur, nbProduit);
		
		if (nbProduitVendu == 0) {
			System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit + ", malheureusement il n’y en a plus !");
		}
		else if (nbProduitVendu == nbProduit) {
			System.out.println(nomAcheteur + " achète " + nbProduit + " " + produit + " à " + nomVendeur + ".");
		}
		else {
			System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit + ", malheureusement " + nomVendeur +
					" n’en a plus que " + nbProduitVendu +". "+ nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
		}

	}
}
