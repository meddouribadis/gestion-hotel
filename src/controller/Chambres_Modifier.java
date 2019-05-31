package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.*;

/* Ce controlleur est utilisé lorque l'utilisateur clique sur Modifier dans le Panel Chambre */

public class Chambres_Modifier implements ActionListener {
	
	Panel_Chambre pChambre = new Panel_Chambre();
	
	public Chambres_Modifier(Panel_Chambre pChambre) { /* On récupère le Panel où le Controlleur doit être affecté */
		this.pChambre = pChambre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		System.out.println("Vous avez cliquer sur modifier.");
		
		/* Ici on vérifie si l'utilisateur a bien sélectionné une Chambre dans le JTable du panel */
		
		int colonne = pChambre.obtenirLesInformations();
		
		if (colonne==-1) { /* Si ce n'est pas le cas il n'y a rien à modifier on affiche donc une erreur. */
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Erreur : Veuillez sélectionner la ligne que vous souhaitez modifier SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		
		else { /* Si l'utilisateur a bel et bien selectionné une chambre a modifier on récupère les informations de la chambre */
			String eid = pChambre.table.getModel().getValueAt(colonne, 0).toString(); /* On récupère le type de la Chambre */
			String pType = pChambre.table.getModel().getValueAt(colonne, 1).toString(); /* On récupère le type de la Chambre */
			String pEtat = pChambre.table.getModel().getValueAt(colonne, 2).toString(); /* On récupère l'état de la Chambre */
			String DateDeb = ""; /* On Affecte une valeure par défaut à Date de Debut, Date de Fin et ID Client car ces valeures peuvent être nulles */
			String DateFin = ""; 
			String pClient = "null"; 
			
			/* Puis on essaye de récupérer les valeurs qui peuvent être nulles. */
			try {
				pClient = pChambre.table.getModel().getValueAt(colonne, 4).toString();
			}
			catch(Exception e) {
			}
			
			try {
				DateDeb = pChambre.table.getModel().getValueAt(colonne, 5).toString();
			}
			catch(Exception e) {
			}
			
			try {
				DateFin = pChambre.table.getModel().getValueAt(colonne, 6).toString();
			}
			catch(Exception e) {
			}
			
			System.out.println("Voila les informations récoltés : "+eid+" "+pType+" "+pEtat+" "+pClient+" "+DateDeb+" "+DateFin);
			/* On créer la fenetre qui permet de modifier un client avec les informations recoltés */
			pChambre.frame = new Chambres_Update_Fenetre(eid, pType, pEtat, pClient, DateDeb, DateFin);
			pChambre.frame.setVisible(true);

	}

}
	
}
