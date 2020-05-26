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
	 * Connexion au serveur
	 * 
	 * @param Object connection Objet de connexion au serveur
	 */
	public abstract void setConnection(Connection connection);	
	
	/**
	 * Reception des messages provenant de l'ordinateur distant
	 * 
	 * @param Connection connection Emetteur du message
	 * @param Object     info       Message envoyé par l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object info);	
	
	/**
	 * Envoi des informations à/aux ordinateur(s) distant(s)
	 * 
	 * @param Connection connection Emetteur du message
	 * @param Object     info       Message envoyé par l'ordinateur distant
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	/**
	 * Gestion de la déconnexion de l'ordinateur distant
	 * 
	 * @param Connection connection : Objet de connexion au serveur
	 */
	public abstract void deconnection(Connection connection);
}
