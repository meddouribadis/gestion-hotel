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

/* Cette classe contrôle le bouton Ajouter du panel Client */

public class Client_Ajouter implements ActionListener {
	

	Panel_Client pClient = new Panel_Client();
	
	public Client_Ajouter(Panel_Client pClient) { /* Le deuxième paramètre permet de choisir quel panel activer */
		this.pClient = pClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		pClient.adder = new Client_Ajouter_Fenetre();
		pClient.adder.setVisible(true);

	}

}
