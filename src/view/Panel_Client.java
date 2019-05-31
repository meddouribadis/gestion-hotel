package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import model.sqlConnect;
import net.proteanit.sql.DbUtils;

public class Panel_Client extends JPanel {
	
	public JTable table;
	public Connection connc1 = null;
	public JButton btnAddClient = new JButton("Ajouter client");
	public JButton btnModifier = new JButton("Modifier");
	public JButton btnSuppr = new JButton("Supprimer");
	public JButton btnRaffraichir = new JButton("Raffraichir");
	public Client_Update_Fenetre frame;
	public Client_Ajouter_Fenetre adder;
    

	public Panel_Client() {
		connc1 = sqlConnect.dbConnector("client.sqlite");
		//Début this;
		this.setBackground(new Color(204, 204, 204));
		this.setBounds(172, 105, 710, 513);
		this.setLayout(null);
		
		//Bouton Ajouter
		btnAddClient.setBounds(160, 57, 129, 35);
		this.add(btnAddClient);
		
		//Bouton Modifier 
		btnModifier.setBounds(300, 57, 129, 35);
		this.add(btnModifier);
		//Fin Bouton Modifier 
		
		//Bouton Supprimer 
		btnSuppr.setBounds(440, 57, 129, 35);
		this.add(btnSuppr);
		//Fin Bouton Supprimer 
		
		//Debut Bouton Refresh
		btnRaffraichir.setBounds(555, 10, 129, 35);
		this.add(btnRaffraichir);
		//Fin Bouton Refresh
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 102, 538, 356);
		this.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		actualiserJTABLE();

	}

	
	public void actualiserJTABLE() {
		try {
			String query="select * from client";
			PreparedStatement pst = connc1.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception z) {
			System.out.println(z);
			z.printStackTrace();
			
		}
	}
	
	public int obtenirLigne() {
		int colonne = -1;
		try{
			colonne = table.getSelectedRow();
			return colonne;
		}
		 catch(Exception e)
	    {
	      return -1;
	    }
	}
}
