package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.sqlite.SQLiteException;

import model.Client;

import java.util.Random;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Client_Ajouter_Fenetre extends JFrame {

	private JPanel contentPane;
	private JPanel panel = new JPanel();
	
	private JTextField champ_Nom;
	private JTextField champ_Age;
	private JTextField champ_Tel;
	private JTextField champ_Prenom;
	private JTextField champ_Nationalite;
	
	private JLabel lblVeuillezEntrerLes = new JLabel("Veuillez entrer les informations requises :");

	private JLabel lblNewLabel = new JLabel("Nom");
	private JLabel lblAge = new JLabel("Age");
	private JLabel lblTlephone = new JLabel("Telephone");
	private JLabel prenomLBL = new JLabel("Prenom");
	private JLabel lblNationalitee = new JLabel("Nationalitee");
	
	private JButton btnValider = new JButton("Valider");

	public Client_Ajouter_Fenetre() {
		
		this.setTitle("Ajouter un Client");	
		this.setSize(330, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		lblVeuillezEntrerLes.setForeground(Color.WHITE);
		lblVeuillezEntrerLes.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeuillezEntrerLes.setBounds(0, 0, 314, 101);
		contentPane.add(lblVeuillezEntrerLes);

		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 100, 314, 385);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel.setBounds(25, 30, 70, 15);
		panel.add(lblNewLabel);
		
		champ_Nom = new JTextField();
		champ_Nom.setText("Nom");
		champ_Nom.setBounds(154, 25, 150, 25);
		panel.add(champ_Nom);

		lblAge.setBounds(24, 155, 70, 15);
		panel.add(lblAge);
		
		champ_Age = new JTextField();
		champ_Age.setBounds(153, 150, 150, 25);
		panel.add(champ_Age);
		

		lblTlephone.setBounds(24, 126, 105, 15);
		panel.add(lblTlephone);
		
		champ_Tel = new JTextField();
		champ_Tel.setBounds(153, 121, 150, 25);
		panel.add(champ_Tel);
		
		prenomLBL.setBounds(25, 61, 70, 15);
		panel.add(prenomLBL);
		
		champ_Prenom = new JTextField();
		champ_Prenom.setBounds(154, 56, 150, 25);
		panel.add(champ_Prenom);
		
		lblNationalitee.setBounds(25, 92, 70, 15);
		panel.add(lblNationalitee);
		
		champ_Nationalite = new JTextField();
		champ_Nationalite.setBounds(154, 87, 150, 25);
		panel.add(champ_Nationalite);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verifierChamps() == 0) {
					Client client = new Client(champ_Nom.getText().toUpperCase(), champ_Prenom.getText(), champ_Nationalite.getText(), Integer.parseInt(champ_Age.getText()), champ_Tel.getText());
					champ_Nom.setText("");
					champ_Age.setText("");
					champ_Tel.setText("");
					champ_Prenom.setText("");
					champ_Nationalite.setText("");
					JOptionPane succesMessage = new JOptionPane();
					succesMessage.showMessageDialog(null, "Le client a été ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane alertMessage = new JOptionPane();
					alertMessage.showMessageDialog(null, "Erreur : Merci de remplir ou de vérifier tous les champs !","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnValider.setBounds(68, 244, 190, 54);
		panel.add(btnValider);

	}
	
	private int verifierChamps() {
		if (champ_Nom.getText().equals("") || champ_Nom.getText().matches("[0-9]+")) return -1;
		if (champ_Prenom.getText().equals("") || champ_Prenom.getText().matches("[0-9]+")) return -1;
		if (champ_Nationalite.getText().equals("") || champ_Nationalite.getText().matches("[0-9]+")) return -1;
		if (champ_Age.getText().equals("") || champ_Age.getText().matches("[0-9]+") == false) return -1;
		if (champ_Tel.getText().equals("") || champ_Tel.getText().matches("[0-9]+") == false) return -1;
	
		return 0;
	}
}