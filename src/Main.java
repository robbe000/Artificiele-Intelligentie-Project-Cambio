import java.util.*;

import gegevens.*;
import oplossing.*;
import LokaalZoeken.*;

public class Main {

	private static ArrayList<Reservatie> reservaties  = new ArrayList<Reservatie>();;
	private static ArrayList<Voertuig> voertuigen = new ArrayList<Voertuig>();
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	private static ArrayList<AReservatie> areservaties = new ArrayList<AReservatie>();
	private static ArrayList<AVoertuig> avoertuigen = new ArrayList<AVoertuig>();
	
	private static Readcsv lees;
	private static Writecsv schrijf;
	private static Writecsv schrijf_tijdschema;
	private static Tijdschema tijdschema;

	public static void main(String[] args) {
		//INLEZEN		
		lees = new Readcsv("100_5_14_25.csv");
		reservaties = lees.LeesReservaties();
		int kost;
		
		//testprint van reservaties
		for(int i = 0; i< reservaties.size(); i++)
		{
			System.out.println(reservaties.get(i).getVoertuigID());
		}
		
		zones = lees.LeesZones();
		
		//testprint van zones
		for(int i = 0; i< zones.size(); i++)
		{
			System.out.println(zones.get(i).getAanliggendId());
		}	
		
		voertuigen = lees.LeesVoertuigen();

		//testprint van zones
		for(int i = 0; i< voertuigen.size(); i++)
		{
			System.out.println(voertuigen.get(i).getId());
		}	
		
		lees.closeLees();
		//dagen ook uitlezen, laatste lijn csv?
		
		
		//Initiele oplossing genereren
		avoertuigen = InitieleOplossing.genereerVoertuig(voertuigen, zones);
		areservaties = InitieleOplossing.genereerReservatie(reservaties);
		
		//Kost berekenen
		kost = BerekenKost.bereken(avoertuigen, areservaties);
		
		//Kost initiele oplossing
		System.out.println("Kost: " + Integer.toString(kost));
		
		AOplossing oplossing = new AOplossing(areservaties, avoertuigen);
		
		//Tijdschema genereren
		tijdschema = new Tijdschema(reservaties);
		
		//Lokaal zoeken
		Zoeken zoek = new Zoeken();
		oplossing = zoek.zoeken(oplossing, zones, reservaties, tijdschema);
		
		//Kost berekenen
		kost = BerekenKost.bereken(oplossing.getVoertuig(), oplossing.getReservatie());
		System.out.println("Bestkost (In main)= "+ kost);
		
		// wegschrijven naar file
		schrijf = new Writecsv("oplossing.csv");
		schrijf.writeTocsv(kost, oplossing.getReservatie(), oplossing.getVoertuig());
		
		
		schrijf_tijdschema = new Writecsv("timetable.csv");
		schrijf_tijdschema.writeTimetable(tijdschema);
		
	}

}
