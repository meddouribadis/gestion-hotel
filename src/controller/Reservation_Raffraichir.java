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

/* Controle le bouton Raffraichir du panel Reservation */

public class Reservation_Raffraichir implements ActionListener {
	

	Panel_Reservation pReservation = new Panel_Reservation();
	
	public Reservation_Raffraichir(Panel_Reservation pReservation) {
		this.pReservation = pReservation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		pReservation.actualiserJTABLE();

	}

}