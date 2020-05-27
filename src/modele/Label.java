package modele;

import java.io.Serializable;

import javax.swing.JLabel;

/**
 * Classe Label
 * 
 * @project  Urban Marginal
 * @pacakage modele
 * @version  1.0
 * @author   Virginie
 */
public class Label implements Serializable {
	
	private static Integer nbLabel;
	private Integer        numLabel;
	private JLabel 		   jLabel;

	/**
	 * Constructeur
	 * @param numLabel N° du label
	 * @param jLabel   Instance de jLabel
	 */
	public Label(int numLabel, JLabel jLabel ) {
		this.numLabel = numLabel;
		this.jLabel   = jLabel;
	}
	/**
	 * Getter nbLabel
	 * @return N° du dernier label ajouté
	 */
	public static int getNbLabel() {
		return nbLabel;
	}

	/**
	 * Setter nbLabel
	 * @param nbLabel N° du dernier label ajouté
	 */
	public static void setNbLabel(int nbLabel) {
		Label.nbLabel = nbLabel;
	}
	
	/**
	 * Getter numLabel
	 * @return Numéro du label
	 */
	public int getNumLabel() {
		return numLabel;
	}
	
	/**
	 * Getter jLabel
	 * @return Objet jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}
		
}
