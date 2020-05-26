package controleur;

import javax.swing.JFrame;

import vue.EntreeJeu;

/**
 * Classe Controle
 * 
 * G�re les �v�nements provenant du mod�le et de la vue
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
	 * Affecte � la propri�t� frmEntreeJeu une instance de la classe EntreeJeu qui permet d'ouvrir la frame de s�lection du serveur
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
	 * Gestion des �v�nements en provenance de la vue
	 * 
	 * @param JFrame uneFrame Frame � l'origine de l'�v�nement
	 * @param Object info     Information � traiter
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		System.out.println("Evenement en provenance de la vue");
		if (uneFrame instanceof EntreeJeu ) {
			this.evenementEntreeJeu(info);
		}
	}
	/**
	 * D�marre un jeu serveur ou client, selon le bouton cliqu� par l'utilisateur
	 * 
	 * @param Object info Information � traiter
	 */
	public void evenementEntreeJeu(Object info) {
		System.out.println("Entr�e jeu : " + (String) info );
	}
}
