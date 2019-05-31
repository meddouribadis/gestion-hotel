package model;

/* En cas de problème aux niveau d'import veuillez importer dans le Build Path les fichiers les deux fichiers JAR présents dans le dossier lib à la racine !*/

import javax.swing.JPanel;
import controller.*;
import view.*;

public class main {

	public static void main(String[] args) {

		Home frame = new Home(); /* Création de la fenetre principale */
		
		/* Création de tous les controlleurs */
		Activer_panel controlleurChambres = new Activer_panel(frame, 4);
		Activer_panel controlleurClient = new Activer_panel(frame, 3);
		Activer_panel controlleurReservation = new Activer_panel(frame, 5);
		Activer_panel controlleurAccueil = new Activer_panel(frame, 1);
		
		Chambres_Ajouter Chambres_Ajouter = new Chambres_Ajouter(frame.panel_Chambre);
		Chambres_Raffraichir Chambres_Raffraichir = new Chambres_Raffraichir(frame.panel_Chambre);
		Chambres_Modifier Chambres_Modifier = new Chambres_Modifier(frame.panel_Chambre);
		Chambres_Supprimer Chambres_Supprimer = new Chambres_Supprimer(frame.panel_Chambre);
		
		Client_Ajouter Client_Ajouter = new Client_Ajouter(frame.panel_Client);
		Client_Raffraichir Client_Raffraichir = new Client_Raffraichir(frame.panel_Client);
		Client_Supprimer Client_Supprimer = new Client_Supprimer(frame.panel_Client);
		Client_Modifier Client_Modifier = new Client_Modifier(frame.panel_Client);
		
		Reservation_Ajouter Reservation_Ajouter = new Reservation_Ajouter(frame.panel_Reservation);
		Reservation_Raffraichir Reservation_Raffraichir = new Reservation_Raffraichir(frame.panel_Reservation);
		Reservation_Modifier Reservation_Modifier = new Reservation_Modifier(frame.panel_Reservation);
		Reservation_Supprimer Reservation_Supprimer = new Reservation_Supprimer(frame.panel_Reservation);
		
		/* Tous les controlleurs sont crées nous allons donc associer chaque controlleur à son bouton correspondant : */	
		
		/* Ajout des Controlleurs pour commun à tous les panels et fenêtres */
		frame.button_Accueil.addActionListener(controlleurAccueil);
		frame.bouton_Chambres.addActionListener(controlleurChambres);
		frame.boutton_Client.addActionListener(controlleurClient);
		frame.bouton_reservation.addActionListener(controlleurReservation);
		
		/* Ajout de tous les controlleurs du panel Chambre */
		frame.panel_Chambre.btnAddChambre.addActionListener(Chambres_Ajouter);
		frame.panel_Chambre.btnRaffraichir.addActionListener(Chambres_Raffraichir);
		frame.panel_Chambre.btnModifier.addActionListener(Chambres_Modifier);
		frame.panel_Chambre.btnSuppr.addActionListener(Chambres_Supprimer);
		
		/* Ajout de tous les controlleurs du panel Client */
		frame.panel_Client.btnAddClient.addActionListener(Client_Ajouter);
		frame.panel_Client.btnRaffraichir.addActionListener(Client_Raffraichir);
		frame.panel_Client.btnSuppr.addActionListener(Client_Supprimer);
		frame.panel_Client.btnModifier.addActionListener(Client_Modifier);
		
		/* Ajout de tous les controlleurs du panel Reservation */
		frame.panel_Reservation.btnNewButton.addActionListener(Reservation_Ajouter);
		frame.panel_Reservation.btnRaffraichir.addActionListener(Reservation_Raffraichir);
		frame.panel_Reservation.btnModifier.addActionListener(Reservation_Modifier);
		frame.panel_Reservation.btnSuppr.addActionListener(Reservation_Supprimer);
		
		/* La fenetre principale est totalement chargé ! On la rend visible : */
		frame.setVisible(true);

	}

}
