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

/* Ce controlleur est utilis� lorque l'utilisateur clique sur Modifier dans le Panel Client */

public class Client_Modifier implements ActionListener {
	
	Panel_Client pClient = new Panel_Client();
	Client client = new Client();
	
	public Client_Modifier(Panel_Client pClient) { /* Le deuxi�me param�tre permet de choisir quel panel activer */
		this.pClient = pClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		int ligne = pClient.obtenirLigne();
		if (ligne == -1) {
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Veuillez s�lectionner la ligne que vous souhaitez modifier SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		else { /* On r�cup�re toutes les informations dans chaque colonne de la ligne selectionn�e */
			String selectionID = pClient.table.getModel().getValueAt(ligne, 0).toString(); /* On r�cup�re l'ID qui nous servira pour la requ�te SQL */
			String selectionNom = pClient.table.getModel().getValueAt(ligne, 1).toString();
			String selectionPrenom = pClient.table.getModel().getValueAt(ligne, 2).toString();
			String selectionNation = pClient.table.getModel().getValueAt(ligne, 3).toString();
			String selectionTel = pClient.table.getModel().getValueAt(ligne, 4).toString();
			String selectionAge = pClient.table.getModel().getValueAt(ligne, 5).toString();
			System.out.println(selectionID + " " + selectionNom + " " + selectionPrenom + " " +selectionNation);
			
			/* Les informations on �t� r�cuper�s : Nous pouvons lancer la fenetre de mise � jour Client */
	        pClient.frame = new Client_Update_Fenetre(selectionID, selectionNom, selectionPrenom, selectionNation, selectionTel, selectionAge);
	        pClient.frame.setVisible(true);
		}	

	}
	
}
