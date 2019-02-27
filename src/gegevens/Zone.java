package gegevens;

import java.util.*;

public class Zone {
	private int id;
	private ArrayList<Integer> aanliggendId;
	
	
	
	public Zone() {
		super();
		this.aanliggendId = new ArrayList<Integer>();
	}
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
	
	public void AddAanliggend(int id)
	{
		this.aanliggendId.add(id);
	}
}
