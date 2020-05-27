package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Classe Mur
 * Cette classe hérite de la classe Objet
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur() {
		super.posx  = (int) ( Math.round( Math.random() * ( L_ARENE - L_MUR) ) );
		super.posy  = (int) ( Math.round( Math.random() * (H_ARENE - H_MUR ) ) );
		super.label = new Label( -1, new JLabel() );
		super.label.getjLabel().setHorizontalAlignment( SwingConstants.CENTER );
		super.label.getjLabel().setVerticalAlignment( SwingConstants.CENTER );
		super.label.getjLabel().setBounds( super.posx, super.posy, L_MUR, H_MUR );
		super.label.getjLabel().setIcon( new ImageIcon( MUR ) );
	}
}
