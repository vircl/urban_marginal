package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Classe JeuServeur
 * 
 * Affichage du jeu c�t� serveur
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class JeuServeur extends Jeu implements Global {

	private ArrayList<Mur>               lesMurs    = new ArrayList<Mur>();
	private Hashtable<Connection,Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();
	private ArrayList<Joueur>            triJoueurs = new ArrayList<Joueur>();
	
	/**
	 * Constructeur
	 * @param controle Instance du controleur
	 */
	public JeuServeur( Controle controle ) {
		super.controle = controle;
		Label.setNbLabel(0);
	}
	
	@Override
	public void setConnection( Connection connection ) {
		this.lesJoueurs.put( connection, new Joueur( this ) );
		// controle.evenementModele(this, "envoi panel murs", connection );
	}

	@Override
	public void reception( Connection connection, Object info ) {
		String[] infos = ( ( String ) info ).split( SEPARE );
		String   laPhrase;
		switch ( Integer.parseInt( infos[0] ) ) {
		case PSEUDO : 
			controle.evenementModele(this, "envoi panel murs", connection );
			for ( Joueur unJoueur : triJoueurs ) {
				super.envoi( connection, unJoueur.getLabel() );
				super.envoi( connection, unJoueur.getMessage() );
				super.envoi( connection, unJoueur.getBoule().getLabel() );
			}
			this.lesJoueurs.get( connection ).initPerso(infos[1], Integer.parseInt( infos[2] ), lesJoueurs, lesMurs );
			this.triJoueurs.add( this.lesJoueurs.get( connection ) );
			laPhrase = " *** " + this.lesJoueurs.get( connection ).getPseudo() + " vient de se connecter *** ";
			controle.evenementModele( this, "ajout tchat", laPhrase );
			break; 
		case TCHAT :
			laPhrase = this.lesJoueurs.get( connection ).getPseudo() + " > " + ( (String) infos[1] );
			controle.evenementModele( this, "ajout tchat", laPhrase );
			break;
		case ACTION :
			if ( ! this.lesJoueurs.get(connection).estMort() ) {
				this.lesJoueurs.get( connection ).action( Integer.parseInt( infos[1] ), this.lesJoueurs, this.lesMurs );
			}
		}
	}

	@Override
	public void deconnection( Connection connection ) {
		this.lesJoueurs.get( connection ).quitter();
		this.lesJoueurs.remove( connection );
	}
	
	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for ( int i = 0; i < NB_MURS; i++ ) {
			this.lesMurs.add(new Mur() );
			controle.evenementModele( this, "ajout mur", this.lesMurs.get( lesMurs.size() - 1 ).getLabel().getjLabel() );
		}
	}
	
	/**
	 * Ajout d'un joueur
	 * @param label Label correspondant au joueur � ajouter
	 */
	public void nouveauLabelJeu( Label label ) {
		controle.evenementModele( this, "ajout joueur", label.getjLabel() );
	}
		
	/**
	 * Envoi des informations du serveur vers tous les clients
	 * @param info
	 */
	public void envoi( Object info ) {
		for ( Connection connection : lesJoueurs.keySet() ) {
			super.envoi( connection, info );
		}
	}
}
