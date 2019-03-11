package LokaalZoeken;

import gegevens.*;
import oplossing.*;

public class Zoeken {
	private AOplossing besteOplossing;
	
	public AOplossing zoeken(AOplossing oplossing) {
		
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
}
