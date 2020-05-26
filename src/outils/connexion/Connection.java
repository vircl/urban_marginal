package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Classe Connection
 * 
 * @project Urban Marginal
 * @pacakge outils.connexion
 * @version 1.0
 * @author  Virginie
 */
public class Connection extends Thread {
	
	private Object leRecepteur;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/**
	 * Constructeur
	 * 
	 * @param Socket socket Socket de l'ordinateur distant
	 * @param leRecepteur   objet leRecepteur de la classe ServeurSocket ou ClientSocket
	 */
	public Connection(Socket socket, Object leRecepteur) {
		this.leRecepteur = leRecepteur;
		// Cr�ation du canal de sortie
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("*** ERREUR *** Erreur lors de la cr�ation du canal de sortie " + e );
			System.exit(0);
		}
		// Cr�ation du canal d'entr�e
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("*** ERREUR *** Erreur lors de la cr�ation du canal d'entr�e " + e );
			System.exit(0);
		}
		super.start();
	}
	/**
	  * {@inheritDoc}
	  */
	@Override
	public void run() {
		boolean inOk = true;
		Object reception;
		while(inOk) {
			try {
				reception = in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("*** ERREUR *** Erreur lors de l'initialisation de la m�thode readObject (classe introuvable) " + e );
				System.exit(0);
			} catch (IOException e) {
				System.out.println("*** WARNING *** Ordinateur distant d�connect� " + e );
				JOptionPane.showMessageDialog(null, "L'ordinateur distant est d�connect�");
				inOk = false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("*** WARNING *** Erreur lors de la fermeture du canal d'entr�e " + e1 );
				}
			}
		}
	}
}