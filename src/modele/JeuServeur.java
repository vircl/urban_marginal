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
		this.lesJoueurs.put( connection, new Joueur() );
		controle.evenementModele(this, "envoi panel murs", connection );
	}

	@Override
	public void reception( Connection connection, Object info ) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deconnection( Connection connection ) {
		// TODO Auto-generated method stub
		
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
	
}
