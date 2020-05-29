package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe ServeurSocket
 * 
 * Cr�e un socket sp�cial pour un serveur, et se met � l'�coute des clients
 * qui voudraient se connecter (plusieurs joueurs peuvent acc�der au serveur
 * pour entrer dans l'ar�ne du jeu)
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
	 * @param port        n� de port d'�coute du socket du serveur
	 */
	public ServeurSocket( Object leRecepteur, int port ) {
		super();
		this.leRecepteur  = leRecepteur;
		try {
			serverSocket = new ServerSocket(port);
		} catch ( IOException e ) {
			System.out.println( "*** ERREUR *** Erreur � la cr�ation socket serveur : " + e );
			System.exit(0);
		}
		super.start();
	}
	
	/**
	 * D�marrage du serveur
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
				System.out.println( "Un client s'est connect�" );
				new Connection( socket, leRecepteur );
			} catch ( IOException e ) {
				System.out.println( "*** ERREUR *** Erreur lors de la mise en �coute de clients = " + e );
				System.exit(0);
			}
		}
	}
}
