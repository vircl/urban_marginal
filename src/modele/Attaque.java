package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Classe Attaque
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class Attaque extends Thread implements Global {

	private Joueur                        attaquant;
	private JeuServeur                    jeuServeur;
	private ArrayList<Mur>                lesMurs;
	private Hashtable<Connection, Joueur> lesJoueurs;

	
	/**
	 * Constructeur
	 * @param attaquant  le joueur à l'origine de l'attaque
	 * @param jeuServeur instance du serveur
	 */
	public Attaque( Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs ) {
		this.attaquant  = attaquant;
		this.jeuServeur = jeuServeur;
		this.lesMurs    = lesMurs;
		this.lesJoueurs = lesJoueurs;
		super.start();
	}
	
	/**
	 * Lancement du thread : permet de faire bouger la boule
	 */
	public void run() {

		attaquant.affiche( MARCHE , 1 ); 
		Boule  laBoule     = this.attaquant.getBoule();
		int    orientation = this.attaquant.getOrientation();
		laBoule.getLabel().getjLabel().setVisible( true );
		Joueur victime     = null;
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
	 * @param milli nombre de millisecondes de la pause
	 * @param nano  nombre de nanosecondes de la pause
	 */
	private void pause( long milli, int nano ) {
		try {
			Thread.sleep( milli, nano );
		} catch (InterruptedException e) 	{
			System.out.println( "*** ERREUR *** Problème sur la pause" );
		}
	}
	
	/**
	 * Teste si la boule touche un mur
	 * @return vrai si la boule est entrée en collision avec un mur
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
	 * @return vrai si la boule est entrée en collision avec un joueur
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
