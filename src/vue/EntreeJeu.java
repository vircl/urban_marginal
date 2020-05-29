package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe EntreeJeu
 * Cette classe génère une frame permettant au joueur de démarrer un nouveau serveur
 * ou de rejoindre un serveur existant
 * 
 * <p><b> Projet :  </b> Urban Marginal </p>
 * <p><b> Package : </b> vue </p>
 * <p><b> Auteur :  </b> vircl </p>
 */
public class EntreeJeu extends JFrame implements Global {

	private JPanel     contentPane;
	private JTextField txtIp;
	private Controle   controle;

	
	/**
	 * Constructeur
	 * @param controle Instance du contrôleur
	 */
	public EntreeJeu(Controle controle) {
		
		/**
		 * Initialisation de la frame
		 */
		setTitle( "Urban Marginal" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 436, 180 );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );
		
		/**
		 * Label Connecter serveur
		 */
		JLabel lblConnect = new JLabel( "Se connecter \u00E0 un serveur existant" );
		lblConnect.setBounds( 10, 49, 262, 14 );
		contentPane.add( lblConnect );
		
		
		/**
		 * Label Démarrer serveur
		 */
		JLabel lblStart = new JLabel( "D\u00E9marrer un nouveau serveur" );
		lblStart.setBounds( 10, 24, 262, 14 );
		contentPane.add( lblStart );
		
		/**
		 * Label IP serveur
		 */
		JLabel lblIp = new JLabel("IP du serveur :");
		lblIp.setBounds(10, 74, 97, 20);
		contentPane.add(lblIp);
		
		/**
		 * Zone de texte IP serveur
		 */
		txtIp = new JTextField();
		txtIp.setText( DEFAULT_IP );
		txtIp.setBounds( 117, 74, 155, 20 );
		contentPane.add( txtIp );
		txtIp.setColumns( 10 );
		
		/**
		 * Bouton démarrer
		 */
		JButton btnStart = new JButton( "D\u00E9marrer" );
		btnStart.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent arg0 ) {
				btnStart_clic();
			}
		});
		btnStart.setBounds(282, 20, 117, 23);
		contentPane.add(btnStart);
		
		/**
		 * Bouton connecter
		 */
		JButton btnConnect = new JButton( "Connecter" );
		btnConnect.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds( 282, 73, 117, 23 );
		contentPane.add( btnConnect );
		
		/**
		 * Bouton sortir
		 */
		JButton btnExit = new JButton( "Quitter" );
		btnExit.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				btnExit_clic();
			}
		});
		btnExit.setBounds( 282, 107, 117, 23 );
		contentPane.add( btnExit );
		
		/**
		 * Initialisation du contrôleur
		 */
		this.controle = controle;
	}
	
	/**
	 * Clic sur le bouton start pour lancer le serveur
	 */
	private void btnStart_clic() {
		System.out.println( "Clic sur le bouton start" );
		this.controle.evenementVue( this, "serveur" );
	}
	
	/**
	 * Clic sur le bouton connecter pour rejoindre un serveur
	 */
	private void btnConnect_clic() {
		System.out.println( "Clic sur le bouton connect" );
		this.controle.evenementVue( this, this.txtIp.getText() );
	}
	
	/**
	 * Clic sur le bouton exit : fermeture de la frame
	 */
	private void btnExit_clic() {
		System.out.println( "Clic sur le bouton exit" );
		System.exit(0);
	}
}
