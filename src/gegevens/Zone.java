package gegevens;

import java.util.*;

public class Zone {
	private int id;
	private ArrayList<Integer> aanliggendId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Integer> getAanliggendId() {
		return aanliggendId;
	}
	public void setAanliggendId(ArrayList<Integer> aanliggendId) {
		this.aanliggendId = aanliggendId;
	}
}
