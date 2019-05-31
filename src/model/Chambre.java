package model;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import view.Client_Ajouter_Fenetre;

	/* La classe Client */

public class Chambre {
	
	public int ID;
	private String typeChambre = new String();
	private String etatChambre = "L";
	private String nomClient = new String();
	private String dateDebut = new String();
	private String dateFin = new String();
	private String clientID;
	
	public Chambre(String pType, String pEtat, String pNomClient, String dateDeb, String dateFin, String pClientID) { /* Ce constructeur permet de créer un Client (en objet + en BDD) */
		this.typeChambre = pType;
		this.etatChambre = pEtat;
		this.nomClient = pNomClient;
		this.dateDebut = dateDeb;
		this.dateFin = dateFin;
		this.clientID = pClientID;
		enregistre_Sur_la_BDD();

	}
	
	public Chambre() { /* Ce constructeur permet d'utiliser les fonctions de gestion des clients déjà existant sans en créer un */
		
	}

	private void enregistre_Sur_la_BDD() {
		Connection connecteur_client = sqlConnect.dbConnector("chambre.sqlite");
		String query="insert into chambres (Type,Etat,NClient,IDClient,DateDebut,DateFin) values (?,?,?,?,?,?)";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connecteur_client.prepareStatement(query);
			pst.setString(1, typeChambre);
			pst.setString(2, etatChambre);
			pst.setString(3, nomClient);
			pst.setString(4, clientID+"");
			pst.setString(5, dateDebut);
			pst.setString(6, dateFin);
			pst.executeUpdate();
			pst.close();
			//Récuperation de l'ID créer lors de l'écriture dans la BDD pour l'assigner à l'objet.
			query= "SELECT id  FROM chambres ORDER BY id DESC LIMIT 1"; /* Je prends la dernière valeure inscrite dans la bdd */
			pst = connecteur_client.prepareStatement(query);
			rs = pst.executeQuery();
			this.ID = rs.getInt( "ID" );
			rs.close();
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
	
	
	public int supprimerUneChambre(String ID){ /* Fonction qui permet de supprimer une chambre */
		Connection connecteur_bdd_client = sqlConnect.dbConnector("chambre.sqlite");
		String query="DELETE FROM chambres where ID =?;"; /* Suppresion de la chambre identifié par l'ID */
		PreparedStatement pst = null;
		
		try {
			pst = connecteur_bdd_client.prepareStatement(query); /* On execute la requête SQL */
			pst.setString(1, ID); /* Remplace le 1er "?" dans la requête par la variable ID */
			pst.execute();
			pst.close();
			System.out.println("Supression avec succès");
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
				return -1;
			}
		}
		
	}
	
	public int modifierUneChambre(String kID, String Type, String Etat, String NClient, String DateDeb, String DateFin){
		Connection connecteur_bdd_client = sqlConnect.dbConnector("chambre.sqlite");
		PreparedStatement pst = null;
		
		try {
			String query="UPDATE chambres SET Type= '"+Type+"', Etat= '"+Etat+"', NClient= '"+NClient+"', DateDebut= '"+DateDeb+"', DateFin= '"+DateFin+"' WHERE ID = "+kID+";";
			pst = connecteur_bdd_client.prepareStatement(query);
			pst.execute();
			pst.close();
			System.out.println("Modification de la chambre ayant pour ID : "+kID+" avec succès");
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
