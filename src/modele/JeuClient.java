package modele;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe JeuClient
 * 
 * Affichage du jeu c�t� client
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class JeuClient extends Jeu {

	private Connection connection;
	
	/**
	 * Constructeur
	 * @param controle Instance du controleur
	 */
	public JeuClient( Controle controle ) {
		super.controle = controle;
	}
	
	@Override
	public void setConnection( Connection connection ) {
		this.connection = connection;
	}

	@Override
	public void reception( Connection connection, Object info ) {
		if ( info instanceof JPanel ) {
			controle.evenementModele( this, "ajout panel murs", info );
		} else if ( info instanceof Label ) {
			controle.evenementModele( this,  "ajout joueur", info );
		} else if ( info instanceof String ) {
			controle.evenementModele( this,  "maj tchat", info );
		} else if ( info instanceof Integer ) {
			controle.evenementModele( this, "jouer son", info );
		}
	}

	@Override
	public void deconnection( Connection connection ) {
		System.exit(0);		
	}
	
	/**
	 * Envoi des infos au serveur
	 * @param info Informations � envoyer
	 */
	public void envoi( Object info ) {
		super.envoi( connection, info );
	}

}
