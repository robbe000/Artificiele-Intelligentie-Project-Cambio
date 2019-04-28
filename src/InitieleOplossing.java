import gegevens.*;
import oplossing.*;

import java.util.*;

public class InitieleOplossing {
	
	//Geen enkel voertuig toewijzen aan een reservatie
	public static ArrayList<AReservatie> genereerReservatie(ArrayList<Reservatie> reservaties) {
		ArrayList<AReservatie> oplossingen = new ArrayList<AReservatie>();
		
		for(Reservatie reservatie: reservaties) {
			AReservatie oplossing = new AReservatie();
			oplossing.setResId(reservatie.getId());
			oplossing.setVoertuigId(null);
			oplossing.setPenalty1(reservatie.getPenalty1());
			oplossing.setPenalty2(reservatie.getPenalty2());
			oplossing.setGewZoneId(reservatie.getZoneId());
			
			oplossingen.add(oplossing);
		}
		
		return oplossingen;
	}
	
	
	
	
	//Alle voertuigen in de 0e zone plaatsen
	public static ArrayList<AVoertuig> genereerVoertuig(ArrayList<Voertuig> voertuigen, ArrayList<Zone> zones) {
		ArrayList<AVoertuig> oplossingen = new ArrayList<AVoertuig>();
		
		for(Voertuig voertuig: voertuigen) {
			AVoertuig oplossing = new AVoertuig();
			oplossing.setVoertuigId(voertuig.getId());
			//oplossing.setZoneId(zones.get(0).getId());
			oplossing.setZoneId(-1);
			oplossingen.add(oplossing);
		}
		
		return oplossingen;
	}
}
