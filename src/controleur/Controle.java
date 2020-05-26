package controleur;

import javax.swing.JFrame;

import vue.EntreeJeu;

/**
 * Classe Controle
 * 
 * Gère les événements provenant du modèle et de la vue
 * 
 * @package Urban Marginal
 * @subpackage controleur
 * @version 1.0
 * @author Virginie
 *
 */
public class Controle {

	private EntreeJeu frmEntreeJeu;
	
	/**
	 * Constructeur
	 * 
	 * Affecte à la propriété frmEntreeJeu une instance de la classe EntreeJeu qui permet d'ouvrir la frame de sélection du serveur
	 */
	public Controle() {
		super();
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}
	
	
	/**
	 * Lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	// EVENEMENTS MODELE

	
	// EVENEMENTS VUE
	/**
	 * Gestion des évènements en provenance de la vue
	 * 
	 * @param JFrame uneFrame Frame à l'origine de l'évènement
	 * @param Object info     Information à traiter
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		System.out.println("Evenement en provenance de la vue");
		if (uneFrame instanceof EntreeJeu ) {
			this.evenementEntreeJeu(info);
		}
	}
	/**
	 * Démarre un jeu serveur ou client, selon le bouton cliqué par l'utilisateur
	 * 
	 * @param Object info Information à traiter
	 */
	public void evenementEntreeJeu(Object info) {
		System.out.println("Entrée jeu : " + (String) info );
	}
}
