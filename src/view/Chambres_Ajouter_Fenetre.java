package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class Chambres_Ajouter_Fenetre extends JFrame {

	private Connection connc2 = sqlConnect.dbConnector("chambre.sqlite");
	private Connection connc1 = sqlConnect.dbConnector("client.sqlite");
	
	private DateVerif dateVerificateur = new DateVerif();
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();
	
	private JLabel lblVeuillezEntrerLes = new JLabel("Entrez les informations que vous désirez :");
	private JLabel lblType = new JLabel("Type");
	private JLabel lbletat = new JLabel("Etat");
	private JLabel lblClient = new JLabel("Client");
	private JLabel lblDateDebut = new JLabel("Date Debut");
	private JLabel lblDateFin = new JLabel("Date Fin");
	
	private JButton btnValider = new JButton("Ajouter");
	
	private JTextField date_debut_champ;
	private JTextField date_fin_champ;
	
	private JComboBox clientliste = new JComboBox();
	private JComboBox type_Chambre = new JComboBox();
	private JComboBox etat_Chambre = new JComboBox();
	
	private ArrayList<String> selectedID = new ArrayList<String>();
	private ArrayList<String> selectedNom = new ArrayList<String>();
	
	private int count;
	private int xyz = 0;

	public static void main(String[] args) {
		Chambres_Ajouter_Fenetre salut = new Chambres_Ajouter_Fenetre();
		salut.setVisible(true);
	}

	public Chambres_Ajouter_Fenetre() {
	
		this.setTitle("Ajouter une Chambre");	
		this.setSize(330, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblVeuillezEntrerLes.setForeground(Color.WHITE);
		lblVeuillezEntrerLes.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeuillezEntrerLes.setBounds(0, 0, 330, 101);
		contentPane.add(lblVeuillezEntrerLes);
		
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 100, 330, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblType.setBounds(25, 30, 70, 15);
		panel.add(lblType);

		type_Chambre.setModel(new DefaultComboBoxModel(new String[] {"D", "S", "N", "P"}));
		type_Chambre.setBounds(183, 25, 125, 25);
		panel.add(type_Chambre);

		lbletat.setBounds(25, 60, 70, 15);
		panel.add(lbletat);

		etat_Chambre.setModel(new DefaultComboBoxModel(new String[] {"L", "R", "O"}));
		etat_Chambre.setSelectedIndex(1);
		etat_Chambre.setBounds(183, 55, 125, 25);
		panel.add(etat_Chambre);
		
		panel.add(lblClient);

		/* Permet de charger */
			type_Chambre.setSelectedIndex(0);
			etat_Chambre.setSelectedIndex(0);	

		String s = "select * from client";
		try {
		PreparedStatement ps = connc1.prepareStatement(s);
		ResultSet rs;
	    rs = ps.executeQuery();
	    count = 0;

		while(rs.next()) {
		 clientliste.addItem(rs.getString("ID")+ " " +rs.getString("Nom")+ " " +rs.getString("Prenom")); 
		 selectedID.add(rs.getString("ID"));
		 selectedNom.add(rs.getString("Nom"));
		 count++;
		}
		
		clientliste.addItem("PAS DE CLIENT");
		
		ps.close();
		rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		clientliste.setSelectedIndex(xyz);
		clientliste.setBounds(104, 88, 204, 25);
		panel.add(clientliste);
		
		btnValider.setBounds(68, 215, 190, 54);
		panel.add(btnValider);
		lblClient.setBounds(25, 93, 70, 15);
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
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(xyz + " " + count + " " + clientliste.getSelectedIndex());
				try {
					System.out.println();
					if(clientliste.getSelectedIndex() < count) {
						if(dateVerificateur.verifierLaDate(date_debut_champ.getText()) == 0 && dateVerificateur.verifierLaDate(date_fin_champ.getText()) == 0) {
							Chambre chaha = new Chambre((String)type_Chambre.getSelectedItem(), (String)etat_Chambre.getSelectedItem(), selectedNom.get(clientliste.getSelectedIndex()), date_debut_champ.getText(), date_fin_champ.getText(), selectedID.get(clientliste.getSelectedIndex()));
						}
						else if (dateVerificateur.verifierLaDate(date_debut_champ.getText()) == -2 || dateVerificateur.verifierLaDate(date_fin_champ.getText()) == -2){
							JOptionPane alertMessage = new JOptionPane();
							alertMessage.showMessageDialog(null, "Erreur : Date(s) vide(s) !","Info",JOptionPane.WARNING_MESSAGE);
						}
						else {
							JOptionPane alertMessage = new JOptionPane();
							alertMessage.showMessageDialog(null, "Erreur : Les dates doivent être au format JJ/MM/AAAA !","Info",JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						Chambre chaha = new Chambre((String)type_Chambre.getSelectedItem(), (String)etat_Chambre.getSelectedItem(), "", "", "", "");
					}
					System.out.println("Ecriture avec succès");
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println(e.getClass().getSimpleName());
				}

			}
		});
		
	}
}