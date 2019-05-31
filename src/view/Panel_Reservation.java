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



public class Panel_Reservation extends JPanel {
	
	public JTable table;
	private Connection connc1 = null;
	public Reservations_Ajouter_Fenetre adder;
	public Reservations_Update_Fenetre updater;
	public JButton btnNewButton = new JButton("Creer reservation");
	public JButton btnModifier = new JButton("Modifier");
	public JButton btnSuppr = new JButton("Supprimer");
	public JButton btnRaffraichir = new JButton("Raffraichir");
	public JScrollPane scrollPane = new JScrollPane();
	

	public Panel_Reservation() {
		connc1 = sqlConnect.dbConnector("reservation.sqlite");
		this.setBackground(new Color(204, 204, 204));
		this.setBounds(172, 105, 710, 513);
		this.setLayout(null);
		btnNewButton.setBounds(277, 57, 129, 35);
		this.add(btnNewButton);
		
		//Bouton Modifier 
		btnModifier.setBounds(416, 57, 129, 35);
		this.add(btnModifier);
		//Fin Bouton Modifier 
		
		//Bouton Supprimer 
		btnSuppr.setBounds(555, 57, 129, 35);
		this.add(btnSuppr);
		//Fin Bouton Supprimer 
		
		//Debut Bouton Refresh
		btnRaffraichir.setBounds(555, 10, 129, 35);
		this.add(btnRaffraichir);
		//Fin Bouton Refresh
		
		
		scrollPane.setBounds(23, 102, 677, 365);
		this.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		actualiserJTABLE();

	}

	
	public void actualiserJTABLE() {
		try {
			String query="SELECT * from reservation";
			PreparedStatement pst = connc1.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception z) {
			System.out.println(z);
			z.printStackTrace();
		}
	}
	
	public int obtenirLaLigne() {
		int ligne = -1;
		try{
			ligne = table.getSelectedRow();
			return ligne;
		}
		 catch(Exception e)
	    {
	      return -1;
	    }
	}
}
