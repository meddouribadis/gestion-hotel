package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Reservation;
import view.Client_Ajouter_Fenetre;
import view.Client_Update_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Cette classe permet de switcher entre les différents panel affichés à l'écran (Chambres, Reservations, Clients) */

public class Reservation_Supprimer implements ActionListener {
	
	Panel_Reservation pReservation = new Panel_Reservation();
	Reservation reservation = new Reservation();
	
	public Reservation_Supprimer(Panel_Reservation pReservation) {
		this.pReservation = pReservation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		System.out.println("Vous avez cliquer sur Supprimer.");
		int ligne = pReservation.obtenirLaLigne();
		if (ligne==-1) {
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Erreur : Veuillez sélectionner la ligne que vous souhaitez supprimer SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		else {
			String value = pReservation.table.getModel().getValueAt(ligne, 0).toString();
			String naming = pReservation.table.getModel().getValueAt(ligne, 1).toString();
			System.out.println(value);
			
			int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimez "+naming+" (ID : "+value+" ) ?", "Confirmez votre action", JOptionPane.YES_NO_OPTION);

			if (confirmation == 0) {
				reservation.supprimerUneReservation(value);
				pReservation.actualiserJTABLE();
			}
		}

	}
	
}
