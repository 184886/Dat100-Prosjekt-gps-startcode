package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int hr = Integer.parseInt(timestr.substring(11,13));
		int min = Integer.parseInt(timestr.substring(14,16));
		int sec = Integer.parseInt(timestr.substring(17,19));
		
		int secs = hr*60*60 + min*60 + sec;
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int time = toSeconds(timeStr);
		double lat = Double.parseDouble(latitudeStr);
		double lon = Double.parseDouble(longitudeStr);		
		double ele = Double.parseDouble(elevationStr);
		
		GPSPoint gpspoint = new GPSPoint(time, lat, lon, ele);
		
		return gpspoint;
	}
	
}
