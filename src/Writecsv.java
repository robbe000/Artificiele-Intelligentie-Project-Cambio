import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public void writeTocsv(int aantal_req, ArrayList<AReservatie> areservaties, ArrayList<AVoertuig> avoertuigen)
	{
		String schrijfweg;
		schrijfweg = Integer.toString(aantal_req);
		try {
			this.bufferedwriter.write(schrijfweg);
			this.bufferedwriter.newLine();
			schrijfweg = "=+Vehicle assignments";
			this.bufferedwriter.newLine();
			
			for(int i = 0; i< avoertuigen.size(); i++) 
			{
				//schrijfweg = "car" + Integer.toString(avoertuigen.get(i).getVoertuigId()) + ";" +
			}
			
			
			
			this.bufferedwriter.close();			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
