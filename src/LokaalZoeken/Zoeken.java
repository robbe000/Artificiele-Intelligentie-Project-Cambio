package LokaalZoeken;

import java.util.Random;

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
	
	private AVoertuig random(AOplossing oplossing)
	{
		Random rand = new Random();
		AVoertuig voertuig = oplossing.getVoertuig().get(rand.nextInt(oplossing.getVoertuig().size()));
		
		Random rand2 = new Random();
		oplossing.getReservatie().get(rand2.nextInt(oplossing.getReservatie().size())).setVoertuig(voertuig);
		oplossing.getReservatie().get(rand2.nextInt(oplossing.getReservatie().size())).setVoertuigId(voertuig.getVoertuigId());
		
		return voertuig;
	}

}
