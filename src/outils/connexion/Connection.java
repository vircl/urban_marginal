package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import controleur.Controle;

/**
 * Classe Connection
 * 
 * @project Urban Marginal
 * @pacakge outils.connexion
 * @version 1.0
 * @author  Virginie
 */
public class Connection extends Thread {
	
	private Object             leRecepteur;
	private ObjectInputStream  in;
	private ObjectOutputStream out;
	
	/**
	 * Constructeur
	 * 
	 * @param socket      Socket de l'ordinateur distant
	 * @param leRecepteur Objet leRecepteur de la classe ServeurSocket ou ClientSocket
	 */
	public Connection( Socket socket, Object leRecepteur ) {
		this.leRecepteur = leRecepteur;
		// Création du canal de sortie
		try {
			this.out = new ObjectOutputStream( socket.getOutputStream() );
		} catch ( IOException e ) {
			System.out.println( "*** ERREUR *** Erreur lors de la création du canal de sortie " + e );
			System.exit(0);
		}
		// Création du canal d'entrée
		try {
			this.in = new ObjectInputStream( socket.getInputStream() );
		} catch ( IOException e ) {
			System.out.println( "*** ERREUR *** Erreur lors de la création du canal d'entrée " + e );
			System.exit(0);
		}
		super.start();
		( ( controleur.Controle ) this.leRecepteur ).setConnection(this);
	}
	/**
	 * Attente des messages de l'ordinateur distant
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		boolean inOk = true;
		Object  reception;
		while ( inOk ) {
			try {
				reception = in.readObject();
				( ( controleur.Controle ) this.leRecepteur ).receptionInfo( this, reception );
			} catch ( ClassNotFoundException e ) {
				System.out.println( "*** ERREUR *** Erreur lors de l'initialisation de la méthode readObject (classe introuvable) " + e );
				System.exit(0);
			} catch ( IOException e ) {
				System.out.println( "*** WARNING *** Ordinateur distant déconnecté " + e );
				JOptionPane.showMessageDialog( null, "L'ordinateur distant est déconnecté" );
				inOk = false;
				try {
					this.in.close();
				} catch ( IOException e1 ) {
					System.out.println( "*** WARNING *** Erreur lors de la fermeture du canal d'entrée " + e1 );
				}
			}
		}
	}
	
	/**
	 * Envoi des données au serveur
	 */
	public synchronized void envoi( Object unObjet ) {
		try {
			this.out.reset();
			this.out.writeObject( unObjet );
			this.out.flush();
		} catch ( IOException e ) {
			System.out.println( "*** WARNING *** Erreur sur l'objet out " + e );
		}
	}
}
