package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe Jeu 
 * 
 * Classe Mère de JeuServeur et JeuClient
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 */
public abstract class Jeu {
	
	protected Controle controle;
	
	/**
	 * Réception d'une connexion
	 * @param connection Objet de connexion au serveur
	 */
	public abstract void setConnection( Connection connection );	
	
	/**
	 * Reception des messages provenant de l'ordinateur distant
	 * @param connection Emetteur du message
	 * @param info       Message envoyé par l'ordinateur distant
	 */
	public abstract void reception( Connection connection, Object info );	
	
	/**
	 * Envoi des informations à/aux ordinateur(s) distant(s)
	 * @param connection Emetteur du message
	 * @param info       Message envoyé par l'ordinateur distant
	 */
	public void envoi( Connection connection, Object info ) {
		connection.envoi( info );
	}
	
	/**
	 * Gestion de la déconnexion de l'ordinateur distant
	 * @param connection : Objet de connexion au serveur
	 */
	public abstract void deconnection( Connection connection );
}
