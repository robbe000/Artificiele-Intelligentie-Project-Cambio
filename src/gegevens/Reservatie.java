package gegevens;
import java.util.*;

public class Reservatie {
	private int id;
	private int zoneId;
	private int dag;
	private int duur;
	private ArrayList<Integer> voertuigID;
	private int penalty1;
	private int penalty2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public int getDag() {
		return dag;
	}
	public void setDag(int dag) {
		this.dag = dag;
	}
	public int getDuur() {
		return duur;
	}
	public void setDuur(int duur) {
		this.duur = duur;
	}
	public ArrayList<Integer> getVoertuigID() {
		return voertuigID;
	}
	public void setVoertuigID(ArrayList<Integer> voertuigID) {
		this.voertuigID = voertuigID;
	}
	public int getPenalty1() {
		return penalty1;
	}
	public void setPenalty1(int penalty1) {
		this.penalty1 = penalty1;
	}
	public int getPenalty2() {
		return penalty2;
	}
	public void setPenalty2(int penalty2) {
		this.penalty2 = penalty2;
	}
}
