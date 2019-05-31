package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class NomGerant {
	String nom = new String();
	String pathtofile = new String();
	String prenom = new String();
	String nomComplet = new String();

	public NomGerant() {
		this.pathtofile = System.getProperty("user.dir");
	}

	public String getBossName() {
		try{
		FileInputStream fs = new FileInputStream(pathtofile+"/config_files/boss.ini"); /* Le nom du gérant est stocké dans un fichier ini en forme de texte sur deux lignes */
		BufferedReader buffer = new BufferedReader(new InputStreamReader(fs)); /* On ouvre buffer qui va lire le fichier ouver grâce à fs */
		nom = buffer.readLine();	/* On lit la première ligne du fichier et on la stocke dans la variable nom */
		prenom = buffer.readLine(); /* On lit la première ligne du fichier et on la stocke dans la variable prenom */
		nomComplet = nom + " " + prenom;
		
		if (nom.equals("non") || prenom.equals("non")){ /* Par defaut le fichier est composé de non, on lance alors la procédure d'enregistrement */
			enregistrement();
			getBossName();
		}
		
		System.out.println("Bienvenue "+nom+" !");			
		buffer.close();
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return this.nomComplet;

	}
	
	private void enregistrement() {
		this.nom = JOptionPane.showInputDialog("Quel est votre Nom de Famille ?");
		this.prenom = JOptionPane.showInputDialog("Quel est votre Prénom ?");
		
		try {
			PrintWriter saver = new PrintWriter(pathtofile+"/config_files/boss.ini");							
			saver.println(this.nom);
			saver.println(this.prenom);
			saver.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
