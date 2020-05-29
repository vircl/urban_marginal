package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
/**
 * Classe ChoixJoueur
 * Cette classe génère une frame permettant au client de sélectionner un personnage
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> vue </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class ChoixJoueur extends JFrame implements Global {

	private JPanel     contentPane;
	private JTextField txtPseudo;
	private JLabel     lblPersonnage;
	
	private Integer    numPerso;
	private Controle   controle;
	
	private Son        precedent;
	private Son        suivant;
	private Son        welcome;
	private Son        go;
	
	/**
	 * Constructeur 
	 */
	public ChoixJoueur( Controle controle ) {
		/**
		 * Définition de la Frame
		 */
		setTitle( "Choisis ton personnage" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 439, 335 );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );
		
		/**
		 * Bouton précédent
		 */
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered( MouseEvent arg0 ) {
				souris_hover();
			}
			@Override
			public void mouseExited( MouseEvent e ) {
				souris_normale();
			}
		});
		lblPrecedent.setBounds( 82, 100, 30, 60 );
		contentPane.add( lblPrecedent );
		
		/**
		 * Bouton suivant
		 */
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered( MouseEvent arg0 ) {
				souris_hover();
			}
			@Override
			public void mouseExited( MouseEvent e ) {
				souris_normale();
			}
		});
		lblSuivant.setBounds( 314, 100, 40, 60 );
		contentPane.add( lblSuivant );
		
		/**
		 * Bouton GO
		 */
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered( MouseEvent arg0 ) {
				souris_hover();
			}
			@Override
			public void mouseExited( MouseEvent e ) {
				souris_normale();
			}
		});
		lblGo.setBounds( 314, 213, 49, 46 );
		contentPane.add( lblGo );
		
		/**
		 * Zone de texte Pseudo
		 */
		txtPseudo = new JTextField();
		txtPseudo.setBounds( 135, 226, 165, 30 );
		contentPane.add( txtPseudo );
		txtPseudo.setColumns( 10 );
		txtPseudo.requestFocus();
		
		/**
		 * Affichage personnage
		 */
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment( SwingConstants.CENTER );
		lblPersonnage.setBounds( 153, 81, 122, 117 );
		contentPane.add( lblPersonnage );
	
		
		/**
		 * Image de fond
		 */
		JLabel lblFond = new JLabel("");
		lblFond.setVerticalAlignment( SwingConstants.TOP );
		lblFond.setBounds( 0, 0, 423, 296 );
		lblFond.setIcon( new ImageIcon( FOND_CHOIX ) );
		//lblFond.setIcon(new ImageIcon("D:\\Documents\\Dev\\java\\Urban Marginal\\media\\fonds\\fondchoix.jpg"));
		contentPane.add( lblFond );

		/**
		 * Initialisation du contrôleur
		 */
		this.controle = controle;
		
		/**
		 * Initialisation du personnage
		 */
		numPerso = 1;
		affichePerso();
		
		this.precedent = new Son( SON_PRECEDENT );
		this.suivant   = new Son( SON_SUIVANT );
		this.go        = new Son( SON_GO );
		this.welcome   = new Son( SON_WELCOME );
		this.welcome.play();
	}

	/**
	 * Comportement par défaut du curseur de la souris
	 * 
	 * @return void
	 */
	private void souris_normale() {
		contentPane.setCursor( new Cursor ( Cursor.DEFAULT_CURSOR ) );
	}
	
	/**
	 * Comportement de la souris au survol d'un bouton
	 * 
	 * @return void
	 */
	private void souris_hover() {
		contentPane.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
	}
	
	/**
	 * Affiche l'image du personnage en fonction du numéro
	 * 
	 * @return void
	 */
	private void affichePerso() {
		lblPersonnage.setIcon( new ImageIcon( PERSO + numPerso + MARCHE + "1d1" + EXTENSION ) );
	}
	
	/**
	 * Gestion du bouton précédent
	 * 
	 * @return void
	 */
	private void lblPrecedent_clic() {
		this.precedent.play();
		numPerso = ( ( numPerso + 1 ) % NB_PERSOS ) + 1;
		affichePerso();
	}
	
	/**
	 * Gestion du bouton suivant
	 * 
	 * @return void
	 */
	private void lblSuivant_clic() {
		this.suivant.play();
		numPerso = ( numPerso % NB_PERSOS ) + 1;
		affichePerso();
	}
	
	/**
	 * Gestion du clic sur le bouton Go
	 * 
	 * @return void
	 */
	private void lblGo_clic() {
		this.go.play();
		if ((txtPseudo.getText()).equals("")) {
			JOptionPane.showMessageDialog(null, "Le pseudo est obligatoire !");
			txtPseudo.requestFocus();
		} else {
			controle.evenementVue(this, PSEUDO + SEPARE + txtPseudo.getText() + SEPARE + numPerso );
		}
	}
}
