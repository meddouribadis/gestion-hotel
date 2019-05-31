package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.sqlite.SQLiteException;

import model.Client;
import model.sqlConnect;

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

public class Client_Update_Fenetre extends JFrame {

	private Connection connc2 = null;
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();
	
	private JButton btnValider = new JButton("Valider");
	
	private JTextField texte_Nom;
	private JTextField texte_Age;
	private JTextField texte_Nationalitee;
	private JTextField texte_Telephone;
	private JTextField texte_Prenom;
	
	private JLabel lblVeuillezEntrerLes = new JLabel("Mettez à jour les informations que vous désirez :");
	private JLabel lblNom = new JLabel("Nom");
	private JLabel lblPrnom = new JLabel("Prenom");
	private JLabel lblAge = new JLabel("Age");
	private JLabel lblNationalite = new JLabel("Nationalitee");
	private JLabel lblTlephone = new JLabel("Telephone");
	
	public Client_Update_Fenetre(String ID, String pName, String pPrenom, String nNationalite, String pTelephone, String pAge) {
		
		this.setTitle("Modifier un Client");	
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
		
		lblNom.setBounds(25, 30, 70, 15);
		panel.add(lblNom);
		
		texte_Nom = new JTextField();
		texte_Nom.setText(pName);
		texte_Nom.setBounds(183, 25, 135, 25);
		panel.add(texte_Nom);
		
		lblPrnom.setBounds(25, 61, 70, 15);
		panel.add(lblPrnom);
		
		texte_Prenom = new JTextField();
		texte_Prenom.setText(pPrenom);
		texte_Prenom.setBounds(183, 56, 135, 25);
		panel.add(texte_Prenom);
		
		lblAge.setBounds(25, 92, 70, 15);
		panel.add(lblAge);
		
		texte_Age = new JTextField();
		texte_Age.setText(pAge + "");
		texte_Age.setBounds(183, 87, 135, 25);
		panel.add(texte_Age);
		
		
		lblNationalite.setBounds(25, 122, 105, 15);
		panel.add(lblNationalite);
		
		texte_Nationalitee = new JTextField();
		texte_Nationalitee.setText(nNationalite);
		texte_Nationalitee.setBounds(183, 117, 135, 25);
		panel.add(texte_Nationalitee);
		
		lblTlephone.setBounds(25, 152, 105, 15);
		panel.add(lblTlephone);
		
		texte_Telephone = new JTextField();
		texte_Telephone.setText(pTelephone);
		texte_Telephone.setBounds(183, 147, 135, 25);
		panel.add(texte_Telephone);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (verifierChamps() == 0) {
					Client clientActuel = new Client();
					if(clientActuel.modifierUnClienbt(ID, texte_Nom.getText().toUpperCase(), texte_Prenom.getText(), texte_Nationalitee.getText(), texte_Age.getText(), texte_Telephone.getText()) == 0) {
						System.out.println("Mise à jour effectuée avec succès");
						JOptionPane succesMessage = new JOptionPane();
						succesMessage.showMessageDialog(null, "La mise à jour des informations a été effectuée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						JOptionPane alerteMessage = new JOptionPane();
						alerteMessage.showMessageDialog(null, "Erreur lors de la mise à jour des informations !","Attention !",JOptionPane.WARNING_MESSAGE);
					}
				}
				
				else {
					JOptionPane alerteMessage = new JOptionPane();
					alerteMessage.showMessageDialog(null, "Merci de remplir ou de verifier tous les champs requis !","Erreur :",JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		
		btnValider.setBounds(70, 205, 190, 54);
		panel.add(btnValider);
	}
	
	private int verifierChamps() {
		if (texte_Nom.getText().equals("") || texte_Nom.getText().matches("[0-9]+")) return -1;
		if (texte_Prenom.getText().equals("") || texte_Prenom.getText().matches("[0-9]+")) return -1;
		if (texte_Nationalitee.getText().equals("") || texte_Nationalitee.getText().matches("[0-9]+")) return -1;
		if (texte_Age.getText().equals("") || texte_Age.getText().matches("[0-9]+") == false) return -1;
		if (texte_Telephone.getText().equals("") || texte_Telephone.getText().matches("[0-9]+") == false) return -1;
	
		return 0;
	}
	
}