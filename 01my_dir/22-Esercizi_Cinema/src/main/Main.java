package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// Leggere da un file le informazioni riguardanti alcuni film proiettati al cinema
        // I dati saranno: titolo, genere, durata(minuti), annoUscita, vietatoMinori(bool)
        // Scrivere un menù che permetta all'utente le seguenti scelte:
        // - Schede complete dei singoli film
        // - Il titolo dei film antecedenti al 1992
        // - Il numero di film vietati ai minori
        // - Il titolo del film più lungo (o dei film)
        // - La durata media dei film di genere Azione
		
		//D
		Scanner file;
		Scanner input;
		String titolo;
		String genere;
		int durata;
		int annoUscita;
		boolean vietatoMinori;
		String cmd;
		
		String menu;
		String risposta;
		int numVietati;
		String titoloLungo;
		
		int filmAzione;
		int durataAzione;
		
		//I
		input = new Scanner(System.in);
		titolo = "";
		genere = "";
		durata = 0;
		annoUscita = 0;
		vietatoMinori = false;
		numVietati = 0;
		cmd = "";
		risposta = "";
		titoloLungo = "";
		filmAzione = 0;
		durataAzione = 0;
		
		menu = "Seleziona : \n1 - Schede complete dei singoli film\n"
				+ "2 - Il titolo dei film antecedenti al 1992\n"
				+ "3 - Il numero di film vietati ai minori\n"
				+ "4 - Il titolo del film più lungo (o dei film)\n"
				+ "5 - La durata media dei film di genere Azione\n"
				+ "6 - Per la legenda\n"
				+ "0 - Esci";
		System.out.println(menu);
		//C
		do
		{
			System.out.println("Commando: ");
			cmd = input.nextLine();
			file = new Scanner(new File("src/res/File.txt"));
			switch(cmd)
			{
				case "1":
					System.out.println("Schede complete dei singoli film: ");
					while (file.hasNextLine())
					{
						titolo = file.nextLine();
						genere = file.nextLine();
						durata = Integer.parseInt(file.nextLine());
						annoUscita = Integer.parseInt(file.nextLine());
						vietatoMinori = Boolean.parseBoolean(file.nextLine());
						
						risposta += "Titolo: " + titolo + "\n"
									+ "Genere: " + genere + "\n"
									+  "Durata(minuti): " + durata + "\n"
									+ "Anno Uscita: " + annoUscita + "\n"
									+ "Vietato Minori: " + (vietatoMinori ? "Si" : "No") + "\n"
									+ "--------------------------------------\n";
					}
					break;
				case "2":
					
					System.out.println("Il titolo dei film antecedenti al 1992: ");
					risposta = "";
					while (file.hasNextLine())
					{
						titolo = file.nextLine();
						genere = file.nextLine();
						durata = Integer.parseInt(file.nextLine());
						annoUscita = Integer.parseInt(file.nextLine());
						vietatoMinori = Boolean.parseBoolean(file.nextLine());
						
						if (annoUscita < 1992)
						{
							risposta += "Titolo: " + titolo + "\n"
									+ "Anno Uscita: " + annoUscita + "\n"
									+ "--------------------------------------\n";
						}
					}
					if (risposta.isEmpty())
						risposta = "Not found";
					break;
				case "3":
					System.out.println("Il numero di film vietati ai minori: ");
					risposta = "";
					while (file.hasNextLine())
					{
						titolo = file.nextLine();
						genere = file.nextLine();
						durata = Integer.parseInt(file.nextLine());
						annoUscita = Integer.parseInt(file.nextLine());
						vietatoMinori = Boolean.parseBoolean(file.nextLine());
						
						if (vietatoMinori == true)
							numVietati++;
					}
					risposta = "" + numVietati;
					break;
				case "4":
					System.out.println("Il titolo del film più lungo: ");
					risposta = "";
					while (file.hasNextLine())
					{
						titolo = file.nextLine();
						genere = file.nextLine();
						durata = Integer.parseInt(file.nextLine());
						annoUscita = Integer.parseInt(file.nextLine());
						vietatoMinori = Boolean.parseBoolean(file.nextLine());
						
						if (titoloLungo.contains(","))
						{
							if (titoloLungo.substring(0, titoloLungo.indexOf(",")).length() < titolo.length())
								titoloLungo = titolo;
						}
								
						else if (titoloLungo.length() < titolo.length())
							titoloLungo = titolo;
						else if (titoloLungo.length() ==  titolo.length())
							titoloLungo +=  ", " + titolo; 
					}
					risposta = titoloLungo;
					break;
				case "5":
					
					System.out.println("La durata media dei film di genere Azione: ");
					risposta = "";
					while (file.hasNextLine())
					{
						titolo = file.nextLine();
						genere = file.nextLine();
						durata = Integer.parseInt(file.nextLine());
						annoUscita = Integer.parseInt(file.nextLine());
						vietatoMinori = Boolean.parseBoolean(file.nextLine());
						
						if (genere.toLowerCase().contains("azione"))
						{
							filmAzione++;
							durataAzione += durata;
						}
					}
					risposta = "" + (durataAzione/filmAzione);
					break;
				case "6":
					risposta  =  menu;
					break;
				case "0":
					risposta  =  "Arriederci.!!\nEND";
					break;
				default:
					risposta = "Commando Inesistente";
			}
			file.close();
			//O
			System.out.println(risposta);

		}while (!cmd.equalsIgnoreCase("0"));
		
		input.close();
	}

}
