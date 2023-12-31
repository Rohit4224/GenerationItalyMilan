package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
//		Scrivere un file che sia formattato nel seguente modo:
//			nomeVideogioco (testo)
//			piattaforma (testo)
//			limiteEta (int)
//			numerogiocatori (int)
//			primaPersona (boolean)
//			Leggere i dati del file e salvarli negli aposisti vettori(array)
//			Scrivere un menù che permetta di:
	//			Vedere tutte le schede dei videogiochi
	//			Vedere tutti i titoli sopra un limiteEta a scelta dell'utente
	//			Vedere quanti videogiochi ci sono in prima persona e quanti no
	//			Vedere il numero medio di giocatori per una piattaforma a scelta dell'utente
//
//			BONUS (da fare solo quando quelli sopra funzionano bene!!)
//			Vedere il numero di giocatori medio per ogni piattaforma disponibile
//			Vedere il numero totale di giochi per ogni limiteEta
		
		Scanner file;
		String percorso;
		String nomeVideogioco[];
		String piattaForma[];
		int limiteEta[];
		int numeriGiocatori[];
		boolean primaPersona[];
		int dimension;
		int i;
		String schedeDeiVideogiochi;
		String legenda;
		
		//I
		percorso = "src/res/Dati.txt";
		file = new Scanner(new File(percorso));
		dimension = Integer.parseInt(file.nextLine());
		nomeVideogioco = new String[dimension];
		piattaForma = new String[dimension];
		limiteEta = new int[dimension];
		numeriGiocatori = new int[dimension];
		primaPersona = new boolean[dimension];
		i = 0;
		schedeDeiVideogiochi = "";
		legenda = "";
		
		//C
		while(file.hasNextLine())
		{
			nomeVideogioco[i] = file.nextLine();
			piattaForma[i] = file.nextLine();
			limiteEta[i] = Integer.parseInt(file.nextLine());
			numeriGiocatori[i] = Integer.parseInt(file.nextLine());
			primaPersona[i] = Boolean.parseBoolean(file.nextLine());
			
			i++;
		}
		file.close();
		
		i = 0;
		while(i < nomeVideogioco.length)
		{
			schedeDeiVideogiochi += "\nNome del videogioco: " + nomeVideogioco[i] 
									+ "\nPittaforma: " + piattaForma[i]
									+ "\nLimite del Età: " + limiteEta[i]
									+ "\nNumeri dei Giocatori: " + numeriGiocatori[i]
									+ "\nPrima Persona: " + primaPersona[i]
									+ "\n----------------------------------\n";
			i++;
		}
	
		legenda += "\tSeleziona: \n1 - Vedere tutte le schede dei videogiochi\n"
				+ "2 - Vedere tutti i titoli sopra un limiteEta a scelta dell'utente\n"
				+ "3 - Vedere quanti videogiochi ci sono in prima persona e quanti no\n"
				+ "4 - Vedere il numero medio di giocatori per una piattaforma a scelta dell'utente\n"
				+ "5 - Vedere il numero di giocatori medio per ogni piattaforma disponibile\n"
				+ "6 - Vedere il numero totale di giochi per ogni limiteEta\n"
				+ "7 - Legenda\n"
				+ "0 - Esci";
		
		String cmd;
		Scanner input;
		String risposta;
		
		cmd = "";
		input = new Scanner(System.in);
		risposta = "";
		System.out.println("Benvenuto.\n" + legenda);		
		do
		{
			System.out.println("Commando: ");
			cmd = input.nextLine();
			risposta = "";
			switch(cmd)
			{
				case "1":
					risposta = "tutte le schede dei videogiochi: \n" + schedeDeiVideogiochi;
					break;
				case "2":
					System.out.println("Scrivi l'eta': ");
					int sceltEta = Integer.parseInt(input.nextLine());
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if(limiteEta[i] > sceltEta)
							risposta += nomeVideogioco[i] + ", ";
						i++;
					}
					if (!risposta.isEmpty())
					{
						risposta = risposta.substring(0, risposta.length() - 2);
						risposta = "Tutti i titoli sopra un limiteEta a scelta dell'utente: \n" + risposta;
					}
					else
						risposta = "Non esiste.";
					break;
				case "3":
					int count = 0;
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if(primaPersona[i] == true)
							count++;
						i++;
					}
					risposta = "Quanti videogiochi ci sono in prima persona: " + count + "\n" + "Non ci sono in prima persona: " + Math.abs(count - dimension);
					break;
				case "4":
					System.out.println("Scrivi piattaforma: ");
					String sceltaPiattaforma = input.nextLine();
					double TotGiocatori = 0.0;
					double numPiattaforma = 0.0;
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if (piattaForma[i].equalsIgnoreCase(sceltaPiattaforma))
						{
							TotGiocatori += numeriGiocatori[i];
							numPiattaforma++;
						}
						i++;
					}
			
					risposta = "Il numero medio di giocatori per una piattaforma a scelta dell'utente: " + TotGiocatori/numPiattaforma;
					break;
				case "5":
					risposta = "Il numero di giocatori medio per ogni piattaforma disponibile: \n";
					int countUniquePiattaforma = 0;
					String uniquePittaformi = "";
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if (!uniquePittaformi.toLowerCase().contains(piattaForma[i].toLowerCase()))
						{
							uniquePittaformi += piattaForma[i] + ", ";
							countUniquePiattaforma++;
						}
						i++;
					}
					String ogniPiattaforma[] = new String[countUniquePiattaforma];
					uniquePittaformi = "";
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if (!uniquePittaformi.toLowerCase().contains(piattaForma[i].toLowerCase()))
						{
							uniquePittaformi += piattaForma[i] + ", ";
							ogniPiattaforma[i] = piattaForma[i];
						}
						i++;
					}
					i = 0;
					int j = 0;
					
					while (i < ogniPiattaforma.length)
					{
						TotGiocatori = 0.0;
						numPiattaforma = 0.0;
						j = 0;
						while (j < nomeVideogioco.length)
						{
							if(ogniPiattaforma[i].equalsIgnoreCase(piattaForma[j]))
							{
								TotGiocatori += numeriGiocatori[j];
								numPiattaforma++;
							}
							j++;
						}
						risposta += ogniPiattaforma[i] + " ---> " + "Media: " + (TotGiocatori/numPiattaforma + "\n");
						i++;
					}
					break;
				case "6":
					risposta = "Il numero totale di giochi per ogni limiteEta: \n";
					int countUniqueLimiteEta = 0;
					String uniqueLimiteEta = "";
					i = 0;
					while (i < nomeVideogioco.length)
					{
						if (!uniqueLimiteEta.contains("" + limiteEta[i]))
						{
							uniqueLimiteEta += "" + limiteEta[i] + ", ";
							countUniqueLimiteEta++;
						}
						i++;
					}
					int ogniLimiteEta[] = new int[countUniqueLimiteEta];
					uniqueLimiteEta = "";
					i = 0;
					j = 0;
					while (i < limiteEta.length)
					{
						if (!uniqueLimiteEta.contains("" + limiteEta[i]))
						{
							uniqueLimiteEta += "" + limiteEta[i] + ", ";
							ogniLimiteEta[j] = limiteEta[i];
							j++;
						}
						i++;
					}
					i = 0;
					j = 0;
					int numTotaleGiochi;
					while (i < ogniLimiteEta.length)
					{
						numTotaleGiochi = 0;
						j = 0;
						while (j < nomeVideogioco.length)
						{
							if(ogniLimiteEta[i] == (limiteEta[j]))
							{
								numTotaleGiochi++;
							}
							j++;
						}
						risposta += "Eta: " + ogniLimiteEta[i] + " ---> "  + "Numeri di Giochi: " + (numTotaleGiochi + "\n");
						i++;
					}
					break;
				case "7":
					risposta = legenda;
					break;
				case "0":
					risposta = "Arrivederci";
					break;
				default:
					risposta = "commando inesistente";
			}
			System.out.println(risposta);
		}while(!cmd.equals("0"));
		System.out.println("END");
		input.close();
	}
}
