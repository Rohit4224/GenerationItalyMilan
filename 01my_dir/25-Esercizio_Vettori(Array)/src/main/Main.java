package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws FileNotFoundException
	{
//		Scrivere un file che contenga i prodotti venduti da un bar.
//		Nel file troveremo: nomeOrdine, prezzo
//		Scrivere un programma che apra un menù all'utente in cui si vede:
//		Menù del bar
//		Il nome dei prodotti più economici
//		Il costo medio di tutti i prodotti del bar
//		Il costo medio dei prodotti che contengono la parola caffe
//		Permettere all'utente di ordinare un prodotto
		//D
		Scanner file;
		String percorso;
		String prodotti[];
		double prezzi[];
		int dimension; // prima riga del file
		int i;
		
		//I
		percorso = "src/res/Dati.txt";
		file = new Scanner(new File(percorso));
		
		dimension = Integer.parseInt(file.nextLine());
		
		prodotti = new String[dimension];
		prezzi = new double[dimension];
		
		i = 0;
		
		while (file.hasNextLine()) // while (i < dimension)
		{
			prodotti[i] = file.nextLine();
			prezzi[i] = Double.parseDouble(file.nextLine());
			i++;
		}
		
		file.close();
		
		
		//D
		
		Scanner input;
		String legenda;
		String cmd;
		String risposta;
		
		// I
		
		input = new Scanner(System.in);
		cmd = "";
		risposta = "";
		legenda = "Seleziona: \n" +
				"1 - Menù del bar\n"
				+ "2 - Il nome dei prodotti più economici\n"
				+ "3 - Il costo medio di tutti i prodotti del bar\n"
				+ "4 - Il costo medio dei prodotti che contengono la parola caffe\n"
				+ "5 - Permettere all'utente di ordinare un prodotto\n"
				+ "6 - Legenda\n"
				+ "0 - Esci\n";
		//C
		System.out.println("Benvenuto.\n" + legenda);
		
		do
		{
			System.out.println("Commando: ");
			cmd = input.nextLine();
			switch(cmd)
			{
				case "1":
					risposta = "";
					i = 0;
					while (i < prodotti.length)
					{
						risposta += prodotti[i] + "-------->" + prezzi[i] + "\n";
						i++;
					}
					if (risposta.isEmpty())
						risposta = "Nessun menu e' disponibile";
					else
						risposta = "\t Menu\n" + risposta; 
					break;
				case "2":
					double min = Double.MAX_VALUE;
					risposta = "Il nome dei prodotti più economici: \n";
					i = 0;
					while (i < prezzi.length)
					{
						if (prezzi[i] < min)
						{
							min = prezzi[i];
							risposta += prodotti[i];
						}
						else if (prezzi[i] == min)
							risposta += ", " + prodotti[i];
						i++;
					}
					break;
				
				case "3":
					risposta = "Il costo medio di tutti i prodotti del bar: \n";
					double TotPrezzi = 0.0;
					i = 0;
					while (i < prezzi.length)
					{
						TotPrezzi += prezzi[i++];
					}
					risposta += TotPrezzi/dimension;
					break;
				case "4":
					risposta = "Il costo medio dei prodotti che contengono la parola caffe: \n";
					double costo = 0.0;
					int count = 0;
					i = 0;
					while(i < prodotti.length)
					{
						if (prodotti[i].toLowerCase().contains("caffe"))
						{
							count++;
							costo += prezzi[i];
						}
						i++;
					}
					risposta += "" + costo/count;
					break;
				case "5":
					risposta = "";
					System.out.println("Scrivi prodotto: ");
					String prodottoRichiesta = input.nextLine();
					i = 0;
					while (i < prezzi.length)
					{
						if (prodotti[i].equalsIgnoreCase(prodottoRichiesta))
						{
							risposta = "Prodotto Scelto: " + prodotti[i] + "\n";
							risposta += "Prezzo: " + prezzi[i];
						}
						i++;
					}
					if (risposta.isEmpty())
						risposta = "Prodotto non trovato.";
					break;
				case "6":
					risposta = legenda;
					break;
				case "0":
					risposta = "Arrivederci";
					break;
				default:
					risposta = "Commando inesistente";
			}
			System.out.println(risposta);
			
		}while(!cmd.equals("0"));
		input.close();
	}

}
