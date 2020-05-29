package controleur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Label;
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
		this.connection = connection;
		if ( this.leJeu instanceof JeuServeur ) {
			this.leJeu.setConnection( connection );
		}
	}
	
	/**
	 * R�ception des informations depuis le serveur
	 * @param connection Objet connection
	 * @param info       Informations transmises depuis le serveur
	 */
	public void receptionInfo( Connection connection, Object info ) {
		leJeu.reception( connection, info );
	}
	
	// EVENEMENTS MODELE
	/**
	 * Gestion des �v�nements en provenance du mod�le
	 * @param unJeu JeuClient ou JeuServeur
	 * @param ordre Cha�ne d'information pr�cisant la demande � traiter
	 * @param info  Objet � traiter
	 */
	public void evenementModele( Object unJeu, String ordre, Object info ) {
		if ( unJeu instanceof JeuServeur ) {
			this.evenementJeuServeur( ordre, info );
		} else if ( unJeu instanceof JeuClient ) {
			this.evenementJeuClient( ordre, info );
		}
	}
	
	/**
	 * Gestion des �v�nements c�t� Serveur
	 */
	private void evenementJeuServeur( String ordre, Object info ) {
		if ( ordre.equals( "ajout mur" ) ) {
			this.frmArene.ajoutMur( ( JLabel ) info);
		} else if ( ordre.equals( "envoi panel murs" ) ) {
			( (JeuServeur) this.leJeu ).envoi( ( Connection )info, frmArene.getJpnMurs() );
		} else if ( ordre.equals( "ajout joueur" ) ) {
			this.frmArene.ajoutJoueur( (JLabel) info );
		} else if ( ordre.equals( "ajout tchat" ) ) {
			this.frmArene.ajoutTchat( (String) info );
			( ( JeuServeur ) leJeu ).envoi( this.frmArene.getTxtTchat() );
		}
	}
	
	/**
	 * Gestion des �v�nements c�t� Client
	 */
	private void evenementJeuClient( String ordre, Object info ) {
		if ( ordre.equals( "ajout panel murs" ) ) {
			this.frmArene.ajoutPanelMurs( ( JPanel ) info );
		} else if ( ordre.equals( "ajout joueur" ) ) {
			this.frmArene.modifJoueur( ( (Label) info ).getNumLabel(), ( (Label) info ).getjLabel() );
		} else if ( ordre.equals( "maj tchat" ) ) {
			this.frmArene.remplaceTchat( (String) info );
		} else if ( ordre.equals( "jouer son" ) ) {
			this.frmArene.jouerSon( ( Integer ) info );
		}
	}
	
	
	// EVENEMENTS VUE
	/**
	 * Gestion des �v�nements en provenance de la vue
	 * 
	 * @param  uneFrame Frame � l'origine de l'�v�nement
	 * @param  info     Information � traiter
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		if ( uneFrame instanceof EntreeJeu ) {
			this.evenementEntreeJeu( info );
		}
		else if ( uneFrame instanceof ChoixJoueur ) {
			this.evenementChoixJoueur( info );
		} else if ( uneFrame instanceof Arene ) {
			this.evenementArene( info );
		}
	}
	
	/**
	 * D�marre un jeu serveur ou client, selon le bouton cliqu� par l'utilisateur
	 * 
	 * @param info Information � traiter
	 */
	private void evenementEntreeJeu(Object info) {
		if ( ( ( String ) info ).equals( "serveur" ) ) {
			new ServeurSocket( this, PORT );
			this.leJeu    = new JeuServeur( this );
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene( "serveur", this );
			( (JeuServeur) leJeu ).constructionMurs();
			this.frmArene.setVisible( true );
		} else {
			if ( ( new ClientSocket( ( String ) info, PORT, this ) ).isConnexionOK() ) {
				this.leJeu          = new JeuClient( this );
				this.leJeu.setConnection( connection );
				this.frmArene       = new Arene( "client", this );
				this.frmChoixJoueur = new ChoixJoueur( this );
				this.frmEntreeJeu.dispose();
				this.frmChoixJoueur.setVisible( true );
			}
		}
	}
	
	/**
	 * R�ception d'informations en provenance de la frame ChoixJoueur
	 * Retourne les informations au serveur, 
	 * efface la frame choixJoueur et affiche l'Ar�ne
	 * @param info Joueur et pseudo choisis par le client
	 */
	private void evenementChoixJoueur( Object info ) {
		( ( JeuClient ) this.leJeu ).envoi( info );
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible( true );
	}
	
	/**
	 * R�ception d'informations en provenance de l'Ar�ne
	 * @param info Le message saisi par le client dans la zone de tchat
	 */
	private void evenementArene( Object info ) {
		( ( JeuClient) this.leJeu ).envoi( info );
	}
	
	
	/**
	 * D�connexion d'un joueur
	 * @param connection
	 */
	public void deconnection( Connection connection ) {
		this.leJeu.deconnection( connection );
	}
}
