import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gegevens.*;


public class Readcsv {

	public FileReader fileReader;
	public BufferedReader bufferedReader;
	
	
	
	public Readcsv(String Filelocatie) throws FileNotFoundException {
		super();
		this.fileReader = new FileReader(Filelocatie);
		this.bufferedReader = new BufferedReader(this.fileReader);
	}
	
	public ArrayList<Reservatie> LeesReservaties() throws IOException
	{
		int aantal;
		String lijn = null;
		ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
		
		lijn = bufferedReader.readLine();
		lijn.replaceAll("\\D+", "");
		System.out.println("lijn:");
		System.out.println(lijn);
		
		
		
		
		return reservaties;
	}
	
}
