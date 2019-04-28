package LokaalZoeken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import gegevens.*;
import oplossing.*;


public class Zoeken {	
	private Tijdschema tijdschema;
	int teller = 0;
	public AOplossing zoeken(AOplossing initOplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatiesOpgave, Tijdschema tijdschemaOpgave) {
		
		System.out.println("Zoeken:");
		
		tijdschema = tijdschemaOpgave;		

		int bestKost = 100000000;
		AOplossing oplossing;
		AOplossing besteOplossing = null;
		
		while(teller < 1000000) {
			oplossing = initOplossing.copy();
			Collections.shuffle(reservatiesOpgave);
			for(Reservatie reservatie : reservatiesOpgave) {
				boolean autoGevonden = false;
				//Collections.shuffle(reservatie.getVoertuigID());
				for(int vid: reservatie.getVoertuigID()) {
					int zoneId = oplossing.getVoertuig().get(vid).getZoneId();
					int gewensteZone = reservatie.getZoneId();
					if(zoneId == gewensteZone) {
						//deze auto is geschikt voor de reservatie -> link
						autoGevonden = true;
						//link
						linkToRes(oplossing, reservatiesOpgave, vid, reservatie);
						break;
					}
					else {
						Collections.shuffle(zones.get(gewensteZone).getAanliggendId());
						for(int zoneAanliggend : zones.get(gewensteZone).getAanliggendId()) {
							if(zoneId == zoneAanliggend) {
								//deze auto is geschikt voor de reservatie (aanliggende zone) -> link
								autoGevonden = true;
								//link
								linkToRes(oplossing, reservatiesOpgave, vid, reservatie);				
								break;
							}
						}
					}
					if(!autoGevonden && oplossing.getVoertuig().get(vid).getZoneId() == -1) {
						//Auto toevoegen in gewenste zone en linken
						if(linkToRes(oplossing, reservatiesOpgave, vid, reservatie)) break;
					}
				}
			}
			int kost = BerekenKost.bereken(oplossing.getVoertuig(), oplossing.getReservatie());
			if(kost < bestKost) {
				bestKost = kost;
				besteOplossing = oplossing.copy();
				System.out.println("Nieuwe best kost: " + kost);
			}
			teller++;
		}
		return besteOplossing;
	}
	
	//Linken aan nieuwe mogelijke reservatie
	private boolean linkToRes(AOplossing oplossing, ArrayList<Reservatie> reservatieOpgave, int vid, Reservatie reservatie) {
		AReservatie huidige_res = null;
		AVoertuig huidig_voertuig = null;
		boolean tijd_mogelijk = true;
		boolean breakloop = false;
		for(AReservatie areservatie: oplossing.getReservatie()) {
			if(areservatie.getResId() == reservatie.getId()) {
				huidige_res = areservatie;
				break;
			}
		}
		//Collections.shuffle(oplossing.getVoertuig());
		for(AVoertuig voertuig: oplossing.getVoertuig()) {
			if(voertuig.getVoertuigId() == vid) {
				for(AReservatie voertuigRes: voertuig.getReservaties()) {
					if(!tijdschema.getSchema(voertuigRes.getResId(), huidige_res.getResId())) {
						tijd_mogelijk = false;
						break;
					}
				}
				if(tijd_mogelijk) {
					huidig_voertuig = voertuig;
					break;
				}
			}
		}
		if(tijd_mogelijk) {
			//zet auto in zone en link
			if(oplossing.getVoertuig().get(vid).getZoneId() == -1) {
				huidig_voertuig.setZoneId(huidige_res.getGewZoneId());
				breakloop = true;
			}
			huidig_voertuig.addReservatie(huidige_res);
			huidige_res.setVoertuig(huidig_voertuig);
			huidige_res.setVoertuigId(vid);
		}
		return breakloop;
	}
	
	private boolean isMogelijk(AVoertuig voertuig, ArrayList<Zone> zones, AOplossing oplossing) {
		//-1 = Het is oké
		int controle = -1;
		for(AReservatie reservatie : oplossing.getReservatie()) {
			if(reservatie.getVoertuigId() != null) {
				if(reservatie.getVoertuigId() == voertuig.getVoertuigId()) {
					//System.out.println("Voertuig gevonden");
					//Voertuig gevonden
					
					//In de exacte zone
					if(reservatie.getGewZoneId() == voertuig.getZoneId()) {
						//System.out.println("In de exacte zone");
						return true;
					}
					
					//In de omliggende zones
					for(Zone zone : zones) {
						//Zone zoeken
						if(zone.getId() == reservatie.getGewZoneId()) {
							//Aanliggende zones overlopen
							for(int idAanliggend : zone.getAanliggendId()) {
								if(voertuig.getZoneId() == idAanliggend) {
									//System.out.println("In de omliggende zone");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		//System.out.println("Het is niet mogelijk! ("+ voertuig.getVoertuigId() +")");
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
	
	private AOplossing unlink(AOplossing oplossing, AVoertuig voertuig) {
		
		if(voertuig.getVoertuigId() == 10)
		{
			System.out.println("In unlink reservatie voertuig 10 size ezservaties" + voertuig.getReservaties().size());
			System.out.println("Itteration:"+ teller);	
			int brol = 2;
		}
		
		
		for(AReservatie reservatie : oplossing.getReservatie()) {
			if(reservatie.getVoertuigId() != null) {
				if(reservatie.getVoertuigId() == voertuig.getVoertuigId()) {
					reservatie.setVoertuig(null);
					reservatie.setVoertuigId(null);
					//int gevonden = 0;
					/*
					for(AReservatie r : voertuig.getReservaties()) {
						if(r.getResId() == reservatie.getResId()) {
							gevonden = 1;
							if(voertuig.getVoertuigId() == 10)
							{
								System.out.println("In setten reservatie voertuig 10 size ezservaties" + voertuig.getReservaties().size());
								System.out.println("In remove reservatie:"+ reservatie.getResId());	
							}
							voertuig.getReservaties().remove(r);
							break;
						}
					}
					*/
					voertuig.getReservaties().remove(reservatie);
					//if(gevonden == 0) {
						//System.out.println("Fout in unlink! De 2 reservatielijsten zijn niet compatiebel.");
					//	System.exit(1);
					//}
					return oplossing;
				}
			}
		}
		return oplossing;
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
				//Een nog niet gelinkte auto gevonden!
				oplossing.getVoertuig().get(random).setZoneId(randomnummer2);
				return random;
			}
		}
		
		//Alle auto's waren reeds gelinkt!
		//+Neem een naburige zone ipv een random zone!
		Zone exacteZone = zones.get(oplossing.getVoertuig().get(randomnummer.get(0)).getZoneId());
		int buurZone = rand.nextInt(exacteZone.getAanliggendId().size());
		
		oplossing.getVoertuig().get(randomnummer.get(0)).setZoneId(exacteZone.getAanliggendId().get(buurZone));
		

		//Aangepast voertuigId teruggeven
		return randomnummer.get(0);
	}
}
