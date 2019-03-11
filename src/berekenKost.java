import gegevens.*;
import oplossing.*;

import java.util.*;

public class berekenKost {
	public static int bereken(ArrayList<AVoertuig> voertuigen, ArrayList<AReservatie> reservaties) {
		int kost = 0;

		//Reservatie toegewezen?
		for(AReservatie reservatie: reservaties) {
			if(reservatie.getVoertuigId() == null) {
				//Niet kunnen plaatsen
				kost += reservatie.getPenalty1();
			} else {
				if(reservatie.getGewZoneId() != reservatie.getVoertuig().getZoneId()) {
					kost += reservatie.getPenalty2();
				}
			}
		}
		
		return kost;
	}
}
