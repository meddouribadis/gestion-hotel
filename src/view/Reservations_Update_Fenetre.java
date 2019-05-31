package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.sqlite.SQLiteException;
import controller.*;
import model.Chambre;
import model.Client;
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

public class Reservations_Update_Fenetre extends JFrame {

	private Connection connc2 = sqlConnect.dbConnector("chambre.sqlite");
	private Connection connc1 = sqlConnect.dbConnector("client.sqlite");
	private int count;
	private int countchambre;
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();
	
	private JLabel lblVeuillezEntrerLes = new JLabel("Mettez à jour les informations que vous désirez :");
	private JLabel lblClient = new JLabel("Client");
	private JLabel lblChambre = new JLabel("Chambre");
	private JLabel lblDateDebut = new JLabel("Date Debut");
	private JLabel lblDateFin = new JLabel("Date Fin");
	
	private JButton btnValider = new JButton("Mettre à jour");
	
	private JTextField date_debut_champ;
	private JTextField date_fin_champ;
	
	private JComboBox clientliste = new JComboBox();
	private JComboBox chambresListe = new JComboBox();
	
	private ArrayList<String> client_SelectedID = new ArrayList<String>();
	private ArrayList<String> client_SelectedNom = new ArrayList<String>();
	private ArrayList<String> chambre_SelectedID = new ArrayList<String>();
	
	private int indexClients = 0;
	private int indexChambres = 0;
	private Boolean IDFourni;

	public Reservations_Update_Fenetre(String pID, String IDClient, String IDChambre, String dDebut, String dFin) {
	
		this.setTitle("Modifier une réservation");	
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
		panel.add(lblClient);
		
		String s = "select * from client";
		try {
		PreparedStatement ps = connc1.prepareStatement(s);
		ResultSet rs;
	    rs = ps.executeQuery();
	    int count = 0;

		while(rs.next()) {
		 clientliste.addItem(rs.getString("ID")+ " " +rs.getString("Nom")+ " " +rs.getString("Prenom")); 
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
		
		for (String uID : client_SelectedID) {
			if(client_SelectedID.get(indexClients).equals(IDClient)){
		        break;
		    }
			indexClients++;
		}
		
		/* Récuperation des chambres dans la combobox */
		
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
		
		for (String cID : chambre_SelectedID) {
			if(chambre_SelectedID.get(indexChambres).equals(IDChambre)){
		        break;
		    }
			indexChambres++;
		}
		
		System.out.println(client_SelectedID.get(indexClients));
		clientliste.setSelectedIndex(indexClients);
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
		date_debut_champ.setText(dDebut);
		panel.add(date_debut_champ);
		
		date_fin_champ = new JTextField();
		date_fin_champ.setBounds(183, 146, 125, 22);
		date_fin_champ.setText(dFin);
		panel.add(date_fin_champ);

		lblChambre.setBounds(25, 75, 70, 15);
		panel.add(lblChambre);
		
		chambresListe.setBounds(104, 70, 204, 25);
		chambresListe.setSelectedIndex(indexChambres);
		panel.add(chambresListe);
		
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println();
					Reservation reservation = new Reservation();
					reservation.modifierUneReservation(pID, client_SelectedID.get(clientliste.getSelectedIndex()), client_SelectedNom.get(clientliste.getSelectedIndex()), chambre_SelectedID.get(chambresListe.getSelectedIndex()), date_debut_champ.getText(), date_fin_champ.getText());
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