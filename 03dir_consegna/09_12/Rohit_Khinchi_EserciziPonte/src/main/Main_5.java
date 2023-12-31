package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main_5 {

	public static void main(String[] args) throws FileNotFoundException {
//		5- Creare un programma che gestisca un agenzia viaggi.
//		Formattare un file con le seguenti informazioni: 
//			destinazione
//			mezzo di trasporto
//			giorni di vacanza
//			costo giornaliero
//			numero persone.
//		Creare un programma che acquisite le informazioni dal file sia in grado di calcolare i prezzi dei 
//		singoli viaggi.
//		Calcolo del prezzo:
//		> Prezzo base 100;
//		> se la destinazione è Dubai o Tokyo aggiungo 200, se è Dublino o Londra aggiungo 150, 
//			se è New York o Miami aggiungo 300. In tutti gli altri casi aggiungo 50
//		> se il mezzo di trasporto è aereo o traghetto aggiungo 500, se è treno aggiungo 200, 
//			se è automobile o autobus aggiungo 100. In tutti gli altri casi aggiungo 1000
//		> se le persone sono più di 5 scontare il viaggio del 5%, se invece sono più di 10 scontare il 
//			viaggio del 10%.
//		
//		Stampare in console:
//		-Scheda di ogni viaggio disponibile nel file
//		-Lista destinazioni(senza ripetizioni)
//		-Lista mezzi di trasporto(senza ripetizioni)
//		-Costo medio dei viaggi in aereo
//		-Costo medio dei viaggi a Dubai
//		-Viaggi più economici(Viaggio/i che costano meno tra tutti quelli nel file)
//		-Viaggi di lusso(Viaggio/i che costano di più tra tutti quelli del file)
		
		//D
		Scanner file;
		String destinazione;
		String mezzoDiTrasporto;
		int giorniDiVacanza;
		double costoGiornaliero;
		int numeroDiPersone;
		double prezzo;
		String risposta;
		String listaDestinazione;
		String listaMezzoDiTrasporto;
		int totalViaggi;
		
		double totalViaggiInAereo;
		double medioAereo;
		double totalPrezzoAereo;
		
		double totalViaggiInDubai;
		double medioDubai;
		double totalPrezzoDubai;
		
		double maxPrezzo;
		double minPrezzo;
		
		String viaggioMinNumero;
		String viaggioMaxNumero;
		
		//I
		file = new Scanner (new File("src/res/File.txt"));
		prezzo = 100.0;
		risposta = "";
		listaDestinazione = "\n";
		listaMezzoDiTrasporto = "\n";
		totalViaggi = 0;
		medioAereo = 0.0;
		totalPrezzoAereo = 0.0;
		totalViaggiInAereo = 0.0;
		totalViaggiInDubai = 0.0;
		medioDubai = 0.0;
		totalPrezzoDubai = 0.0;
		maxPrezzo = Double.MIN_VALUE;
		minPrezzo = Double.MAX_VALUE;
		viaggioMinNumero = "";
		viaggioMaxNumero = "";
		//C

		
		while (file.hasNextLine())
		{
			totalViaggi++;
			destinazione = file.nextLine().toLowerCase();
			mezzoDiTrasporto = file.nextLine().toLowerCase();
			giorniDiVacanza = Integer.parseInt(file.nextLine());
			costoGiornaliero = Double.parseDouble(file.nextLine());
			numeroDiPersone = Integer.parseInt(file.nextLine());
			
			prezzo = 100.0;
//			> se la destinazione è Dubai o Tokyo aggiungo 200, se è Dublino o Londra aggiungo 150, 
//				se è New York o Miami aggiungo 300. In tutti gli altri casi aggiungo 50
			
			prezzo += (destinazione.equalsIgnoreCase("dubai") || destinazione.equalsIgnoreCase("tokyo")) ?
						200 : (destinazione.equalsIgnoreCase("dublino") || destinazione.equalsIgnoreCase("londra")) ?
								150 : (destinazione.equalsIgnoreCase("new york") || destinazione.equalsIgnoreCase("miami")) ?
										300 : 50;
			
//			> se il mezzo di trasporto è aereo o traghetto aggiungo 500, se è treno aggiungo 200, 
//			      se è automobile o autobus aggiungo 100. In tutti gli altri casi aggiungo 1000
			
			switch(mezzoDiTrasporto)
			{
				case "aereo" :
				case "traghetto" :
					prezzo += 500;
					break;
				case "treno" :
					prezzo += 200;
					break;
				case "autobus" :
				case "automobile" :
					prezzo += 100;
					break;
				default:
					prezzo += 1000;
			}
			
//			> se le persone sono più di 5 scontare il viaggio del 5%, se invece sono più di 10 scontare il 
//			viaggio del 10%.
			
			prezzo += giorniDiVacanza * costoGiornaliero;
			
			prezzo += numeroDiPersone > 10 ? prezzo * 0.90 : numeroDiPersone > 5 ? prezzo * 0.95 : prezzo;
			
//			-Lista destinazioni(senza ripetizioni)
			
			//contains ha due problemi, 1.) riguarda a case sensitive || 2.) es new York (in stati uniti) per Contains esclude anche York (regno unito)
			
			if (!listaDestinazione.contains("\n" + destinazione.toUpperCase() + "\n"))
			{
				listaDestinazione += destinazione.toUpperCase() + "\n";
				
			}
			if (!listaMezzoDiTrasporto.contains("\n" + mezzoDiTrasporto.toUpperCase() + "\n"))
				listaMezzoDiTrasporto += mezzoDiTrasporto.toUpperCase() + "\n";
			
			if (mezzoDiTrasporto.contains("aereo"))
			{
				totalViaggiInAereo++;
				totalPrezzoAereo += prezzo;
				medioAereo = totalPrezzoAereo / totalViaggiInAereo;
			}
			
			if (destinazione.contains("dubai"))
			{
				totalViaggiInDubai++;
				totalPrezzoDubai += prezzo;
				medioDubai = totalPrezzoDubai / totalViaggiInDubai;
			}
			
			if (minPrezzo > prezzo)
			{
				minPrezzo = prezzo;
				viaggioMinNumero = "" + totalViaggi;
			}
			
			else if (prezzo == minPrezzo)
			{
				viaggioMinNumero += ", " + totalViaggi; 
			}
			
			if (maxPrezzo < prezzo)
			{
				maxPrezzo = prezzo;
				viaggioMaxNumero = "" + totalViaggi;
			}
			
			else if (prezzo == maxPrezzo)
			{
				viaggioMaxNumero += ", " + totalViaggi; 
			}
			
//			Stampare in console:
//			-Scheda di ogni viaggio disponibile nel file
//			-Lista destinazioni(senza ripetizioni)
//			-Lista mezzi di trasporto(senza ripetizioni)
//			-Costo medio dei viaggi in aereo
//			-Costo medio dei viaggi a Dubai
//			-Viaggi più economici(Viaggio/i che costano meno tra tutti quelli nel file)
//			-Viaggi di lusso(Viaggio/i che costano di più tra tutti quelli del file)
			
					
			risposta += totalViaggi + "° Viaggio\n"
						+ "Destinazione: " + Character.toUpperCase(destinazione.charAt(0)) + destinazione.toLowerCase().substring(1) 
						+ "\nMezzo di trasporto: " + Character.toUpperCase(mezzoDiTrasporto.charAt(0)) + mezzoDiTrasporto.toLowerCase().substring(1) + "\n"
						+ "Giorni di vacanza: " + giorniDiVacanza + "\n"
						+ "Costo giornaliero: " + costoGiornaliero + "\n"
						+ "Numero persone: " + numeroDiPersone + "\n"
						+ "Prezzo: " + prezzo
						+ "\n...............................................\n";
		}
		
		file.close();
		
		listaDestinazione = listaDestinazione.substring(1, listaDestinazione.length());
		
		risposta += "...............................................\n"
					+ "Lista destinazioni: \n" + listaDestinazione
					+ "\n...............................................\n"
					+ "Lista mezzi di trasporto: \n" +listaMezzoDiTrasporto
					+ "\n...............................................\n"
					+ "Costo medio dei viaggi in aereo: " + medioAereo
					+ "\n...............................................\n"
					+"Costo medio dei viaggi a Dubai: " + medioDubai
					+ "\n...............................................\n"
					+ "Viaggi più economici: \n" + "Prezzo: " + minPrezzo + "\nViaggi Numeri: " + viaggioMinNumero
					+ "\n...............................................\n"
					+ "Viaggi di lusso: \n" + "Prezzo: " + maxPrezzo + "\nViaggi Numeri: " + viaggioMaxNumero;
		
		//O
		System.out.println(risposta);

	}

}
