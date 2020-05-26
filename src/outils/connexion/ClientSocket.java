package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * Classe ClientSocket
 * 
 * Connexion à un serveur et lancement d'un thread indépendant
 * en attente des messages du serveur
 * 
 * @project Urban Marginal
 * @pacakge outils.connexion
 * @version 1.0
 * @author  Virginie
 */
public class ClientSocket {
	private boolean connexionOK;
	/**
	 * Constructeur
	 * @param String ip          Adresse Ip du serveur auquel il faut se connecter
	 * @param int    port        N° de port d'écoute du serveur
	 * @param Object leRecepteur Objet vers lequel seront transférées les informations reçues du serveur
	 */
	public ClientSocket(String ip, int port, Object leRecepteur) {
		this.setConnexionOK(false);
		try {
			Socket socket = new Socket(ip, port);
			System.out.println(" Connexion au serveur réussie");
			this.setConnexionOK(true);
			new Connection(socket, leRecepteur);
		} catch (UnknownHostException e) {
			System.out.println("*** WARNING *** Le serveur n'est pas disponible : " + e );
			JOptionPane.showMessageDialog(null, "Le serveur n'est pas disponible !");
		} catch (IOException e) {
			System.out.println("*** WARNING *** Problème au niveau de l'entrée sortie : " + e );
			JOptionPane.showMessageDialog(null, "Serveur inaccessible, vérifiez l'adresse IP");
		}
	}
	/**
	 * Getter ConnexionOK
	 * @return boolean
	 */
	public boolean isConnexionOK() {
		return connexionOK;
	}
	/**
	 * Setter ConnexionOK
	 * @param boolean connexionOK
	 */
	public void setConnexionOK(boolean connexionOK) {
		this.connexionOK = connexionOK;
	}
}
