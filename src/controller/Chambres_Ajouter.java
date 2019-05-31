package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Chambres_Ajouter_Fenetre;
import view.Client_Ajouter_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Ce controlleur est activé lorsque l'utisateur appuie sur le bouton Ajouter dans le Panel Chambre */

public class Chambres_Ajouter implements ActionListener {
	

	Panel_Chambre pChambre = new Panel_Chambre();
	
	public Chambres_Ajouter(Panel_Chambre pChambre) { 
		this.pChambre = pChambre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/* L'utilisateur à décider d'ajouter une Chambre, on lance alors la fenêtre et on la rend visible */
		pChambre.adder = new Chambres_Ajouter_Fenetre();
		pChambre.adder.setVisible(true);

	}

}
