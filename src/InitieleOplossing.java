import gegevens.*;
import oplossing.*;

import java.util.*;

public class InitieleOplossing {
	public ArrayList<AReservatie> genereerReservatie(ArrayList<Reservatie> reservaties) {
		ArrayList<AReservatie> oplossingen = new ArrayList<AReservatie>();
		
		for(Reservatie reservatie: reservaties) {
			AReservatie oplossing = new AReservatie();
			oplossing.setResId(reservatie.getId());
			oplossing.setVoertuigId(null);
			
			oplossingen.add(oplossing);
		}
		
		return oplossingen;
	}
	
	public ArrayList<AVoertuig> genereerVoertuig(ArrayList<Voertuig> voertuigen, ArrayList<Zone> zones) {
		ArrayList<AVoertuig> oplossingen = new ArrayList<AVoertuig>();
		
		for(Voertuig voertuig: voertuigen) {
			AVoertuig oplossing = new AVoertuig();
			oplossing.setVoertuigId(voertuig.getId());
			oplossing.setZoneId(zones.get(0).getId());
			
			oplossingen.add(oplossing);
		}
		
		return oplossingen;
	}
}
