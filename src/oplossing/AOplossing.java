package oplossing;
import java.io.*;
import java.util.ArrayList;

import gegevens.*;
import oplossing.*;

public class AOplossing implements Cloneable {
	private ArrayList<AReservatie> reservatie;
	private ArrayList<AVoertuig> voertuig;
	
	public AOplossing(ArrayList<AReservatie> areservaties, ArrayList<AVoertuig> avoertuigen) {
		setReservatie(areservaties);
		setVoertuig(avoertuigen);
	}

	public AOplossing() {}

	public ArrayList<AReservatie> getReservatie() {
		return reservatie;
	}

	public void setReservatie(ArrayList<AReservatie> reservatie) {
		this.reservatie = reservatie;
	}

	public ArrayList<AVoertuig> getVoertuig() {
		return voertuig;
	}

	public void setVoertuig(ArrayList<AVoertuig> voertuig) {
		this.voertuig = voertuig;
	}
	
	public AOplossing copy() {
		AOplossing copy = new AOplossing();
		
		ArrayList<AReservatie> copyReservaties = new ArrayList<AReservatie>();	
		for(AReservatie reservatie : this.getReservatie()) {
			AReservatie copyReservatie = new AReservatie();
			copyReservatie.setGewZoneId(reservatie.getGewZoneId());
			copyReservatie.setPenalty1(reservatie.getPenalty1());
			copyReservatie.setPenalty2(reservatie.getPenalty2());
			copyReservatie.setResId(reservatie.getResId());
			
			if(reservatie.getVoertuig() != null ) {
				AVoertuig copyVoertuig = new AVoertuig();
				copyVoertuig.setVoertuigId(reservatie.getVoertuig().getVoertuigId());
				copyVoertuig.setZoneId(reservatie.getVoertuig().getZoneId());
				copyReservatie.setVoertuig(copyVoertuig);
			} else {
				copyReservatie.setVoertuig(null);
			}
			
			
			copyReservatie.setVoertuigId(reservatie.getVoertuigId());
			
			copyReservaties.add(copyReservatie);
		}
		copy.setReservatie(copyReservaties);
		
		ArrayList<AVoertuig> copyVoertuigen = new ArrayList<AVoertuig>();
		for(AVoertuig voertuig : this.getVoertuig()) {
			AVoertuig copyVoertuig = new AVoertuig();
			copyVoertuig.setVoertuigId(voertuig.getVoertuigId());
			copyVoertuig.setZoneId(voertuig.getZoneId());
			
			copyVoertuigen.add(copyVoertuig);
		}
		copy.setVoertuig(copyVoertuigen);
		
		return copy;
	}
	
}
