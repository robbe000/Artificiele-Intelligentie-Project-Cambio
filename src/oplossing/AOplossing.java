package oplossing;
import java.util.ArrayList;

import gegevens.*;
import oplossing.*;

public class AOplossing {
	private ArrayList<AReservatie> reservatie;
	private ArrayList<AVoertuig> voertuig;
	
	public AOplossing(ArrayList<AReservatie> areservaties, ArrayList<AVoertuig> avoertuigen) {
		setReservatie(areservaties);
		setVoertuig(avoertuigen);
	}

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
	

	
	
}
