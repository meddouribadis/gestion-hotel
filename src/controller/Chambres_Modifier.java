package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.*;

/* Ce controlleur est utilis� lorque l'utilisateur clique sur Modifier dans le Panel Chambre */

public class Chambres_Modifier implements ActionListener {
	
	Panel_Chambre pChambre = new Panel_Chambre();
	
	public Chambres_Modifier(Panel_Chambre pChambre) { /* On r�cup�re le Panel o� le Controlleur doit �tre affect� */
		this.pChambre = pChambre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		System.out.println("Vous avez cliquer sur modifier.");
		
		/* Ici on v�rifie si l'utilisateur a bien s�lectionn� une Chambre dans le JTable du panel */
		
		int colonne = pChambre.obtenirLesInformations();
		
		if (colonne==-1) { /* Si ce n'est pas le cas il n'y a rien � modifier on affiche donc une erreur. */
			JOptionPane alertMessage = new JOptionPane();
			alertMessage.showMessageDialog(null, "Erreur : Veuillez s�lectionner la ligne que vous souhaitez modifier SVP !","Info",JOptionPane.WARNING_MESSAGE);
		}
		
		else { /* Si l'utilisateur a bel et bien selectionn� une chambre a modifier on r�cup�re les informations de la chambre */
			String eid = pChambre.table.getModel().getValueAt(colonne, 0).toString(); /* On r�cup�re le type de la Chambre */
			String pType = pChambre.table.getModel().getValueAt(colonne, 1).toString(); /* On r�cup�re le type de la Chambre */
			String pEtat = pChambre.table.getModel().getValueAt(colonne, 2).toString(); /* On r�cup�re l'�tat de la Chambre */
			String DateDeb = ""; /* On Affecte une valeure par d�faut � Date de Debut, Date de Fin et ID Client car ces valeures peuvent �tre nulles */
			String DateFin = ""; 
			String pClient = "null"; 
			
			/* Puis on essaye de r�cup�rer les valeurs qui peuvent �tre nulles. */
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
			
			System.out.println("Voila les informations r�colt�s : "+eid+" "+pType+" "+pEtat+" "+pClient+" "+DateDeb+" "+DateFin);
			/* On cr�er la fenetre qui permet de modifier un client avec les informations recolt�s */
			pChambre.frame = new Chambres_Update_Fenetre(eid, pType, pEtat, pClient, DateDeb, DateFin);
			pChambre.frame.setVisible(true);

	}

}
	
}
