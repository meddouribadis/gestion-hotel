package model;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import view.Client_Ajouter_Fenetre;

public class Client {
	
	private String nom = new String();
	private String prenom = new String();
	private String nationalitee = new String();
	private String numTel = new String();
	private int age, ID;

	public Client(String pNom, String pPrenom, String pNationalite, int pAge, String pNumTel) { /* Ce constructeur permet de créer un Client (en objet + en BDD) */
		this.nom = pNom;
		this.prenom = pPrenom;
		this.nationalitee = pNationalite;
		this.age = pAge;
		this.numTel = pNumTel;
		enregistre_Sur_la_BDD();
	}
	
	public Client() { /* Ce constructeur permet d'utiliser les fonctions de gestion des clients déjà existant sans en créer un */
		
	}

	private void enregistre_Sur_la_BDD() {
		Connection connecteur_client = sqlConnect.dbConnector("client.sqlite");
		String query="insert into client (Nom,Prenom,Nationalite,numero_tel,age) values (?,?,?,?,?)";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connecteur_client.prepareStatement(query);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setString(3, nationalitee);
			pst.setString(4, numTel);
			pst.setString(5, age + "");
			pst.executeUpdate();
			pst.close();
			System.out.println("Ecriture du client " +nom+ " " +prenom+ " avec succès !");
			//Récuperation de l'ID créer lors de l'écriture dans la BDD pour l'assigner à l'objet.
			query= "SELECT id  FROM client ORDER BY id DESC LIMIT 1"; /* Je prends la dernière valeure */
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
	
	
	public void supprimerUnClient(String ID){
		Connection connecteur_bdd_client = sqlConnect.dbConnector("client.sqlite");
		String query="DELETE FROM client where ID =?;";
		PreparedStatement pst = null;
		
		try {
			pst = connecteur_bdd_client.prepareStatement(query);
			pst.setString(1, ID);
			pst.execute();
			pst.close();
			System.out.println("Supression avec succès");
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
	
	public int modifierUnClienbt(String kID, String pNom, String pPrenom, String pNationalite, String pAge, String pNumTel){
		Connection connecteur_bdd_client = sqlConnect.dbConnector("client.sqlite");
		PreparedStatement pst = null;
		String pathtofile = System.getProperty("user.dir");
		
		try {
			String query="UPDATE client SET Nom= '"+pNom+"', Prenom= '"+pPrenom+"', Nationalite= '"+pNationalite+"', numero_tel= '"+pNumTel+"', age= '"+pAge+"' WHERE ID = "+kID+";";
			pst = connecteur_bdd_client.prepareStatement(query);
			pst.execute();
			pst.close();
			System.out.println("Modification de "+pNom+" "+pPrenom+" avec succès");
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
