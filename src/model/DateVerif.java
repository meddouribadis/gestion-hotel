package model;

public class DateVerif {
	
	/* Cette classe permet de v�rifier les dates pour qu'elles soit conformes � travers les diff�rentes base de don�es et objets
	 	Toutes les dates du programme poss�dent comme format JJ/MM/AAAA */
	
	private String dateFournie = new String();
	
	public DateVerif() {

	}
	
	public int verifierLaDate(String pDate) {
		this.dateFournie = pDate;
		
		if(dateFournie.length() == 0) { /* Si la date est vide on renvoie 2 */
			return -2;
		}
		
		else if(dateFournie.length() != 10) { /* N'importe quelle date doit faire 10 caract�res pas un de moins pas un de plus */
			return -1;						/* Donc si la taille est diff�rente, : la date n'est pas valide, on renvoie -1 ! */
		}
		
		
		if(dateFournie.charAt(2) != '/' && dateFournie.charAt(5) != '/'){ /* On v�rifie si il y'a les deux slash s�parateurs */
			return -1;
		}

		
		for(int i = 0; i < dateFournie.length(); i++){
			if (Character.isDigit(dateFournie.charAt(i)) || i == 2 || i == 5) { /* On v�rifie si tous les autres caract�res sont des chiffres */
				System.out.println("C'est ok pour la "+i+" ! ");
			}

			else return -1;
		}
		
		System.out.println("La date est correcte ! ");

		return 0;
	}

}
