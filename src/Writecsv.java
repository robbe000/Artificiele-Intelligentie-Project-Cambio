import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import LokaalZoeken.Tijdschema;
import gegevens.Reservatie;
import gegevens.Voertuig;
import gegevens.Zone;
import oplossing.AReservatie;
import oplossing.AVoertuig;

public class Writecsv {

	private FileWriter filewriter;
	private BufferedWriter bufferedwriter;
	


		
	public Writecsv(String Filelocatie){
		super();
		
			try {
				this.filewriter = new FileWriter(Filelocatie);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		this.bufferedwriter = new BufferedWriter(this.filewriter);
	}
	
	public void writeTocsv(int kost, ArrayList<AReservatie> areservaties, ArrayList<AVoertuig> avoertuigen)
	{
		String schrijfweg;
		schrijfweg = Integer.toString(kost);
		try {
			this.bufferedwriter.write(schrijfweg);
			this.bufferedwriter.newLine();
			
			
			
			
			schrijfweg = "=+Vehicle assignments";
			this.bufferedwriter.write(schrijfweg);
			this.bufferedwriter.newLine();
			
			for(int i = 0; i< avoertuigen.size(); i++) 
			{
				schrijfweg = "car" + Integer.toString(avoertuigen.get(i).getVoertuigId()) + ";"+ "z" + avoertuigen.get(i).getZoneId();
				this.bufferedwriter.write(schrijfweg);
				this.bufferedwriter.newLine();
			}
			
			schrijfweg = "=+Assigned requests";
			this.bufferedwriter.write(schrijfweg);
			this.bufferedwriter.newLine();			
	
			for(int i = 0; i< areservaties.size(); i++) 
			{
				if(areservaties.get(i).getVoertuigId() != null)
				{
					schrijfweg = "req" + Integer.toString(areservaties.get(i).getResId()) + ";" + "car" +areservaties.get(i).getVoertuigId();
					this.bufferedwriter.write(schrijfweg);
					this.bufferedwriter.newLine();
				}
			}
			
			
			schrijfweg = "=+Unassigned requests";
			this.bufferedwriter.write(schrijfweg);
			this.bufferedwriter.newLine();			
	
			for(int i = 0; i< areservaties.size(); i++) 
			{
				if(areservaties.get(i).getVoertuigId() == null)
				{
					schrijfweg = "req" + Integer.toString(areservaties.get(i).getResId());
					this.bufferedwriter.write(schrijfweg);
					this.bufferedwriter.newLine();
				}
			}		
			
			
			this.bufferedwriter.close();			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void writeTimetable(Tijdschema tijdschema)
	{
		String schrijfweg = "";
		for(int i = 0; i< 100; i++)
		{
			for(int j = 0 ;j < 100; j++)
			{
				schrijfweg += tijdschema.getSchema(i, j).toString() + ";";
			}
			try {
				this.bufferedwriter.write(schrijfweg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.bufferedwriter.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			schrijfweg = "";
		}
		
		try {
			this.bufferedwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
