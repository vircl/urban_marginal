package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
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
	private JTextArea  txtTchat;
	private boolean    estClient;
	private Controle   controle;

	/**
	 * Constructeur
	 */
	public Arene( String typeJeu, Controle controle ) {
		this.estClient = typeJeu.equals("client");
		this.controle  = controle;
		
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
		
		if ( estClient ) {
			contentPane.addKeyListener( new KeyAdapter() {
				@Override
				public void keyPressed( KeyEvent arg0 ) {
					contentPane_keyPressed(arg0);
				}
			});
		}
		/**
		 * Zone de texte saisie chat
		 */
		if ( estClient ) {
			txtSaisie = new JTextField();
			txtSaisie.setBounds( 0, H_ARENE, L_ARENE, H_SAISIE );
			contentPane.add( txtSaisie );
			txtSaisie.setColumns( 10 );
			txtSaisie.addKeyListener( new KeyAdapter() {
				@Override
				public void keyPressed( KeyEvent arg0 ) {
					txtSaisie_keyPressed(arg0);
				}
			});
		}
		
		/**
		 * Conteneur Tchat
		 */
		JScrollPane jspTchat = new JScrollPane();
		jspTchat.setBounds( 0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7 * MARGE );
		contentPane.add( jspTchat );
		jspTchat.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		
		/**
		 * Text area chat
		 */
		txtTchat = new JTextArea();
		jspTchat.setViewportView( txtTchat );
		
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
	 * Getter jpnMurs
	 * @return JPanel murs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	/**
	 * Getter txtTchat
	 * @return
	 */
	public String getTxtTchat() {
		return txtTchat.getText();
	}
	
	/**
	 * Gestion des déplacements des personnages
	 * @param arg0 Code de la touche pressée par l'utilisateur
	 */
	private void contentPane_keyPressed(KeyEvent arg0) {
		int valeur = -1;
		switch ( arg0.getKeyCode() ) {
			case KeyEvent.VK_SPACE:
				valeur = TIRE;
				break;
			case KeyEvent.VK_LEFT:
				valeur = GAUCHE;
				break;
			case KeyEvent.VK_RIGHT:
				valeur = DROITE;
				break;
			case KeyEvent.VK_UP:
				valeur = HAUT;
				break;
			case KeyEvent.VK_DOWN:
				valeur = BAS;
				break;
		}
		if ( valeur != -1 ) {
			this.controle.evenementVue( this,  ACTION + SEPARE + valeur );
		}
	}
	
	/**
	 * Validation zone de saisie Tchat
	 * @param arg0 Code de la touche pressée par l'utilisateur
	 */
	private void txtSaisie_keyPressed(KeyEvent arg0) {
		if ( ( arg0.getKeyCode() == KeyEvent.VK_ENTER ) && ( ! txtSaisie.getText().equals("") ) ) {
			this.controle.evenementVue( this, TCHAT + SEPARE + txtSaisie.getText() );
			txtSaisie.setText("");
			contentPane.requestFocus();
		}
	}

	
	/**
	 * Ajout d'un mur sur l'Arene
	 * @param unMur Le mur à ajouter
	 */
	public void ajoutMur( JLabel unMur ) {
		jpnMurs.add( unMur );
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
	 * Ajout d'un joueur sur l'Arène
	 * @param unJoueur
	 */
	public void ajoutJoueur( JLabel unJoueur ) {
		jpnJeu.add( unJoueur );
		jpnJeu.repaint();
	}
	
	/**
	 * Modification d'un joueur sur l'Arène
	 * ( Le joueur est ajouté s'il n'existe pas encore )
	 * @param num   N° du joueur à modifier
	 * @param label Label du joueur
	 */
	public void modifJoueur( int num, JLabel jLabel ) {
		try {
			jpnJeu.remove( num );
		} catch ( ArrayIndexOutOfBoundsException e ) {}
		jpnJeu.add( jLabel, num );
		jpnJeu.repaint();
	}
	
	/**
	 * Mise à jour du tchat : ajout d'une phrase
	 * @param unePhrase
	 */
	public void ajoutTchat( String unePhrase ) {
		String old = txtTchat.getText();
		txtTchat.setText( unePhrase + "\r\n" + old );
	}
	/**
	 * Remplace le contenu du tchat
	 * @param contentu
	 */
	public void remplaceTchat( String contenu ) {
		txtTchat.setText( contenu );
	}
}
