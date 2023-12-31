package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//D
		Scanner file;
		Scanner input;
		String name;
		int age;
		String residence;
		String cercaNome;
		int cercaAge;
		String cercaResidence;
		String risposta;
		String legenda;
		String cmd;
		
		//I
		input = new Scanner(System.in);
		//file = new Scanner(new File("src/res/Anagrafiche.txt"));
		risposta = "";
		cercaNome = "";
		
		legenda = "1 - cerca file tramite il nome\n"
				+ "2 - cerca file tramite l'eta\n"
				+ "3 - cerca file tramite la residenza\n"
				+ "4 - Legenda\n"
				+ "0 - Esci\n";
		
		System.out.println("Benvenuto.");
		
		System.out.println(legenda);
		
		//C
		
		do
		{
			System.out.println("Commando: ");
			cmd = input.nextLine();
			risposta = "";
			
			switch(cmd)
			{
			case "1" :
				System.out.println("Digita il nome che vuoi cercare: ");
				cercaNome = input.nextLine();
				file = new Scanner(new File("src/res/Anagrafiche.txt"));
				while(file.hasNextLine())
				{
					name = file.nextLine();
					age = Integer.parseInt(file.nextLine());
					residence = file.nextLine();
					
					if (name.equalsIgnoreCase(cercaNome))
						risposta += name + " di anni " + age + " residente a " + residence + "\n";
				}
				file.close();
				if (risposta.equalsIgnoreCase(""))
					risposta += "Nessun correspondenza trovata";
				break;
			case "2":
				System.out.println("Digita l'eta che vuoi cercare: ");
				cercaAge = Integer.parseInt(input.nextLine());
				file = new Scanner(new File("src/res/Anagrafiche.txt"));
				while(file.hasNextLine())
				{
					name = file.nextLine();
					age = Integer.parseInt(file.nextLine());
					residence = file.nextLine();
					
					if (age == cercaAge)
						risposta += name + " di anni " + age + " residente a " + residence + "\n";
				}
				file.close();
				if (risposta.isEmpty())
					risposta += "Nessun correspondenza trovata";
				break;
			case "3":
				System.out.println("Digita la residenza che vuoi cercare: ");
				cercaResidence = input.nextLine();
				file = new Scanner(new File("src/res/Anagrafiche.txt"));
				while(file.hasNextLine())
				{
					name = file.nextLine();
					age = Integer.parseInt(file.nextLine());
					residence = file.nextLine();
					
					if (residence.equalsIgnoreCase(cercaResidence))
						risposta += name + " di anni " + age + " residente a " + residence + "\n";
				}
				file.close();
				if (risposta.equalsIgnoreCase(""))
					risposta += "Nessun correspondenza trovata";
				break;
			case "4":
				risposta = legenda;
				break;
			case "0":
				risposta = "Arrivederci";
				break;
			default:
				risposta = "Commando inesistente";
			}//End switch
			
			System.out.println(risposta);
		}while(!cmd.equalsIgnoreCase("0"));
		
		//file.close();
		input.close();
		
		//O
		
	}

}
