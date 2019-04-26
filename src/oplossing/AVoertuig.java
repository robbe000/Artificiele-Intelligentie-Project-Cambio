package oplossing;

import java.util.ArrayList;
import gegevens.Reservatie;

public class AVoertuig {
	private int voertuigId;
	private int zoneId;
	private ArrayList<AReservatie> reservaties;
	
	public AVoertuig() {
		this.reservaties = new ArrayList<AReservatie>();
	}
	public int getVoertuigId() {
		return voertuigId;
	}
	public void setVoertuigId(int voertuigId) {
		this.voertuigId = voertuigId;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	
	public AReservatie getReservatie(int index) {
		return reservaties.get(index);
	}
	
	public void addReservatie(AReservatie reservatie) {
		this.reservaties.add(reservatie);
	}
	
	public ArrayList<AReservatie> getReservaties() {
		return reservaties;
	}
	public void setReservaties(ArrayList<AReservatie> reservaties) {
		this.reservaties = reservaties;
	}
	
}
