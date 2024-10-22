package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] tab = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLatitude();
		}
		return tab;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] tab = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLongitude();
		}
		return tab;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double lat1 = toRadians(gpspoint1.getLatitude());
		double lon1 = toRadians(gpspoint1.getLongitude());
		double lat2 = toRadians(gpspoint2.getLatitude());
		double lon2 = toRadians(gpspoint2.getLongitude());

		double diffLat = lat2 - lat1;
		double diffLon = lon2 - lon1;

		double a = compute_a(lat1, lat2, diffLat, diffLon);
		double c = compute_c(a);
		return R * c;
	}

	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {

		return pow(sin(deltaphi / 2), 2) + cos(phi1) * cos(phi2) * pow(sin(deltadelta / 2), 2);

	}

	private static double compute_c(double a) {

		return 2*atan2(sqrt(a), sqrt(1 - a));

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double distanse = GPSUtils.distance(gpspoint1, gpspoint2);

		return distanse / secs;

	}

	public static String formatTime(int secs) {

		return String.format("  %02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
	}


	public static String formatDouble(double d) {

		return String.format("%10.2f", d); 

	}
}
