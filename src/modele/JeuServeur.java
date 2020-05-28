package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Classe JeuServeur
 * 
 * Affichage du jeu côté serveur
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 */
public class JeuServeur extends Jeu implements Global {

	private ArrayList<Mur> lesMurs                  = new ArrayList<Mur>();
	private Hashtable<Connection,Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();
	
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
		switch ( Integer.parseInt( infos[0] ) ) {
		case PSEUDO : 
			controle.evenementModele(this, "envoi panel murs", connection );
			Joueur leJoueur = lesJoueurs.get( connection );
			leJoueur.initPerso(infos[1], Integer.parseInt( infos[2] ), lesJoueurs, lesMurs );
			break; 
		}
	}

	@Override
	public void deconnection( Connection connection ) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for ( int i = 0; i < NB_MURS; i++ ) {
			this.lesMurs.add(new Mur() );
			controle.evenementModele( this, "ajout mur", this.lesMurs.get( lesMurs.size() - 1 ).getLabel().getjLabel() );
		}
	}
	
	/**
	 * Ajout d'un joueur
	 * @param label Label correspondant au joueur à ajouter
	 */
	public void nouveauLabelJeu( Label label ) {
		controle.evenementModele( this, "ajout joueur", label.getjLabel() );
	}
	
}
