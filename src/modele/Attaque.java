package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Classe Attaque
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 *
 */
public class Attaque extends Thread implements Global {

	private Joueur         attaquant;
	private JeuServeur     jeuServeur;
	private ArrayList<Mur> lesMurs;
	private Hashtable<Connection, Joueur> lesJoueurs;

	
	/**
	 * Constructeur
	 * @param attaquant
	 * @param jeuServeur
	 */
	public Attaque( Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs ) {
		this.attaquant  = attaquant;
		this.jeuServeur = jeuServeur;
		this.lesMurs    = lesMurs;
		this.lesJoueurs = lesJoueurs;
		super.start();
	}
	
	/**
	 * Lancement du thread
	 */
	public void run() {
		attaquant.affiche( MARCHE , 1 ); 
		Boule laBoule     = this.attaquant.getBoule();
		Joueur victime    = null;
		int   orientation = this.attaquant.getOrientation();
		laBoule.getLabel().getjLabel().setVisible( true );
		do {
			laBoule.setPosx( orientation == GAUCHE ? laBoule.getPosx() - PAS : laBoule.getPosx() + PAS ) ;
			laBoule.getLabel().getjLabel().setBounds( laBoule.getPosx(), laBoule.getPosy(), L_BOULE, H_BOULE );
			this.pause( 10, 0 );
			this.jeuServeur.envoi( laBoule.getLabel() );
			victime = toucheJoueur();
		} while ( laBoule.getPosx() >= 0 && laBoule.getPosx() <= L_ARENE  &&  ! toucheMur() && victime == null );
		if ( victime != null && ! victime.estMort() ) {
			this.jeuServeur.envoi( SON_BLESSE );
			victime.perdVie();
			attaquant.gagneVie();
			for ( int i = 1; i < NB_ETATS_BLESSE; i ++ ) {
				victime.affiche( BLESSE, i );
				this.pause( 80, 0 );
			}
			if ( victime.estMort() ) {
				this.jeuServeur.envoi( SON_MORT );
				for ( int i = 1; i < NB_ETATS_MORT; i ++ ) {
					victime.affiche( MORT, i);
					this.pause( 80, 0);
				}
			} else {
				victime.affiche( MARCHE, 1 );
			}
		}
		attaquant.affiche( MARCHE , 1 );
		laBoule.getLabel().getjLabel().setVisible( false );
		this.jeuServeur.envoi( laBoule.getLabel() );
	}
	
	/**
	 * Pause
	 * @param milli
	 * @param nano
	 */
	private void pause( long milli, int nano ) {
		try {
			Thread.sleep( milli, nano );
		} catch (InterruptedException e) 	{
			System.out.println( "Problème sur la pause" );
		}
	}
	
	/**
	 * Teste si la boule touche un mur
	 * @return
	 */
	private boolean toucheMur() {
		for ( Mur unMur: this.lesMurs ) {
			if ( attaquant.getBoule().toucheObjet( unMur ) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Teste si la boule touche un joueur
	 * @return
	 */
	private Joueur toucheJoueur() {
		for ( Joueur unJoueur : this.lesJoueurs.values() ) {
			if ( attaquant.getBoule().toucheObjet( unJoueur ) ) {
				return unJoueur;
			}
		}
		return null;
	}
	
}
