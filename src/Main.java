import java.util.*;

import gegevens.*;
import oplossing.*;

public class Main {

	private static ArrayList<Reservatie> reservaties  = new ArrayList<Reservatie>();;
	private static ArrayList<Voertuig> voertuigen = new ArrayList<Voertuig>();
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	private static ArrayList<AReservatie> areservaties = new ArrayList<AReservatie>();
	private static ArrayList<AVoertuig> avoertuigen = new ArrayList<AVoertuig>();
	
	private static Readcsv lees;

	public static void main(String[] args) {
		//INLEZEN		
		lees = new Readcsv("100_5_14_25.csv");
		reservaties = lees.LeesReservaties();
		
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
	}

}
