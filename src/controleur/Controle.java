package controleur;

import javax.swing.JFrame;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Classe Controle
 * 
 * Gère les événements provenant du modèle et de la vue
 * 
 * @project Urban Marginal
 * @package controleur
 * @version 1.0
 * @author  Virginie
 *
 */
public class Controle {

	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	
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
		if ( ((String) info).equals("serveur") ) {
			new ServeurSocket(this, 6666);
			this.leJeu    = new JeuServeur(this);
			this.frmArene = new Arene();
			this.frmEntreeJeu.dispose();
			this.frmArene.setVisible(true);
			
		} else {
			if ((new ClientSocket((String) info, 6666, this)).isConnexionOK()) {
				this.leJeu          = new JeuClient(this);
				this.frmArene       = new Arene();
				this.frmChoixJoueur = new ChoixJoueur();
				this.frmEntreeJeu.dispose();
				this.frmChoixJoueur.setVisible(true);
				//this.frmArene.setVisible(true);
			}
		}
	}
}
