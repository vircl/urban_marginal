package modele;

import javax.swing.JLabel;

/**
 * Classe Objet
 * 
 * Définition des caractéristiques communes
 * des différents objets utilisés dans l'application
 * (Murs, Personnages, Boules ...)
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
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
	 * Setter posx
	 */
	public void setPosx( int posx ) {
		this.posx = posx;
	}
	
	/**
	 * Setter posy
	 * @param posy
	 */
	public void setPosy( int posy ) {
		this.posy = posy;
	}
	
	/**
	 * Teste si l'objet actuel touche celui passé en paramètre
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
