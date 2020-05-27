package controleur;

import javax.swing.JFrame;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
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
public class Controle implements Global {

	private EntreeJeu   frmEntreeJeu;
	private Jeu         leJeu;
	private Arene       frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Connection  connection;
	
	/**
	 * Lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	public Controle() {
		this.frmEntreeJeu = new EntreeJeu( this );
		this.frmEntreeJeu.setVisible( true );
	}
	

	/**
	 * R�cup�ration de la connexion
	 * @param connection Objet Connection
	 */
	public void setConnection( Connection connection ) {
		System.out.println( "Controleur setConnection" );
		this.connection = connection;
	}
	
	/**
	 * R�ception des informations depuis le serveur
	 * @param connection Objet connection
	 * @param info       Informations transmises depuis le serveur
	 */
	public void receptionInfo( Connection connection, Object info ) {
		System.out.println( "Controleur receptionInfo" );
		leJeu.reception( connection, info );
	}
	
	// EVENEMENTS MODELE

	
	// EVENEMENTS VUE
	/**
	 * Gestion des �v�nements en provenance de la vue
	 * 
	 * @param  uneFrame Frame � l'origine de l'�v�nement
	 * @param  info     Information � traiter
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		System.out.println( "Evenement en provenance de la vue" );
		if ( uneFrame instanceof EntreeJeu ) {
			this.evenementEntreeJeu( info );
		}
		else if ( uneFrame instanceof ChoixJoueur ) {
			this.evenementChoixJoueur( info );
		}
	}
	/**
	 * D�marre un jeu serveur ou client, selon le bouton cliqu� par l'utilisateur
	 * 
	 * @param info Information � traiter
	 */
	public void evenementEntreeJeu(Object info) {
		System.out.println( "Entr�e jeu : " + ( String ) info );
		if ( ( ( String ) info ).equals( "serveur" ) ) {
			new ServeurSocket( this, PORT );
			this.leJeu    = new JeuServeur( this );
			this.frmArene = new Arene();
			this.frmEntreeJeu.dispose();
			this.frmArene.setVisible( true );
			
		} else {
			if ( ( new ClientSocket( ( String ) info, PORT, this ) ).isConnexionOK() ) {
				System.out.println( "Entr�e jeu client : " + ( String ) info );
				this.leJeu          = new JeuClient( this );
				this.leJeu.setConnection( connection );
				this.frmArene       = new Arene();
				this.frmChoixJoueur = new ChoixJoueur( this );
				this.frmEntreeJeu.dispose();
				this.frmChoixJoueur.setVisible( true );
			} else {
				System.out.println( "Entr�e jeu client connexion pas ok : " + ( String ) info );
			}
		}
	}
	
	/**
	 * Enregistre le joueur s�lectionn� et affiche l'Arene au client
	 * 
	 * @param info Information � traiter
	 */
	public void evenementChoixJoueur( Object info ) {
		System.out.println( "Evenement choix joueur : " + ( String ) info );
		( ( JeuClient ) this.leJeu ).envoi( info );
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible( true );
	}
	
}
