package modele;

import java.io.Serializable;

import javax.swing.JLabel;

/**
 * Classe Label
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> modele </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class Label implements Serializable {
	
	private static Integer nbLabel;
	private Integer        numLabel;
	private JLabel 		   jLabel;

	/**
	 * Constructeur
	 * @param numLabel N� du label
	 * @param jLabel   Instance de jLabel
	 */
	public Label(int numLabel, JLabel jLabel ) {
		this.numLabel = numLabel;
		this.jLabel   = jLabel;
	}
	/**
	 * Getter nbLabel
	 * @return N� du dernier label ajout�
	 */
	public static int getNbLabel() {
		return nbLabel;
	}

	/**
	 * Setter nbLabel
	 * @param nbLabel N� du dernier label ajout�
	 */
	public static void setNbLabel(int nbLabel) {
		Label.nbLabel = nbLabel;
	}
	
	/**
	 * Getter numLabel
	 * @return Num�ro du label
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
