package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showStatistics();
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

	    setColor(0, 0, 255); 

	    int prevX = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
	    int prevY = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);

	   
	    for (int i = 1; i < gpspoints.length; i++) {
	        int x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        int y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

	        drawLine(prevX, prevY, x, y); 
	        prevX = x;
	        prevY = y;
	    }		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int startY = MARGIN;
		int startX = MARGIN+100;

		setColor(0,0,0);
		setFont("Courier",12);
		
	    startY += TEXTDISTANCE;

	    drawString("Total Time     : " + GPSUtils.formatTime(gpscomputer.totalTime()), startX, startY);
	    startY += TEXTDISTANCE;
	    
	    drawString("Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km", startX, startY);
	    startY += TEXTDISTANCE;

	    drawString("Total elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", startX, startY);
	    startY += TEXTDISTANCE;

	    drawString("Max speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed() * 3.6) + " km/t", startX, startY);
	    startY += TEXTDISTANCE;

	    drawString("Average speed  : " + GPSUtils.formatDouble(gpscomputer.averageSpeed() * 3.6) + " km/t", startX, startY);
	    startY += TEXTDISTANCE;

	    drawString("Energy         : " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", startX, startY);
	    startY += TEXTDISTANCE;

	}
	

	public void replayRoute(int ybase) {

	    int x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
	    int y = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);

	    int circle = fillCircle(x, y, 5); 
	    setSpeed(10); 

	    for (int i = 1; i < gpspoints.length; i++) {
	        x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

	        moveCircle(circle, x, y);
	    }
	}
	}


