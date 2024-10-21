package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {

		gpspoints = new GPSPoint[antall];

	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		GPSPoint[] gpoints = this.getGPSPoints();
		for (int x = 0; x < gpoints.length; x++) {
			if (x == antall) {
				gpoints[x] = gpspoint;
				inserted = true;
				this.antall++;
				break;
			}
		}

		return inserted;

	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		this.antall++;

		return true;

	}

	public void print() {

		new GPSData(1).insertGPS(gpspoints[0]);
		new GPSData(2).insertGPS(gpspoints[1]);
		new GPSData(3).insertGPS(gpspoints[2]);

	}
}