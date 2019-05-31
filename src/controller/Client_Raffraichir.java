package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Client_Ajouter_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Client;
import view.Panel_Reservation;

/* Controle le bouton Raffraichir du panel Client */

public class Client_Raffraichir implements ActionListener {
	

	Panel_Client pClient = new Panel_Client();
	
	public Client_Raffraichir(Panel_Client pClient) {
		this.pClient = pClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		pClient.actualiserJTABLE();

	}

}
