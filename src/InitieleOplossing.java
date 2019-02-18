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
}
