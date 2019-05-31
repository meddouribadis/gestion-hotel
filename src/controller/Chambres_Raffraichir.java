package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Client_Ajouter_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Cette classe permet de controller le bouton Raffraichir du Panel Chambre */

public class Chambres_Raffraichir implements ActionListener {
	
	Panel_Chambre pChambre = new Panel_Chambre();
	
	public Chambres_Raffraichir(Panel_Chambre pChambre) { /* On récupère le Panel où le Controlleur doit être affecté */
		this.pChambre = pChambre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		pChambre.actualiserJTABLE();

	}

}
