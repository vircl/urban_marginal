package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * Classe ClientSocket
 * 
 * Connexion � un serveur et lancement d'un thread ind�pendant
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
	 * @param int    port        N� de port d'�coute du serveur
	 * @param Object leRecepteur Objet vers lequel seront transf�r�es les informations re�ues du serveur
	 */
	public ClientSocket(String ip, int port, Object leRecepteur) {
		this.setConnexionOK(false);
		try {
			Socket socket = new Socket(ip, port);
			System.out.println(" Connexion au serveur r�ussie");
			this.setConnexionOK(true);
			new Connection(socket, leRecepteur);
		} catch (UnknownHostException e) {
			System.out.println("*** WARNING *** Le serveur n'est pas disponible : " + e );
			JOptionPane.showMessageDialog(null, "Le serveur n'est pas disponible !");
		} catch (IOException e) {
			System.out.println("*** WARNING *** Probl�me au niveau de l'entr�e sortie : " + e );
			JOptionPane.showMessageDialog(null, "Serveur inaccessible, v�rifiez l'adresse IP");
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
