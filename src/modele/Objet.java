package modele;

import javax.swing.JLabel;

/**
 * Classe Objet
 * 
 * D�finition des caract�ristiques communes
 * des diff�rents objets utilis�s dans l'application
 * (Murs, Personnages, Boules ...)
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 */
public abstract class Objet {
	
	protected int   posx;
	protected int   posy;
	protected Label label;
	
	/**
	 * Getter posx
	 * @return la position en x de l'objet
	 */
	public int getPosx() {
		return posx;
	}
	
	/**
	 * Getter poxy
	 * @return la position en y de l'objet
	 */
	public int getPosy() {
		return posy;
	}
	
	/**
	 * Getter label
	 * @return l'instance de Label contenant l'objet
	 */
	public Label getLabel() {
		return label;
	}
	
	/**
	 * Teste si l'objet actuel touche celui pass� en param�tre
	 * @param  objet   Objet dont on veut tester la collision
	 * @return boolean
	 */
	public boolean toucheObjet( Objet objet ) {
		if ( objet.label == null || objet.label.getjLabel() == null ) {
			return false;
		} else {
			int l_obj  = objet.label.getjLabel().getWidth();
			int h_obj  = objet.label.getjLabel().getHeight();
			int l_this = this.label.getjLabel().getWidth();
			int h_this = this.label.getjLabel().getHeight();
			return ( ! ( ( this.posx + l_this < objet.posx || 
					this.posx > objet.posx + l_obj ) || 
					( this.posy + h_this < objet.posy || 
					this.posy > objet.posy + h_obj ) ) );
		}
	}
}