package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe Arene
 * 
 * Cette classe affiche l'arène dans 
 * laquelle les joueurs seront invités à combattre
 * 
 * @project Urban Marginal
 * @pacakge vue
 * @version 1.0
 * @author  Virginie
 *
 */
public class Arene extends JFrame {

	private JPanel contentPane;

	/**
	 * Constructeur
	 */
	public Arene() {
		/**
		 * Initialisation de la frame
		 */
		setTitle( "Arena" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 450, 300 );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );
	}

}
