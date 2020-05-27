package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe JeuClient
 * 
 * Affichage du jeu côté client
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
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
		System.out.println( "Reception JeuClient : " + ( String ) info );
	}

	@Override
	public void deconnection( Connection connection ) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Envoi des infos au serveur
	 * @param info Informations à envoyer
	 */
	public void envoi( Object info ) {
		super.envoi( connection, info );
	}

}
