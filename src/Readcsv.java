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
		String[] lijndelen;
		String[] mog_wagens;
		ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
		
		try {
			lijn = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lijn = lijn.replaceAll("[^0-9]+", "");
		System.out.println("lijn:");
		System.out.println(lijn);
		
		aantal = Integer.parseInt(lijn);
		
		
		//binnenhalen van reservaties
		Reservatie temp;
		for(int i = 0; i< aantal; i++)
		{
			temp = new Reservatie();
			mog_wagens = null;
			try 
			{
				lijn = bufferedReader.readLine();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			lijndelen = lijn.split(";");

			//ID van reservatie
			temp.setId(Integer.parseInt(lijndelen[0].replaceAll("[^0-9]+", "")));
			
			//zone van reservatie
			temp.setZoneId(Integer.parseInt(lijndelen[1].replaceAll("[^0-9]+", "")));
			
			//dag van reservatie
			temp.setDag(Integer.parseInt(lijndelen[2].replaceAll("[^0-9]+", "")));
			
			//starttijd
			temp.setStartTijd(Integer.parseInt(lijndelen[3].replaceAll("[^0-9]+", "")));
			
			//duurtijd
			temp.setDuur(Integer.parseInt(lijndelen[4].replaceAll("[^0-9]+", "")));
			
			//mogelijke wagens
			mog_wagens = lijndelen[5].split(",");
			for(int j = 0; j< mog_wagens.length; j++)
			{
				temp.AddVoertuig(Integer.parseInt(mog_wagens[j].replaceAll("[^0-9]+", "")));
			}
			
			//penalty 1
			temp.setPenalty1(Integer.parseInt(lijndelen[6].replaceAll("[^0-9]+", "")));	
			
			
			//penalty 2
			temp.setPenalty2(Integer.parseInt(lijndelen[7].replaceAll("[^0-9]+", "")));
			
			reservaties.add(temp);
		}

		return reservaties;
	}
	
	
	public ArrayList<Zone> LeesZones()
	{
		ArrayList<Zone> zones = new ArrayList<Zone>();
		
		int aantal;
		String lijn = null;
		String[] lijndelen;
		String[] mog_wagens;	
		try {
			lijn = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lijn = lijn.replaceAll("[^0-9]+", "");
		System.out.println("lijn:");
		System.out.println(lijn);
		
		aantal = Integer.parseInt(lijn);
		
		Zone temp;
		for(int i = 0; i< aantal; i++)
		{	
			temp = new Zone();
			try 
			{
				lijn = bufferedReader.readLine();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			lijndelen = lijn.split(";");

			//ID van reservatie
			temp.setId(Integer.parseInt(lijndelen[0].replaceAll("[^0-9]+", "")));

			//mogelijke wagens
			mog_wagens = lijndelen[1].split(",");
			for(int j = 0; j< mog_wagens.length; j++)
			{
				temp.AddAanliggend(Integer.parseInt(mog_wagens[j].replaceAll("[^0-9]+", "")));
			}
			
			zones.add(temp);
		}
		return zones;
	}
	
	public ArrayList<Voertuig> LeesVoertuigen()
	{
		ArrayList<Voertuig> voertuigen = new ArrayList<Voertuig>();
		
		int aantal;
		String lijn = null;
		String[] lijndelen;
		String[] mog_wagens;
		
		try {
			lijn = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lijn = lijn.replaceAll("[^0-9]+", "");
		System.out.println("lijn:");
		System.out.println(lijn);
		
		aantal = Integer.parseInt(lijn);
		
		Voertuig temp;
		
		for(int i = 0; i< aantal; i++)
		{	
			temp = new Voertuig();
			try 
			{
				lijn = bufferedReader.readLine();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			temp.setId(Integer.parseInt(lijn.replaceAll("[^0-9]+", "")));
			voertuigen.add(temp);
		}
		
		return voertuigen;
	}
	
	public void closeLees()
	{
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
