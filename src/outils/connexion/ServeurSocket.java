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
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> outils.connexion </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class ServeurSocket extends Thread {
	
	private Object       leRecepteur;
	private ServerSocket serverSocket;

	/**
	 * Constructeur de la classe
	 * 
	 * @param leRecepteur objet voulant solliciter ServeurSocket
	 * @param port        n° de port d'écoute du socket du serveur
	 */
	public ServeurSocket( Object leRecepteur, int port ) {
		super();
		this.leRecepteur  = leRecepteur;
		try {
			serverSocket = new ServerSocket(port);
		} catch ( IOException e ) {
			System.out.println( "*** ERREUR *** Erreur à la création socket serveur : " + e );
			System.exit(0);
		}
		super.start();
	}
	
	/**
	 * Démarrage du serveur
	 * Boucle infinie en attente de nouveaux clients
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		Socket socket;
		while ( true ) {
			try {
				System.out.println( "Le serveur attend" );
				socket = serverSocket.accept();
				System.out.println( "Un client s'est connecté" );
				new Connection( socket, leRecepteur );
			} catch ( IOException e ) {
				System.out.println( "*** ERREUR *** Erreur lors de la mise en écoute de clients = " + e );
				System.exit(0);
			}
		}
	}
}
