	package main;

import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		// D
		String stazionePartenza;
		String stazioneArrivo;
		Scanner tastiera;
		double prezzoBiglietto;
		String risposta;
		
		// I
		tastiera = new Scanner(System.in);
		stazionePartenza = "Centrale Milano";
		prezzoBiglietto = 0;
		risposta = "";
		
		System.out.println("In che stazione vuoi andare");
		stazioneArrivo = tastiera.nextLine();
		
		tastiera.close();
		
		switch(stazioneArrivo.toLowerCase())
		{
			case "bergamo":
			case "como":
				prezzoBiglietto = 5.50;
				break;
			case "torino":
				prezzoBiglietto = 15.50;
				break;
			default:
				risposta = "La stazione non esiste";
		}
		
		if (risposta.isEmpty())
			risposta = "Il tuo biglietto costa" + prezzoBiglietto + "€.";
		
		System.out.println(risposta);
	}

}