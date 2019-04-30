package LokaalZoeken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import gegevens.*;
import oplossing.*;


public class Zoeken {	
	private Tijdschema tijdschema;
	int teller = 0;
	public AOplossing zoeken(AOplossing initOplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatiesOpgave, Tijdschema tijdschemaOpgave, long tijdlimiet, long randomseed) {
		
		System.out.println("Zoeken:");
		
		tijdschema = tijdschemaOpgave;		

		int bestKost = 100000000;
		AOplossing oplossing;
		AOplossing besteOplossing = null;
		
		long tijd= System.currentTimeMillis();
		long eindtijd = tijd + tijdlimiet;
		Random rnd = new Random(randomseed);
		
		while(System.currentTimeMillis() < eindtijd) {
			oplossing = initOplossing.copy();
			Collections.shuffle(reservatiesOpgave, rnd);
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
						//Collections.shuffle(zones.get(gewensteZone).getAanliggendId());
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
			}
			huidig_voertuig.addReservatie(huidige_res);
			huidige_res.setVoertuig(huidig_voertuig);
			huidige_res.setVoertuigId(vid);
			breakloop = true;
		}
		return breakloop;
	}		
}
