package LokaalZoeken;

import gegevens.*;
import oplossing.*;

public class Zoeken {
	private AOplossing besteOplossing;
	
	public AOplossing zoeken(AOplossing oplossing) {
		
		return oplossing;
	}
	
	//Linken aan nieuwe mogelijke reservatie
	private AOplossing linkToRes(AVoertuig voertuig, AOplossing oplossing) {
		for(AReservatie reservatie : oplossing.getReservatie()) {
			//In de gewenste zone? => Dit hebben we het liefst!
			if(reservatie.getGewZoneId() == voertuig.getZoneId()) {
				reservatie.setVoertuig(voertuig);
				reservatie.setVoertuigId(voertuig.getVoertuigId());
				return oplossing;
			}
			//Ook nog met omliggende zones!
			
		}		
		
		return oplossing;
	}

}
