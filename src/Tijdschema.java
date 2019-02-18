import java.util.*;

public class Tijdschema {
	private Boolean schema[][];

	public Boolean getSchema(int x, int y) {
		return schema[x][y];
	}

	public void setSchema(int x, int y, Boolean b) {
		this.schema[x][y] = b;
	}

	public Tijdschema(int aantReservaties) {
		super();
		this.schema = new Boolean[aantReservaties][aantReservaties];
		genereerSchema(aantReservaties);
	}	
	public void genereerSchema(int aantReservaties) {
		for(int x = 0; x < aantReservaties; x++)
	}
}
