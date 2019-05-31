package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.sqlite.SQLiteException;

import controller.Activer_panel;
import controller.Chambres_Ajouter;
import controller.Chambres_Modifier;
import controller.Chambres_Raffraichir;
import controller.Client_Ajouter;
import controller.Client_Raffraichir;
import model.Chambre;
import model.Client;
import model.DateVerif;
import model.Reservation;
import model.sqlConnect;

import java.util.ArrayList;
import java.util.Random;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Reservations_Ajouter_Fenetre extends JFrame {
	
	private Connection connc2 = sqlConnect.dbConnector("chambre.sqlite");
	private Connection connc1 = sqlConnect.dbConnector("client.sqlite");
	private int count;
	private int countchambre;
	private DateVerif dateVerificateur = new DateVerif();
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();
	
	private JLabel lblTitre = new JLabel("Entrez les informations requises :");
	private JLabel lblClient = new JLabel("Client");
	private JLabel lblChambre = new JLabel("Chambre");
	private JLabel lblDateDebut = new JLabel("Date Debut");
	private JLabel lblDateFin = new JLabel("Date Fin");
	
	public JButton btnValider = new JButton("Ajouter");
	
	public JTextField date_debut_champ;
	public JTextField date_fin_champ;
	
	public JComboBox clientliste = new JComboBox();
	public JComboBox chambresListe = new JComboBox();
	
	public ArrayList<String> client_SelectedID = new ArrayList<String>();
	public ArrayList<String> client_SelectedNom = new ArrayList<String>();
	public ArrayList<String> chambre_SelectedID = new ArrayList<String>();
	
	private int xyz = 0;

	public Reservations_Ajouter_Fenetre() {
	
		this.setTitle("Créer une réservation");	
		this.setSize(330, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(0, 0, 330, 101);
		contentPane.add(lblTitre);
		
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 100, 330, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(lblClient);
		
		/* Ici je vais charger le JCombobox (Menu déroulant) avec les clients de notre base de données */
		
		String s = "select * from client"; /* Je récupère tous les clients sans exception (*)  */
		try {
		PreparedStatement ps = connc1.prepareStatement(s);
		ResultSet rs;
	    rs = ps.executeQuery();
	    int count = 0;

		while(rs.next()) { /* Tant qu'il y'a des client je continue d'ajouter à la JCombobox */
		 clientliste.addItem(rs.getString("ID")+ " " +rs.getString("Nom")+ " " +rs.getString("Prenom")); /* J'ai décidé de stocker l'ID et les noms des clients dans des ArrayList */
		 client_SelectedID.add(rs.getString("ID"));
		 client_SelectedNom.add(rs.getString("Nom"));
		 count++;
		}
		
		ps.close();
		rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/* Même procédé que precedemment mais pour les chambres */
		
		String sc = "select * from chambres";
		try {
		PreparedStatement ps = connc2.prepareStatement(sc);
		ResultSet rs;
	    rs = ps.executeQuery();
	    countchambre = 0;

		while(rs.next()) {
		chambresListe.addItem(rs.getString("ID")+ " " +rs.getString("Etat")+ " " +rs.getString("NClient")); 
		 chambre_SelectedID.add(rs.getString("ID"));
		 countchambre++;
		}
		
		ps.close();
		rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(client_SelectedID.get(xyz));
		clientliste.setSelectedIndex(xyz);
		clientliste.setBounds(104, 22, 204, 25);
		panel.add(clientliste);
		
		btnValider.setBounds(68, 215, 190, 54);
		panel.add(btnValider);
		lblClient.setBounds(25, 27, 70, 15);
		lblDateDebut.setBounds(25, 124, 70, 15);
		
		panel.add(lblDateDebut);
		lblDateFin.setBounds(25, 150, 70, 15);
		
		panel.add(lblDateFin);
		
		date_debut_champ = new JTextField();
		date_debut_champ.setBounds(183, 120, 125, 22);
		panel.add(date_debut_champ);
		
		date_fin_champ = new JTextField();
		date_fin_champ.setBounds(183, 146, 125, 22);
		panel.add(date_fin_champ);

		lblChambre.setBounds(25, 75, 70, 15);
		panel.add(lblChambre);
		
		chambresListe.setBounds(104, 70, 204, 25);
		panel.add(chambresListe);

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					System.out.println();
					if(dateVerificateur.verifierLaDate(date_debut_champ.getText()) == 0 && dateVerificateur.verifierLaDate(date_fin_champ.getText()) == 0) {
						Reservation reservation = new Reservation(client_SelectedID.get(clientliste.getSelectedIndex()), client_SelectedNom.get(clientliste.getSelectedIndex()), chambre_SelectedID.get(chambresListe.getSelectedIndex()), date_debut_champ.getText(), date_fin_champ.getText());
						if (reservation.erreur == false) {
							System.out.println("Ecriture avec succès");
							JOptionPane succesMessage = new JOptionPane();
							succesMessage.showMessageDialog(null, "La réservation a bien été ajoutée !","Succès",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane erreurSQL = new JOptionPane();
							erreurSQL.showMessageDialog(null, "Erreur base de donnée : Vérifiez que la chambre n'est pas déjà reservée !", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if (dateVerificateur.verifierLaDate(date_debut_champ.getText()) == -2 || dateVerificateur.verifierLaDate(date_fin_champ.getText()) == -2){
						JOptionPane alertMessage = new JOptionPane();
						alertMessage.showMessageDialog(null, "Erreur : Date(s) vide(s) !","Attention",JOptionPane.WARNING_MESSAGE);
					}
					else {
						JOptionPane alertMessage = new JOptionPane();
						alertMessage.showMessageDialog(null, "Erreur : Les dates doivent être au format JJ/MM/AAAA !","Attention",JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println(e.getClass().getSimpleName());
				}

			}
		});
		
		
	}
}