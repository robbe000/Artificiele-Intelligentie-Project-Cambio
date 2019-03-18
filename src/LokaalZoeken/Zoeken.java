package LokaalZoeken;

import java.util.ArrayList;
import java.util.Random;

import gegevens.*;
import oplossing.*;


public class Zoeken {
	private AOplossing besteOplossing;
	
	public AOplossing zoeken(AOplossing oplossing, ArrayList<Zone> zones) {
		
		System.out.println("Zoeken:");
		
		int teller = 0;
		int bestKost = 100000000;
		AOplossing besteOplossing = null;
		
		while(teller < 100000) {
			//Random voertuig nemen en en random zone plaatsen
			AVoertuig verplaatstVoertuig = random(oplossing, zones);
			System.out.println("Verplaatst voertuig: " + Integer.toString(verplaatstVoertuig.getVoertuigId()) + "; Zone: "+ Integer.toString(verplaatstVoertuig.getZoneId()));
			
			//Is voertuig gelinkt aan zone?
			if(isLinked(verplaatstVoertuig, oplossing)) {
				System.out.println("Linked");
				//Is de link mogelijk of niet mogelijk?
				if(!isMogelijk(verplaatstVoertuig, zones, oplossing)) {
					unlink(oplossing, verplaatstVoertuig);
				}
			}
			
			
			oplossing = linkToRes(verplaatstVoertuig, oplossing);
			
			//Kost berekenen
			int kost = BerekenKost.bereken(oplossing.getVoertuig(), oplossing.getReservatie());
					
			//Kost initiele oplossing
			System.out.println("Kost: " + Integer.toString(kost));
			
			if(kost < bestKost) {
				bestKost = kost;
				besteOplossing = oplossing;
			}
		
			teller++;
		}
		
		System.out.println("Bestkost = "+ bestKost);
		return besteOplossing;
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
	
	private boolean isMogelijk(AVoertuig voertuig, ArrayList<Zone> zones, AOplossing oplossing) {
		for(AReservatie reservatie : oplossing.getReservatie()) {
			if(reservatie.getVoertuigId() != null) {
				if(reservatie.getVoertuigId() == voertuig.getVoertuigId()) {
					System.out.println("Voertuig gevonden");
					//Voertuig gevonden
					
					//In de exacte zone
					if(reservatie.getGewZoneId() == voertuig.getZoneId()) {
						System.out.println("In de exacte zone");
						return true;
					}
					
					//In de omliggende zones
					for(Zone zone : zones) {
						//Zone zoeken
						if(zone.getId() == reservatie.getGewZoneId()) {
							//Aanliggende zones overlopen
							for(int id : zone.getAanliggendId()) {
								if(zone.getId() == voertuig.getZoneId()) {
									System.out.println("In de omliggende zone");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("Het is niet mogelijk!");
		return false;
	}
	
	private boolean isLinked(AVoertuig voertuig, AOplossing oplossing) {
		for(AReservatie reservatie : oplossing.getReservatie()) {
			if(reservatie.getVoertuigId() != null) {
				if(reservatie.getVoertuigId() == voertuig.getVoertuigId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void unlink(AOplossing oplossing, AVoertuig voertuig) {
		for(AReservatie reservatie : oplossing.getReservatie()) {
			if(reservatie.getVoertuigId() != null) {
				if(reservatie.getVoertuigId() == voertuig.getVoertuigId()) {
					reservatie.setVoertuig(null);
					reservatie.setVoertuigId(null);
				}
			}
		}	
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
