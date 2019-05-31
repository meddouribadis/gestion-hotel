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



public class Panel_Chambre extends JPanel {
	
	public Connection connc1 = null;
	public Chambres_Update_Fenetre frame;
	public Chambres_Ajouter_Fenetre adder;
	public JTable table;
	public JButton btnModifier = new JButton("Modifier");
	public JButton btnAddChambre = new JButton("Ajouter chambre");
	public JButton btnSuppr = new JButton("Supprimer");
	public JButton btnRaffraichir = new JButton("Raffraichir");

	public Panel_Chambre() {
		connc1 = sqlConnect.dbConnector("chambre.sqlite");
		this.setBackground(new Color(204, 204, 204));
		this.setBounds(172, 105, 710, 513);
		this.setLayout(null);
		btnAddChambre.setBounds(277, 57, 129, 35);
		this.add(btnAddChambre);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 102, 677, 365);
		this.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		actualiserJTABLE();

	}
	
	public void actualiserJTABLE() {
		try {
			String query="SELECT * from chambres";
			PreparedStatement pst = connc1.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			System.out.println("Refresh avec succès.");
		}
		catch(Exception z) {
			System.out.println(z);
			z.printStackTrace();
		}
	}
	
	public int obtenirLesInformations() {
		int colonne = -1;
		try{
			colonne = table.getSelectedRow();
			return colonne;
		}
		 catch(Exception e)
	    {
	      System.out.println("Erreur innatendue !");
	      return -1;
	    }
	}
}
