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
		return (AOplossing) this.deepCopy(this);
	}

	/**
	 * Makes a deep copy of any Java object that is passed.
	 */
	 private Object deepCopy(Object object) {
	   try {
	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	     ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
	     outputStrm.writeObject(object);
	     ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	     ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
	     return objInputStream.readObject();
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	 }
	
}
