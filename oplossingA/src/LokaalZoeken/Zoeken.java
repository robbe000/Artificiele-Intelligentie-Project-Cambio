package LokaalZoeken;

import java.util.ArrayList;
import java.util.Random;

import gegevens.*;
import oplossing.*;


public class Zoeken {	
	private Tijdschema tijdschema;
	int teller = 0;
	public AOplossing zoeken(AOplossing oplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatiesOpgave, Tijdschema tijdschemaOpgave) {
		
		System.out.println("Zoeken:");
		
		tijdschema = tijdschemaOpgave;		

		int bestKost = 100000000;
		AOplossing besteOplossing = null;
		
		while(teller < 1000000) {
			//Random voertuig nemen en en random zone plaatsen
			int verplaatstVoertuigId = random(oplossing, zones);
			//System.out.println("Verplaatst voertuig: " + Integer.toString(verplaatstVoertuigId) + "; Zone: "+ Integer.toString(oplossing.getVoertuig().get(verplaatstVoertuigId).getZoneId()));
			
			
			
			//Is voertuig gelinkt aan zone?
			if(isLinked(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing)) {
				//System.out.println("Linked");
				//Is de nieuwe link mogelijk of niet mogelijk?
				
				int i = 0;
				while(i <= oplossing.getVoertuig().get(verplaatstVoertuigId).getReservaties().size()) {
					for(AReservatie r : oplossing.getVoertuig().get(verplaatstVoertuigId).getReservaties()) {
						if(r.getGewZoneId() == oplossing.getVoertuig().get(verplaatstVoertuigId).getZoneId()) {
							//In de exacte zone
						} else {
							int gevonden = 0;
							for(int z : zones.get(oplossing.getVoertuig().get(verplaatstVoertuigId).getZoneId()).getAanliggendId()) {
								if(z == oplossing.getVoertuig().get(verplaatstVoertuigId).getZoneId()) {
									//In een omliggende zone!
									gevonden = 1;
								}
							}
							
							if(gevonden == 0) {
								//Niet in de exacte zone en niet in de omliggende zone!
								r.setVoertuig(null);
								r.setVoertuigId(null);
								int gevonden1 = 0;
								for(AReservatie r1 : oplossing.getVoertuig().get(verplaatstVoertuigId).getReservaties()) {
									if(r1.getResId() == r.getResId()) {
										gevonden1 = 1;
										oplossing.getVoertuig().get(verplaatstVoertuigId).getReservaties().remove(r1);
										break;
									}
								}
								if(gevonden1 == 0) {
									//System.out.println("Fout in unlink! De 2 reservatielijsten zijn niet compatiebel.");
									System.exit(1);
								}
								break;
							}
							
						}
					}
					i++;
				}
				
				if(!isMogelijk(oplossing.getVoertuig().get(verplaatstVoertuigId), zones, oplossing)) {
					
					if(verplaatstVoertuigId == 10)
					{
						System.out.println("Voor unlink to res voertuig 10 size ezservaties" + oplossing.getVoertuig().get(10).getReservaties().size());
						System.out.println("itteratie:"+teller);			
					}
					
					
					oplossing = unlink(oplossing, oplossing.getVoertuig().get(verplaatstVoertuigId));
					oplossing = linkToRes(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing, zones, reservatiesOpgave);
				} else {
					oplossing = linkToRes(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing, zones, reservatiesOpgave);
				}
				

				
			} else {
				oplossing = linkToRes(oplossing.getVoertuig().get(verplaatstVoertuigId), oplossing, zones, reservatiesOpgave);
			}
			
			if(verplaatstVoertuigId == 10)
			{
				System.out.println("Na link to res voertuig 10 size ezservaties" + oplossing.getVoertuig().get(10).getReservaties().size());
				System.out.println("itteratie:"+teller);			
			}
			
			//Kost berekenen
			int kost = BerekenKost.bereken(oplossing.getVoertuig(), oplossing.getReservatie());
					
			//Kost initiele oplossing
			//System.out.println("Kost: " + Integer.toString(kost));
			
			if(kost < bestKost) {
				bestKost = kost;
				besteOplossing = oplossing.copy();
				
				if(verplaatstVoertuigId == 10)
				{
					System.out.println("Na opslaan oplossing" + besteOplossing.getVoertuig().get(10).getReservaties().size());
					System.out.println("itteratie:"+teller);			
				}
				
				
				//System.out.println("Herberekende kost = " + BerekenKost.bereken(besteOplossing.getVoertuig(), besteOplossing.getReservatie()));
			}
		
			teller++;
		}
		
		//System.out.println("Herberekende kost = " + BerekenKost.bereken(besteOplossing.getVoertuig(), besteOplossing.getReservatie()));
		//System.out.println("Bestkost = "+ bestKost);
		return besteOplossing;
	}
	
	//Linken aan nieuwe mogelijke reservatie
	private AOplossing linkToRes(AVoertuig voertuig, AOplossing oplossing, ArrayList<Zone> zones, ArrayList<Reservatie> reservatieOpgave) {
		for(AReservatie reservatie : oplossing.getReservatie()) {			
			//In de gewenste zone? => Dit hebben we het liefst!
			if(reservatie.getGewZoneId() == voertuig.getZoneId()) {
				
				int gevonden = 0;
				int tijd_mogelijk = 1;
				
				
				for(int mogelijkVoertuig : reservatieOpgave.get(reservatie.getResId()).getVoertuigID()) {
					if(mogelijkVoertuig == voertuig.getVoertuigId()) {
						gevonden = 1;
						break;
					}
				}
				
				if(!voertuig.getReservaties().isEmpty()) {
					//System.out.println("Size:" + voertuig.getReservaties().size());
					
					for(AReservatie VoertuigReservatie : voertuig.getReservaties()) {
						if(!tijdschema.getSchema(VoertuigReservatie.getResId(), reservatie.getResId())) {
							tijd_mogelijk = 0;
							break;
						}
					}	
				} 
				else {
					tijd_mogelijk = 1;
				}
				
				if(gevonden == 1 && tijd_mogelijk == 1) {
					//if(reservatie.getVoertuig() == null) {
						reservatie.setVoertuig(voertuig);
						reservatie.setVoertuigId(voertuig.getVoertuigId());
						voertuig.addReservatie(reservatie);
						//System.out.println("Voertuig " + voertuig.getVoertuigId() + " gelinkt aan reservatie " + reservatie.getResId() + " in zone " + voertuig.getZoneId());
						
						if(voertuig.getVoertuigId() == 10)
						{
							System.out.println("In setten reservatie voertuig 10 size ezservaties" + voertuig.getReservaties().size());
							System.out.println("In toegevoegde reservatie:"+ reservatie.getResId());	
							System.out.println("itteratie:"+ teller);
						}
						
						return oplossing;
					//}
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
