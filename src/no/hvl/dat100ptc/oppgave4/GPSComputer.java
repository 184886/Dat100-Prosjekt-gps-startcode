package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;

	}

	public double totalElevation() {

		double elevation = 0;
		double eldiff = 0;
		for (int i = 1; i < gpspoints.length; i++) {
			eldiff = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			if (eldiff > 0)
				elevation += eldiff;
		}
		return elevation;

	}

	public int totalTime() {

		if (gpspoints.length < 2) {
			return 0;
		}
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
	}

	public double[] speeds() {

		if (gpspoints.length < 2) {
			return new double[0];
		}

		double[] speeds = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return speeds;

	}

	public double maxSpeed() {

		double maxspeed = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			maxspeed = Math.max(GPSUtils.speed(gpspoints[i], gpspoints[i + 1]), maxspeed);
		}
		return maxspeed;

	}

	public double averageSpeed() {

		return totalDistance() / totalTime();

	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;
		double met = 0;
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}

		kcal = met * weight * secs / 3600.0;
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		for (int i = 0; i < gpspoints.length-1; i++) {
			totalkcal += kcal(weight, gpspoints[i+1].getTime()-gpspoints[i].getTime(), speeds()[i]);
		}
		return totalkcal;
		
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

System.out.println("==============================================");

System.out.println(
				"Total Time     :" + GPSUtils.formatTime(totalTime()) + "\n" +
				"Total distance :" + GPSUtils.formatDouble(totalDistance()/1000) + " km\n" + 
				"Total elevation:" + GPSUtils.formatDouble(totalElevation()) + " m\n" +
				"Max speed      :" + GPSUtils.formatDouble(maxSpeed()*3.6) + " km/t\n" +
				"Average speed  :" + GPSUtils.formatDouble(averageSpeed()*3.6) + " km/t\n" +
				"Energy         :" + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal\n");

System.out.println("==============================================");

	}

}
