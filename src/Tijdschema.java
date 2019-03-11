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

	public Tijdschema(ArrayList<Reservatie> reserv) {
		super();
		this.schema = new Boolean[reserv.size()][reserv.size()];
		this.reservaties = reserv;
		genereerSchema(reserv.size());
	}	
	public void genereerSchema(int aantReservaties) {
		for(int x = 0; x < aantReservaties; x++) {
			for(int y = 0; y < aantReservaties; y++) {
				if(x == y) schema[x][y] = true;
				else {
					int dagX = this.getReservaties(x).getDag();
					int startTijdX = this.getReservaties(x).getStartTijd();
					int duurX = this.getReservaties(x).getDuur();
					int dagY = this.getReservaties(y).getDag();
					int startTijdY = this.getReservaties(y).getStartTijd();
					int duurY = this.getReservaties(y).getDuur();
						//1440 min in een dag
					int beginX = (dagX * 1440) + startTijdX;	//de tijd in minuten startend van dag 0
					int eindX = beginX + duurX;
					int beginY = (dagY * 1440) + startTijdY;
					int eindY = beginY + duurY;
					
					if(beginX >= eindY || eindX <= beginY) schema[x][y] = false;
					else schema[x][y] = true;
				}
			}
		}
	}
}
