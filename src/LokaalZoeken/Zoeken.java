package LokaalZoeken;

import java.util.ArrayList;
import java.util.Random;

import gegevens.*;
import oplossing.*;


public class Zoeken {
	
	public AOplossing zoeken(AOplossing oplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatiesOpgave) {
		
		System.out.println("Zoeken:");
		
		int teller = 0;
		int bestKost = 100000000;
		AOplossing besteOplossing = null;
		
		while(teller < 10000) {
			//Random voertuig nemen en en random zone plaatsen
			int verplaatstVoertuigId = random(oplossing, zones);
			System.out.println("Verplaatst voertuig: " + Integer.toString(verplaatstVoertuigId) + "; Zone: "+ Integer.toString(oplossing.getVoertuig().get(verplaatstVoertuigId).getZoneId()));
			
			//Is voertuig gelinkt aan zone?
			if(isLinked(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing)) {
				System.out.println("Linked");
				//Is de nieuwe link mogelijk of niet mogelijk?
				if(!isMogelijk(oplossing.getVoertuig().get(verplaatstVoertuigId), zones, oplossing)) {
					unlink(oplossing, oplossing.getVoertuig().get(verplaatstVoertuigId));
					oplossing = linkToRes(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing, zones, reservatiesOpgave);
				}
			} else {
				oplossing = linkToRes(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing, zones, reservatiesOpgave);
			}
			
			//Kost berekenen
			int kost = BerekenKost.bereken(oplossing.getVoertuig(), oplossing.getReservatie());
					
			//Kost initiele oplossing
			System.out.println("Kost: " + Integer.toString(kost));
			
			if(kost < bestKost) {
				bestKost = kost;
				besteOplossing = oplossing.copy();
				System.out.println("Herberekende kost = " + BerekenKost.bereken(besteOplossing.getVoertuig(), besteOplossing.getReservatie()));
			}
		
			teller++;
		}
		
		System.out.println("Herberekende kost = " + BerekenKost.bereken(besteOplossing.getVoertuig(), besteOplossing.getReservatie()));
		System.out.println("Bestkost = "+ bestKost);
		return besteOplossing;
	}
	
	//Linken aan nieuwe mogelijke reservatie
	private AOplossing linkToRes(AVoertuig voertuig, AOplossing oplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatieOpgave) {
		for(AReservatie reservatie : oplossing.getReservatie()) {
			//In de gewenste zone? => Dit hebben we het liefst!
			if(reservatie.getGewZoneId() == voertuig.getZoneId()) {
				
				int gevonden = 0;
				
				for(int mogelijkVoertuig : reservatieOpgave.get(reservatie.getResId()).getVoertuigID()) {
					if(mogelijkVoertuig == voertuig.getVoertuigId()) {
						gevonden = 1;
					}
				}
				
				if(gevonden == 1) {
					if(reservatie.getVoertuig() == null) {
						reservatie.setVoertuig(voertuig);
						reservatie.setVoertuigId(voertuig.getVoertuigId());
						System.out.println("Voertuig " + voertuig.getVoertuigId() + " gelinkt aan reservatie " + reservatie.getResId() + " in zone " + voertuig.getZoneId());
						return oplossing;
					}
				}
			}			
		}		
		
		/*
		for(AReservatie reservatie : oplossing.getReservatie()) {
			//Ook nog met omliggende zones
			for(Zone zone : zones) {
				if(zone.getId() == voertuig.getZoneId()) {
					//Over de zones
					for(int zoneAanliggend : zone.getAanliggendId()) {
						if(zoneAanliggend == reservatie.getGewZoneId()) {
							if(reservatie.getVoertuig() == null) {
								reservatie.setVoertuig(voertuig);
								reservatie.setVoertuigId(voertuig.getVoertuigId());	
								System.out.println("Voertuig " + voertuig.getVoertuigId() + " gelinkt aan reservatie " + reservatie.getResId() + "(OMLIGGENDE ZONE)");
								return oplossing;
							}
						}
					}
				}
			}
		}
		*/
		
		//unlink(oplossing, voertuig);
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
							for(int idAanliggend : zone.getAanliggendId()) {
								if(voertuig.getZoneId() == idAanliggend) {
									System.out.println("In de omliggende zone");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("Het is niet mogelijk! ("+ voertuig.getVoertuigId() +")");
		return false;
	}
	
	//Is het voertuig al gelinkt aan een reservatie?
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

	private int random(AOplossing oplossing, ArrayList<Zone> zones)
	{
		Random rand = new Random();
		Random rand2 = new Random();
		
		ArrayList<Integer> randomnummer = new ArrayList<Integer>();
		
		randomnummer.add(rand.nextInt(oplossing.getVoertuig().size()));
		randomnummer.add(rand.nextInt(oplossing.getVoertuig().size()));
		randomnummer.add(rand.nextInt(oplossing.getVoertuig().size()));
		randomnummer.add(rand.nextInt(oplossing.getVoertuig().size()));
		randomnummer.add(rand.nextInt(oplossing.getVoertuig().size()));
		
		int randomnummer2 = rand2.nextInt(zones.size());
		
		for(int random : randomnummer) {
			int gevonden = 0;
			for(AReservatie reservatie : oplossing.getReservatie()) {
				if(reservatie.getVoertuigId() != null) {
					if(random == reservatie.getVoertuigId()) {
						gevonden = 1;
					}
				}
			}
			if(gevonden == 0) {
				oplossing.getVoertuig().get(random).setZoneId(randomnummer2);
				return random;
			}
		}
		
		oplossing.getVoertuig().get(randomnummer.get(0)).setZoneId(randomnummer2);

		//Aangepast voertuigId teruggeven
		return randomnummer.get(0);
	}
}
