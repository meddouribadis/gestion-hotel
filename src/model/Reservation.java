package model;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sqlite.SQLiteException;

import view.Client_Ajouter_Fenetre;

public class Reservation {
	
	public int ID;
	public Boolean erreur;
	private String IDClient = new String();
	private String nomClient = new String();
	private String IDChambre = new String();
	private String dateDebut = new String();
	private String dateFin = new String();
	
	public Reservation(String pClientID, String pNomClient, String pChambreID, String dateDeb, String dateFin) { /* Ce constructeur permet de cr�er une Reservation (en objet + en BDD) */
		this.IDClient = pClientID;
		this.nomClient = pNomClient;
		this.IDChambre = pChambreID;
		this.dateDebut = dateDeb;
		this.dateFin = dateFin;
		enregistre_Sur_la_BDD();
	}
	
	public Reservation() { /* Ce constructeur permet d'utiliser les fonctions de gestion des r�servations d�j� existantes sans en cr�er une */
		
	}

	private void enregistre_Sur_la_BDD() {
		Connection connecteur_client = sqlConnect.dbConnector("reservation.sqlite");
		String query="insert into reservation (IDClient,NomClient,Chambre,DateDebut,DateFin) values (?,?,?,?,?)";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connecteur_client.prepareStatement(query);
			pst.setString(1, IDClient);
			pst.setString(2, nomClient);
			pst.setString(3, IDChambre);
			pst.setString(4, dateDebut);
			pst.setString(5, dateFin);
			pst.executeUpdate();
			pst.close();
			//R�cuperation de l'ID cr�er lors de l'�criture dans la BDD pour l'assigner � l'objet.
			query= "SELECT id FROM reservation ORDER BY id DESC LIMIT 1"; /* Je prends la derni�re valeure */
			pst = connecteur_client.prepareStatement(query);
			rs = pst.executeQuery();
			this.ID = rs.getInt( "ID");
			rs.close();
			erreur = false;
		}
		
		catch(SQLiteException se) {
			se.printStackTrace();
			erreur = true;
			System.out.println(se.getClass().getSimpleName());
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getSimpleName());
		}
		
		finally {
			try {
			rs.close();
			pst.close();
			connecteur_client.close();
			}
			catch (Exception e) {
				
			}
		}
	}
	
	
	public void supprimerUneReservation(String ID){
		Connection connecteur_bdd_client = sqlConnect.dbConnector("reservation.sqlite");
		String query="DELETE FROM reservation where ID =?;";
		PreparedStatement pst = null;
		
		try {
			pst = connecteur_bdd_client.prepareStatement(query);
			pst.setString(1, ID);
			pst.execute();
			pst.close();
			System.out.println("Supression avec succ�s");
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getSimpleName());
		}
		
		finally {
			try {
			pst.close();
			connecteur_bdd_client.close();
			}
			catch (Exception e) {
				
			}
		}
		
	}
	
	public int modifierUneReservation(String kID, String IDClient, String NomClient, String IDChambre, String dDebut, String dFin){
		Connection connecteur_bdd_client = sqlConnect.dbConnector("reservation.sqlite");
		PreparedStatement pst = null;
		
		try {
			String query="UPDATE reservation SET IDClient= '"+IDClient+"', NomClient= '"+NomClient+"', Chambre= '"+IDChambre+"', DateDebut= '"+dDebut+"', DateFin= '"+dFin+"' WHERE ID = "+kID+";";
			pst = connecteur_bdd_client.prepareStatement(query);
			pst.execute();
			pst.close();
			System.out.println("Modification de la reservation ayant pour ID : "+kID+" avec succ�s");
			return 0;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getSimpleName());
			return -1;
		}
		
		finally {
			try {
			pst.close();
			connecteur_bdd_client.close();
			}
			catch (Exception e) {
				
			}
		}
		
	}
	
}
