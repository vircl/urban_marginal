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
 * G�re les �v�nements provenant du mod�le et de la vue
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
