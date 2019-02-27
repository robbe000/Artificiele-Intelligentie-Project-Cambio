import java.util.*;

import gegevens.*;

public class Tijdschema {
	private Boolean schema[][];
	private ArrayList<Reservatie> reservaties;

	public Boolean getSchema(int x, int y) {
		return schema[x][y];
	}

	public void setSchema(int x, int y, Boolean b) {
		this.schema[x][y] = b;
	}
	
	public Reservatie getReservaties(int i) {
		return reservaties.get(i);
	}

	public Tijdschema(int aantReservaties, ArrayList<Reservatie> reserv) {
		super();
		this.schema = new Boolean[aantReservaties][aantReservaties];
		this.reservaties = reserv;
		genereerSchema(aantReservaties);
	}	
	public void genereerSchema(int aantReservaties) {
<<<<<<< HEAD
		for(int x = 0; x < aantReservaties; x++);
=======
		for(int x = 0; x < aantReservaties; x++) {
			for(int y = 0; y < aantReservaties; y++) {
				if(x == y) schema[x][y] = true;
				else {
					
				}
			}
		}
>>>>>>> 9dafb720cdb49af0afbb0967ce1f3c844bc94472
	}
}
