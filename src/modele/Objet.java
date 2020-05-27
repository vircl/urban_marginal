package modele;

/**
 * Classe Objet
 * 
 * Définition des caractéristiques communes
 * des différents objets utilisés dans l'application
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
	
	
}
