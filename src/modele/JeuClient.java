package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Classe JeuClient
 * 
 * Affichage du jeu côté client
 * 
 * @project Urban Marginal
 * @package modele
 * @version 1.0
 * @author  Virginie
 */
public class JeuClient extends Jeu {

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
	public JeuClient(Controle controle) {
		super.controle = controle;
	}

}
