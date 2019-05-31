package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.NomGerant;
import model.sqlConnect;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Button;
import java.awt.FlowLayout;

public class Home extends JFrame {
	
	public NomGerant patron = new NomGerant();
	private JLabel Nom_Patron = new JLabel("");
	
	public JPanel Conteneur = new JPanel();
	public JPanel panel_Haut = new JPanel();
	public JPanel panel_Centrale = new JPanel();
	public JPanel barre_laterale = new JPanel();
	
	public Panel_Chambre panel_Chambre = new Panel_Chambre();
	public Panel_Client panel_Client = new Panel_Client();
	public Panel_Reservation panel_Reservation = new Panel_Reservation();
	
	public JButton button_Accueil = new JButton("Accueil");
	public JButton bouton_Chambres = new JButton("Chambres");
	public JButton boutton_Client = new JButton("Clients");
	public JButton bouton_reservation = new JButton("Reservations");
	private final JLabel Informations = new JLabel("Bienvenue sur le logiciel de Gestion d'Hôtel. \n Tous se passe sur le panneau latérale gauche ! \n");
    
	private String pathtofile = System.getProperty("user.dir");
	
	public Home() {

		/* Premier bloc : Fenetre et conteneur principale */
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(pathtofile+"\\config_files\\ico_32.png")); /* Icone trouvée sur flaticon.com */
		this.setTitle("Projet Java ~ MEDDOURI Badis et LIBERGE Kevin");
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Le programme se ferme lorsque l'utilisateur clique sur la croix */
		this.setSize(882, 618); /* On définit la taille de la fenêtre (882px de largeur et 618px de hauteur) */
		this.setLocationRelativeTo(null); /* La fenêtre s'ouvre centrée */
		this.setResizable(false); /* La Fenetre n'est pas redimensionnable */
		setContentPane(Conteneur);
		Conteneur.setLayout(null);

		/* Deuxième bloc : Panel du Haut (Ou le Nom du patron / employé est affiché) */
		panel_Haut.setBackground(new Color(28, 28, 33));
		panel_Haut.setBounds(0, 0, 882, 105);
		Conteneur.add(panel_Haut);
		panel_Haut.setLayout(null);
		
		Nom_Patron.setForeground(Color.WHITE);
		Nom_Patron.setHorizontalAlignment(SwingConstants.RIGHT);
		Nom_Patron.setFont(new Font("Dialog", Font.PLAIN, 21));
		Nom_Patron.setBounds(596, 59, 276, 46);
		Nom_Patron.setText(patron.getBossName());
		panel_Haut.add(Nom_Patron);
		
		/* Troisième Bloc : Barre Laterale à Gauche où tous les boutons sont positionnés*/
		barre_laterale.setBackground(Color.DARK_GRAY);
		barre_laterale.setBounds(0, 105, 171, 513);
		Conteneur.add(barre_laterale);
		barre_laterale.setLayout(null);
		
			/* Ajout des différents boutons dans le troisième bloc */
			button_Accueil.setBounds(0, 45, 171, 50);
			bouton_reservation.setBounds(0, 115, 171, 50);
			bouton_Chambres.setBounds(0, 185, 171, 50);
			boutton_Client.setBounds(0, 255, 171, 50);
			barre_laterale.add(button_Accueil);
			barre_laterale.add(bouton_reservation);
			barre_laterale.add(bouton_Chambres);
			barre_laterale.add(boutton_Client);
		
		/* Quatrième Bloc : Le panel par defaut */
		panel_Centrale.setBackground(new Color(204, 204, 204));
		panel_Centrale.setBounds(172, 105, 710, 513);
		Conteneur.add(panel_Centrale);
		panel_Centrale.setLayout(new BorderLayout());
		
		Informations.setHorizontalAlignment(SwingConstants.CENTER);
		Informations.setVerticalAlignment(SwingConstants.CENTER);
		panel_Centrale.add(Informations, BorderLayout.CENTER);

	}
}


