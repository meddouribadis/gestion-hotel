package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Chambre;
import view.Client_Ajouter_Fenetre;
import view.Client_Update_Fenetre;
import view.Home;
import view.Panel_Chambre;
import view.Panel_Reservation;

/* Ce controlleur g�re le bouton supprimer dans Chambre */

public class Chambres_Supprimer implements ActionListener {
	
	Panel_Chambre pChambre = new Panel_Chambre();
	Chambre chambre = new Chambre();
	
	public Chambres_Supprimer(Panel_Chambre pChambre) {
		this.pChambre = pChambre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		System.out.println("Vous avez cliquer sur Supprimer.");
		
		/* Ici on v�rifie si l'utilisateur a bien s�lectionn� une Chambre dans le JTable du panel */
		
		int colonne = pChambre.obtenirLesInformations();
		
		if (colonne==-1) { /* Si ce n'est pas le cas il n'y a rien � modifier on affiche donc une erreur. */
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Erreur : Veuillez s�lectionner la ligne que vous souhaitez supprimer SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			String value = pChambre.table.getModel().getValueAt(colonne, 0).toString(); /* On r�cup�re l'ID de la chambre a supprim� (positionn� en 0 qui correspond � la premier colonne */
			String naming = pChambre.table.getModel().getValueAt(colonne, 1).toString();
			System.out.println(value);
			
			/* On demande une confirmation � l'utilisateur */
			int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimez "+naming+" (ID : "+value+" ) ?", "Confirmez votre action", JOptionPane.YES_NO_OPTION);

			if (confirmation == 0) {
				if (chambre.supprimerUneChambre(value) == 0) {
					System.out.println("Supression avec succ�s");
					pChambre.actualiserJTABLE();
				}
			}
		}

}
	
}
