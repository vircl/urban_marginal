package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe ServeurSocket
 * 
 * Crée un socket spécial pour un serveur, et se met à l'écoute des clients
 * qui voudraient se connecter (plusieurs joueurs peuvent accéder au serveur
 * pour entrer dans l'arène du jeu)
 * 
 * @project Urban Marginal
 * @package outils.connexion
 * @version 1.0
 * @author  Virginie
 */
public class ServeurSocket extends Thread {
	
	private Object leRecepteur;
	private ServerSocket serverSocket;

	/**
	 * Constructeur de la classe
	 * 
	 * @param Object leRecepteur objet voulant solliciter ServeurSocket
	 * @param int    port        n° de port d'écoute du socket du serveur
	 */
	public ServeurSocket(Object leRecepteur, int port) {
		super();
		this.leRecepteur  = leRecepteur;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("*** ERREUR *** Erreur à la création socket serveur : " + e );
			System.exit(0);
		}
		super.start();
	}
	/**
	  * {@inheritDoc}
	  */
	@Override
	public void run() {
		Socket socket;
		// Boucle infinie en attente de nouveaux clients
		while (true ) {
			try {
				// Mise à l'écoute de nouveaux clients
				System.out.println("Le serveur attend");
				socket = serverSocket.accept();
				System.out.println("Un client s'est connecté");
				new Connection(socket, leRecepteur);
			} catch (IOException e) {
				System.out.println("*** ERREUR *** Erreur lors de la mise en écoute de clients = " + e );
				System.exit(0);
			}
		}
	}
}
