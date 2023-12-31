package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//Scrivere un file con le informazioni legate alle scommesse sui cavalli
		// il file sara' formattatto con nome, numero, quotato, razza
		// Stampare poi un menu' che permetta all'utente di chiedere:
		// - Lista dei cavalli del file
		// - Lista delle razze senza ripetizioni
		// - Cavallo/i piu' quotato/i
		// - Quota media dei cavelli di razza a richiesta dell'utente

		//D
		Scanner file;
		String percorso;
		Scanner input;
		String nome;
		int numero;
		double quotato;
		String razza;
		String risposta;
		String legenda;
		String cmd;
		
		String schedeDeiCavalli;
		String listaDelleRazzeSenzaRipetizioni;
		double quotazioneMassima;
		String cavalloQuotazioneMassima;
		String razzaRichiestaUtente;
		
		int razzaRichiestaNum;
		double sumQuotaRazzaRichiestaUtente;
		
		
		//I
		percorso = "src/res/Dati.txt";
		file = new Scanner(new File(percorso));
		input = new Scanner (System.in);
		nome = "";
		numero = 0;
		quotato = 0;
		razza = "";
		risposta = "";
		schedeDeiCavalli = "";
		cmd = "";
		listaDelleRazzeSenzaRipetizioni = ", ";
		quotazioneMassima = Double.MIN_VALUE;
		cavalloQuotazioneMassima = "";
		razzaRichiestaNum = 0;
		sumQuotaRazzaRichiestaUtente = 0.0;
		
		legenda = "Seleziona: \n"
				+ "1 - Lista dei cavalli del file\n"
				+ "2 - Lista delle razze senza ripetizioni\n"
				+ "3 - Cavallo/i piu' quotato/i\n"
				+ "4 - Quota media dei cavelli di razza a richiesta\n"
				+ "5 - Per stampare la legenda\n"
				+ "0 - Esci";
		System.out.println(legenda);
		//C
		
		while (file.hasNextLine())
		{
			nome = file.nextLine();
			numero = Integer.parseInt(file.nextLine());
			quotato = Double.parseDouble(file.nextLine());
			razza = file.nextLine();
			
			schedeDeiCavalli += "Nome: " + nome
								+ "\nNumero: " + numero
								+ "\nQuotato: " + quotato
								+ "\nRazza: " + razza
								+ "\n--------------------------------------------\n";
			
			if (!listaDelleRazzeSenzaRipetizioni.toLowerCase().contains(", " + razza.toLowerCase() + ", "))
			{
				listaDelleRazzeSenzaRipetizioni += razza + ", ";
			}
			
			if (quotato > quotazioneMassima)
			{
				quotazioneMassima = quotato;
				cavalloQuotazioneMassima = nome;
			}
			else if (quotato == quotazioneMassima)
			{
				cavalloQuotazioneMassima += ", " + nome;
			}
		}
		file.close();
		listaDelleRazzeSenzaRipetizioni = 
				listaDelleRazzeSenzaRipetizioni.substring
				(2, listaDelleRazzeSenzaRipetizioni.length() - 2);
		do 
		{
			System.out.println("Commando: ");
			cmd = input.nextLine();
			switch(cmd)
			{
				case "1":
					risposta = schedeDeiCavalli;
					break;
				case "2":
					risposta = listaDelleRazzeSenzaRipetizioni;
					break;
				case "3":
					risposta = cavalloQuotazioneMassima;
					break;
				case "4":
					System.out.println("Scrivi la razza: ");
					razzaRichiestaUtente = input.nextLine();
					file = new Scanner(new File(percorso));
					razzaRichiestaNum = 0;
					sumQuotaRazzaRichiestaUtente = 0.0;
					while(file.hasNextLine())
					{
						file.nextLine();
						file.nextLine();
						quotato = Double.parseDouble(file.nextLine());
						razza = file.nextLine();
						
						if (razza.equalsIgnoreCase(razzaRichiestaUtente))
						{
							razzaRichiestaNum++;
							sumQuotaRazzaRichiestaUtente += quotato;
						}
					}
					file.close();
					if (razzaRichiestaNum == 0)
					{
						risposta = "Razza does not exist";
					}
					else
						risposta = "Quota media dei cavelli di razza a richiesta: " + (sumQuotaRazzaRichiestaUtente/razzaRichiestaNum);
					break;
				case "5":
					risposta = legenda;
					break;
				case "0":
					risposta = "Arrivederci";
					break;
				default:
					risposta = "commando inesistente";
			}
			//O
			System.out.println(risposta);
		}while(!cmd.equals("0"));
		
		input.close();
		System.out.println("END");
	}

}
