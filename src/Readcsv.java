import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gegevens.*;


public class Readcsv {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	
	
	
	public Readcsv() {
		super();
	}

		
	public Readcsv(String Filelocatie){
		super();
		try {
			this.fileReader = new FileReader(Filelocatie);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bufferedReader = new BufferedReader(this.fileReader);
	}
	
	public ArrayList<Reservatie> LeesReservaties()
	{
		int aantal;
		String lijn = null;
		ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
		
		try {
			lijn = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lijn.replaceAll("\\D+", "");
		System.out.println("lijn:");
		System.out.println(lijn);
		
		
		Reservatie test = new Reservatie();
		test.setDag(12);
		reservaties.add(test);
		return reservaties;
	}
	
}
