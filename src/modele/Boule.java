package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Description des caractéristiques de la boule 
 * servant d'arme aux joueurs
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class Boule extends Objet implements Global {

	private JeuServeur jeuServeur;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.label = new Label( Label.getNbLabel(), new JLabel() );
		Label.setNbLabel( Label.getNbLabel() + 1 );
		super.label.getjLabel().setHorizontalAlignment( SwingConstants.CENTER );
		super.label.getjLabel().setVerticalAlignment( SwingConstants.CENTER );
		super.label.getjLabel().setBounds( 0, 0, L_BOULE, H_BOULE );
		super.label.getjLabel().setIcon( new ImageIcon( BOULE ) );
		super.label.getjLabel().setVisible( false );
		jeuServeur.nouveauLabelJeu( super.label );
	}
	
	/**
	 * Tire de la boule par un joueur
	 * @param attaquant le Joueur qui lance la boule
	 */
	public void tirer( Joueur attaquant, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs ) {
		this.posx = attaquant.getOrientation() == GAUCHE ? ( attaquant.getPosx() - L_BOULE - 1 ) : ( attaquant.getPosx() + L_PERSO + 1 );
		this.posy = attaquant.getPosy() + H_PERSO / 2; 
		new Attaque ( attaquant, jeuServeur, lesMurs, lesJoueurs );
	}
}
