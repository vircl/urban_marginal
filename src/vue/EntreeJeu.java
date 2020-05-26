package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe EntreeJeu
 * 
 * Cette classe génère une frame permettant au joueur de démarrer un nouveau serveur
 * ou de rejoindre un serveur existant
 * 
 * @package Urban Marginal
 * @subpackage vue
 * @version 1.0
 * @author Virginie
 *
 */
public class EntreeJeu extends JFrame {

	private JPanel contentPane;
	private JTextField txtIp;

	/**
	 * Clic sur le bouton start pour lancer le serveur
	 * @return void
	 */
	private void btnStart_clic() {
		System.out.println("Clic sur le bouton start");
	}
	private void btnConnect_clic() {
		System.out.println("Clic sur le bouton connect");
	}
	/**
	 * Clic sur le bouton exit : fermeture de la frame
	 * @return void
	 */
	private void btnExit_clic() {
		System.out.println("Clic sur le bouton exit");
		System.exit(0);
	}
	
	/**
	 * Génération de la frame
	 */
	public EntreeJeu() {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConnect = new JLabel("Se connecter \u00E0 un serveur existant");
		lblConnect.setBounds(10, 49, 262, 14);
		contentPane.add(lblConnect);
		
		JLabel lblStart = new JLabel("D\u00E9marrer un nouveau serveur");
		lblStart.setBounds(10, 24, 262, 14);
		contentPane.add(lblStart);
		
		JLabel lblIp = new JLabel("IP du serveur :");
		lblIp.setBounds(10, 74, 97, 20);
		contentPane.add(lblIp);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(117, 74, 155, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		JButton btnStart = new JButton("D\u00E9marrer");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic();
			}
		});
		btnStart.setBounds(282, 20, 117, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connecter");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds(282, 73, 117, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setBounds(282, 107, 117, 23);
		contentPane.add(btnExit);
	}
}
