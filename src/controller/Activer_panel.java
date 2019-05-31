package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.Home;

/*	Dans la fenêtre principale, lorsque l'utilisateur clique sur un bouton le panel affiché au centre change.
	Cette classe permet de controller les boutons afin de switcher entre les différents panel affichés à l'écran (Chambres, Reservations, Clients et Accueil) */

public class Activer_panel implements ActionListener {
	
	Home home = new Home();
	int numeroPanel;
	
	public Activer_panel(Home pHome, int numeroPanel) { /* Le int passé en deuxième paramètre permet de choisir quel panel activer */
		this.home = pHome;
		this.numeroPanel = numeroPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(numeroPanel == 4) {  /* Lorsque l'utilisateur passe 4 en second paramètre le Panel_Chambre est activé dans la fenetre principale */
			home.panel_Centrale.setVisible(true); /* Je réinitialise tous les autres panels afin qu'il ne soit plus affichés. */
			home.panel_Centrale.setVisible(false);
			home.panel_Centrale.repaint();
			home.panel_Client.setVisible(true);
			home.panel_Client.setVisible(false);
			home.panel_Client.repaint();
			home.panel_Reservation.setVisible(true);
			home.panel_Reservation.setVisible(false);
			home.panel_Reservation.repaint();
			home.panel_Chambre.setVisible(true); /* Enfin j'active le panel voulu */
			home.Conteneur.add(home.panel_Chambre); /* Je l'ajoute au principal conteneur. */
			System.out.println("Bouton Chambre cliqué : Le panel Chambre a donc été activé !");
		}
		
		/* C'est exactement le même fonctionnement pour les autres panels ci-dessous */
		
		else if(numeroPanel == 3) {
			home.panel_Centrale.setVisible(true);
			home.panel_Centrale.setVisible(false);
			home.panel_Centrale.repaint();
			home.panel_Chambre.setVisible(true);
			home.panel_Chambre.setVisible(false);
			home.panel_Chambre.repaint();
			home.panel_Reservation.setVisible(true);
			home.panel_Reservation.setVisible(false);
			home.panel_Reservation.repaint();
			home.panel_Client.setVisible(true);
			home.Conteneur.add(home.panel_Client);
			System.out.println("Bouton Client cliqué : Le panel Client a donc été activé !");
		}
		
		else if(numeroPanel == 5) {
			home.panel_Centrale.setVisible(true);
			home.panel_Centrale.setVisible(false);
			home.panel_Centrale.repaint();
			home.panel_Chambre.setVisible(true);
			home.panel_Chambre.setVisible(false);
			home.panel_Chambre.repaint();
			home.panel_Client.setVisible(true);
			home.panel_Client.setVisible(false);
			home.panel_Client.repaint();
			home.panel_Reservation.setVisible(true);
			home.Conteneur.add(home.panel_Reservation);
			System.out.println("Bouton Reservations cliqué : Le panel Reservations a donc été activé !");
		}
		
		else if(numeroPanel == 1) {
			home.panel_Centrale.setVisible(true);
			home.panel_Centrale.setVisible(false);
			home.panel_Centrale.repaint();
			home.panel_Chambre.setVisible(true);
			home.panel_Chambre.setVisible(false);
			home.panel_Chambre.repaint();
			home.panel_Client.setVisible(true);
			home.panel_Client.setVisible(false);
			home.panel_Client.repaint();
			home.panel_Reservation.setVisible(true);
			home.panel_Reservation.setVisible(false);
			home.panel_Reservation.repaint();
			home.panel_Centrale.setVisible(true);
			home.Conteneur.add(home.panel_Centrale);
			System.out.println("Retour à l'accueil");
		}
	}

}
