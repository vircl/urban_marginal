package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe JeuServeur
 * 
 * Affichage du jeu côté serveur
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 */
public class JeuServeur extends Jeu {

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reception(Connection connection, Object info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Constructeur
	 * 
	 * @param  Controle controle Instance du controleur
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
}
