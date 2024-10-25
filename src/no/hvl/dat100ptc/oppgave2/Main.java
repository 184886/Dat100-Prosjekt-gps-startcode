package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint gps1 = new GPSPoint(1234, 3.0, 2.0, 4.0);
		GPSPoint gps2 = new GPSPoint(12345, 1.0, 4.0, 8.0);
		GPSPoint gps3 = new GPSPoint(12364, 2.0, 6.0, 12.0);
		
		GPSData Data2 = new GPSData(3);
		Data2.insertGPS(gps1);
		Data2.insertGPS(gps2);
		Data2.insertGPS(gps3);
		Data2.print();
		
		
	}
}
