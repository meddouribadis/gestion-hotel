package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Client;
import view.Client_Ajouter_Fenetre;
import view.Client_Update_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Client;
import view.Panel_Reservation;

/* Ce controlleur gère le bouton supprimer dans Client */

public class Client_Supprimer implements ActionListener {
	
	Panel_Client pClient = new Panel_Client();
	Client client = new Client();
	
	public Client_Supprimer(Panel_Client pClient) {
		this.pClient = pClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		int ligne = pClient.obtenirLigne();
		if (ligne==-1) {
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Veuillez sélectionner la ligne que vous souhaitez supprimer SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		else {
			/* On récupère l'ID et le Nom du Client selectionné par l'utilisateur */
			String value = pClient.table.getModel().getValueAt(ligne, 0).toString();
			String naming = pClient.table.getModel().getValueAt(ligne, 1).toString();
			System.out.println(value);
			
			/* On demande confirmation */
			int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimez "+naming+" (ID : "+value+" ) ?", "Confirmez votre action", JOptionPane.YES_NO_OPTION);

			if (confirmation == 0) {
				client.supprimerUnClient(value); /* On invoque la fonction supprimer de l'objet client avec en paramètre l'ID de l'utilisateur qu'on veut supprimer */
				pClient.actualiserJTABLE();
			}
		}

	}
	
}
