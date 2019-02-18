import java.util.*;

import gegevens.*;
import oplossing.*;

public class Main {

	private ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
	private ArrayList<Voertuig> voertuigen = new ArrayList<Voertuig>();
	private ArrayList<Zone> zones = new ArrayList<Zone>();
	private ArrayList<AReservatie> areservaties = new ArrayList<AReservatie>();
	private ArrayList<AVoertuig> avoertuigen = new ArrayList<AVoertuig>();
	
	private static Readcsv lees = new Readcsv("test");

	public static void main(String[] args) {		
		
		reservaties = lees.LeesReservaties();
	}

}
