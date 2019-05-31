package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Client_Ajouter_Fenetre;
import view.Home;
import view.Reservations_Ajouter_Fenetre;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Cette classe contrôle le bouton Ajouter du Panel Reservation */

public class Reservation_Ajouter implements ActionListener {

	private Panel_Reservation pReservation = new Panel_Reservation();
	
	public Reservation_Ajouter(Panel_Reservation pReservation) {
		this.pReservation = pReservation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		pReservation.adder = new Reservations_Ajouter_Fenetre();
		pReservation.adder.setVisible(true);

	}

}
