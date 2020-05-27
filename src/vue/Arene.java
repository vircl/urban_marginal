package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
public class Arene extends JFrame implements Global {

	private JPanel     contentPane;
	private JTextField txtSaisie;
	private JPanel     jpnMurs;
	private JPanel     jpnJeu;

	/**
	 * Constructeur
	 */
	public Arene() {
		/**
		 * Initialisation de la frame
		 */
		setTitle( "Arena" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, L_ARENE + 3*MARGE, H_ARENE + H_CHAT );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );
		
		/**
		 * Zone de texte saisie chat
		 */
		txtSaisie = new JTextField();
		txtSaisie.setBounds( 0, H_ARENE, L_ARENE, H_SAISIE );
		contentPane.add( txtSaisie );
		txtSaisie.setColumns( 10 );
		
		/**
		 * Conteneur Tchat
		 */
		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds( 0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7 * MARGE );
		contentPane.add( jspChat );
		jspChat.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		
		/**
		 * Text area chat
		 */
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView( txtChat );
		
		/**
		 * Jeu
		 */
		jpnJeu = new JPanel();
		jpnJeu.setBounds( 0, 0, L_ARENE, H_ARENE );
		contentPane.add( jpnJeu );
		jpnJeu.setLayout(null);
		jpnJeu.setOpaque( false );
		
		/**
		 * Murs
		 */
		jpnMurs = new JPanel();
		jpnMurs.setBounds( 0, 0, L_ARENE, H_ARENE );
		jpnMurs.setOpaque( false );
		contentPane.add( jpnMurs );
		jpnMurs.setLayout( null );

		
		/**
		 * Background
		 */
		JLabel lblFond = new JLabel("");
		lblFond.setBounds( 0, 0, L_ARENE, H_ARENE );
		lblFond.setIcon( new ImageIcon( FOND_ARENE ) );
		contentPane.add( lblFond );
		
	}
	
	/**
	 * Ajout d'un mur sur l'Arene
	 * @param unMur Le mur à ajouter
	 */
	public void ajoutMur( JLabel unMur ) {
		System.out.println( "Vue Arene méthode ajoutMur : " );
		jpnMurs.add( ( JLabel ) unMur );
		jpnMurs.repaint();
	}
	
	/**
	 * Ajout de tous les murs sur l'arène
	 * @param lesMurs Les murs à ajouter
	 */
	public void ajoutPanelMurs( JPanel lesMurs ) {
		jpnMurs.add( lesMurs );
		jpnMurs.repaint();
		contentPane.requestFocus();
	}
	
	/**
	 * Getter jpnMurs
	 * @return JPanel murs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
}
