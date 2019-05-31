package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Client_Ajouter_Fenetre;
import view.Home;
import view.Reservations_Ajouter_Fenetre;
import view.Reservations_Update_Fenetre;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Permet de contrôler le bouton modifier dans le panel Reservation */

public class Reservation_Modifier implements ActionListener {

	private Panel_Reservation pReservation = new Panel_Reservation();
	
	public Reservation_Modifier(Panel_Reservation pReservation) { /* Le deuxième paramètre permet de choisir quel panel activer */
		this.pReservation = pReservation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		int ligne = pReservation.obtenirLaLigne();
		if (ligne==-1) {
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Veuillez sélectionner la ligne que vous souhaitez modifier SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		else {
			String eID = pReservation.table.getModel().getValueAt(ligne, 0).toString();
			String ID_Client = pReservation.table.getModel().getValueAt(ligne, 1).toString();
			String ID_Chambre = pReservation.table.getModel().getValueAt(ligne, 3).toString();
			String DateDeb = pReservation.table.getModel().getValueAt(ligne, 4).toString();
			String DateFin = pReservation.table.getModel().getValueAt(ligne, 5).toString();
			pReservation.updater = new Reservations_Update_Fenetre(eID, ID_Client, ID_Chambre, DateDeb, DateFin);
			pReservation.updater.setVisible(true);
		}				

	}

}
