package LokaalZoeken;

import java.util.ArrayList;
import java.util.Random;

import gegevens.*;
import oplossing.*;

public class Zoeken {
	private AOplossing besteOplossing;
	
	public AOplossing zoeken(AOplossing oplossing, ArrayList<Zone> zones) {
		
		System.out.println("Zoeken:");
		AVoertuig verplaatstVoertuig = random(oplossing, zones);
		System.out.println("Verplaatst voertuig: " + Integer.toString(verplaatstVoertuig.getVoertuigId()) + "; Zone: "+ Integer.toString(verplaatstVoertuig.getZoneId()));
		
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
	
	private boolean isLinked(AVoertuig voertuig) {
		if(voertuig != null) return true;
		else return false;
	}
	
	private void unlink(AReservatie reservatie) {
		reservatie.setVoertuig(null);
		reservatie.setVoertuigId(null);
	}

	private AVoertuig random(AOplossing oplossing, ArrayList<Zone> zones)
	{
		Random rand = new Random();
		Random rand2 = new Random();
		int randomnummer = rand.nextInt(oplossing.getVoertuig().size());
		int randomnummer2 = rand2.nextInt(zones.size());
		oplossing.getVoertuig().get(randomnummer).setZoneId(randomnummer2);
		AVoertuig voertuig = oplossing.getVoertuig().get(randomnummer);
		return voertuig;
	}
}
