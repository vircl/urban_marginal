package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
/**
 * Classe ChoixJoueur
 * 
 * Cette classe génère une frame permettant au client de sélectionner un personnage
 * 
 * @project Urban Marginal
 * @pacakge vue
 * @version 1.0
 * @author  Virginie
 *
 */
public class ChoixJoueur extends JFrame implements Global {

	private JPanel contentPane;
	private JTextField txtPseudo;


	/**
	 * Constructeur 
	 * 
	 * Création de la frame
	 */
	public ChoixJoueur() {
		setTitle("Choisis ton personnage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_hover();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				souris_normale();
			}
		});
		lblPrecedent.setBounds(82, 100, 30, 60);
		contentPane.add(lblPrecedent);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_hover();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				souris_normale();
			}
		});
		lblSuivant.setBounds(314, 100, 40, 60);
		contentPane.add(lblSuivant);
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_hover();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				souris_normale();
			}
		});
		lblGo.setBounds(314, 213, 49, 46);
		contentPane.add(lblGo);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(135, 226, 165, 30);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
	
		
		JLabel lblFond = new JLabel("");
		lblFond.setVerticalAlignment(SwingConstants.TOP);
		lblFond.setBounds(0, 0, 423, 296);
		lblFond.setIcon(new ImageIcon(FOND_CHOIX));
		contentPane.add(lblFond);
		txtPseudo.requestFocus();
	}

	/**
	 * Comportement par défaut du curseur de la souris
	 * 
	 * @return void
	 */
	private void souris_normale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	/**
	 * Comportement de la souris au survol d'un bouton
	 * 
	 * @return void
	 */
	private void souris_hover() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
