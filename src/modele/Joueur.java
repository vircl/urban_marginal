package modele;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Classe Joueur
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 *
 */
public class Joueur extends Objet implements Global {
	
	private String     pseudo;
	private int        numPerso;
	private Label      message;
	private JeuServeur jeuServeur;
	private int        vie;
	private int        orientation;
	private int        etape;

	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur  = jeuServeur;
		this.vie         = 10;
		this.etape       = 1;
		this.orientation = DROITE;
	}
	
	/**
	 * Initialisation d'un joueur
	 * @param pseudo   Pseudo du joueur
	 * @param numPerso N° du joueur
	 */
	public void initPerso( String pseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs ) {
		this.pseudo   = pseudo;
		this.numPerso = numPerso;
		
		super.label   = new Label( Label.getNbLabel(), new JLabel() );
		Label.setNbLabel( Label.getNbLabel() + 1 );
		super.label.getjLabel().setHorizontalAlignment( SwingConstants.CENTER );
		super.label.getjLabel().setVerticalAlignment( SwingConstants.CENTER );
		this.jeuServeur.nouveauLabelJeu( label );
		
		this.message = new Label( Label.getNbLabel(), new JLabel() );
		Label.setNbLabel( Label.getNbLabel() + 1 );
		this.message.getjLabel().setHorizontalAlignment( SwingConstants.CENTER );
		this.message.getjLabel().setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		this.message.getjLabel().setForeground( Color.WHITE );
		this.jeuServeur.nouveauLabelJeu( message );
		
		this.premierePosition(lesJoueurs, lesMurs);
		this.affiche( MARCHE, this.etape );
	}
	
	/**
	 * Getter pseudo
	 * @return le pseudo associé au joueur
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	/**
	 * Getter message
	 * @return le message du type Label
	 */
	public Label getMessage() {
		return this.message;
	}
	
	/**
	 * Teste la collision entre les joueurs
	 * @param  lesJoueurs Dictionnaire contenant la liste des joueurs
	 * @return boolean
	 */
	public boolean toucheJoueur( Hashtable<Connection, Joueur> lesJoueurs ) {
		for ( Joueur unJoueur : lesJoueurs.values() )  {
			if ( ! unJoueur.equals( this ) && toucheObjet( unJoueur ) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Teste la collision entre le joueur et les murs
	 * @param  lesMurs Liste des murs
	 * @return boolean
	 */
	public boolean toucheMur( ArrayList<Mur> lesMurs ) {
		for ( Mur unMur: lesMurs ) {
			if ( toucheObjet( unMur ) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Défini la première position du joueur
	 * @param lesJoueurs Dictionnaire contenant la liste des joueurs
	 * @param lesMurs    Liste des murs
	 */
	private void premierePosition( Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs ) {
		super.label.getjLabel().setBounds(0, 0, L_PERSO, H_PERSO);
		do {
			super.posx  = (int) ( Math.round( Math.random() * ( L_ARENE - L_PERSO) ) );
			super.posy  = (int) ( Math.round( Math.random() * ( H_ARENE - H_PERSO - H_MESSAGE) ) );
		} while ( this.toucheMur( lesMurs ) || this.toucheJoueur( lesJoueurs ) ) ;
	}
	
	/**
	 * Affiche le joueur en fonction de son état et l'étape de marche
	 * @param etat
	 */
	private void affiche( String etat, int etape ) {
		super.label.getjLabel().setBounds( super.posx, super.posy, L_PERSO, H_PERSO );
		super.label.getjLabel().setIcon( new ImageIcon( PERSO + numPerso + etat + etape + "d" + this.orientation + EXTENSION ) );
		this.message.getjLabel().setBounds( super.posx - 10, super.posy + H_PERSO, L_PERSO + 20, H_PERSO );
		this.message.getjLabel().setText( this.pseudo + " (" + this.vie + ")" );
		this.jeuServeur.envoi( super.label );
		this.jeuServeur.envoi( this.message );
	}
	
	/**
	 * Gestion du déplacement du joueur
	 * @param action
	 * @param position
	 * @param orientation
	 * @param pas
	 * @param max
	 * @param lesJoueurs
	 * @param lesMurs
	 * @return
	 */
	private int deplace( int action, int position, int orientation, int pas, int max, Hashtable<Connection,Joueur> lesJoueurs, ArrayList<Mur> lesMurs ) {
		this.orientation = orientation;
		int positionInitiale = position;
		position += pas;
		position = position < 0 ? 0 : position > max ? max : position;
		this.posx = action == GAUCHE || action == DROITE ? position : this.posx;
		this.posy = action == HAUT || action == BAS ? position : this.posy;
		if ( this.toucheJoueur( lesJoueurs ) || this.toucheMur( lesMurs ) ) {
			position = positionInitiale;
		}
		etape = ( etape % NB_ETAPES ) + 1 ;
		return position;
	}
	
	/**
	 * Repositionne le joueur sur l'arène
	 * @param action
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	public void action( int action, Hashtable<Connection,Joueur> lesJoueurs, ArrayList<Mur> lesMurs ) {
		switch ( action ) {
		case GAUCHE :
			this.posx = deplace( action, this.posx, GAUCHE, -PAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs );
			break;
		case DROITE :
			this.posx = deplace( action, this.posx, DROITE, PAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs );
			break;
		case HAUT :
			this.posy = deplace( action, this.posy, orientation, -PAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs );
			break;
		case BAS :
			this.posy = deplace( action, this.posy, orientation, PAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs );
			break;
		case TIRE :
			break;
		}
		affiche( MARCHE, etape );
	}
}
