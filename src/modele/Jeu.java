package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe Jeu 
 * Classe M�re de JeuServeur et JeuClient
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public abstract class Jeu {
	
	protected Controle controle;
	
	/**
	 * R�ception d'une connexion
	 * @param connection Objet de connexion au serveur
	 */
	public abstract void setConnection( Connection connection );	
	
	/**
	 * Reception des messages provenant de l'ordinateur distant
	 * @param connection Emetteur du message
	 * @param info       Message envoy� par l'ordinateur distant
	 */
	public abstract void reception( Connection connection, Object info );	
	
	/**
	 * Envoi des informations �/aux ordinateur(s) distant(s)
	 * @param connection Emetteur du message
	 * @param info       Message envoy� par l'ordinateur distant
	 */
	public void envoi( Connection connection, Object info ) {
		connection.envoi( info );
	}
	
	/**
	 * Gestion de la d�connexion de l'ordinateur distant
	 * @param connection : Objet de connexion au serveur
	 */
	public abstract void deconnection( Connection connection );
}
