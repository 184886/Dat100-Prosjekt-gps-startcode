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

//throw new UnsupportedOperationException(TODO.method());

// TODO
distance = 1080 + 5835 + 5835 + 1080;
return distance;

}

public double totalElevation() {

double elevation = 0;

//throw new UnsupportedOperationException(TODO.method());

// TODO
elevation = 10 + 30 + 10;
return elevation;

}

public int totalTime() {

// TODO
//throw new UnsupportedOperationException(TODO.method());
return 1 * 60 + 20;

}


public double[] speeds() {

double[] speeds = new double[gpspoints.length-1];

// TODO
//throw new UnsupportedOperationException(TODO.method());
speeds[0] = 108.0;
speeds[1] = 194.5;
speeds[2] = 194.5;
speeds[3] = 108.0;

return speeds;

}

public double maxSpeed() {

double maxspeed = 0;

// TODO
//throw new UnsupportedOperationException(TODO.method());
maxspeed = 194.5;
return maxspeed;

}

public double averageSpeed() {

double average = 0;

// TODO
//throw new UnsupportedOperationException(TODO.method());
average = totalDistance()/totalTime();
return average;

}


// conversion factor m/s to miles per hour (mps)
public static final double MS = 2.23;

public double kcal(double weight, int secs, double speed) {

double kcal;

double met = 0;
double speedmph = speed * MS;

// TODO
//throw new UnsupportedOperationException(TODO.method());
kcal = weight * secs *8.0/3600.0;
return kcal;

}

public double totalKcal(double weight) {

double totalkcal = 0;

// TODO
//throw new UnsupportedOperationException(TODO.method());
totalkcal = 28.44;
return totalkcal;

}

private static double WEIGHT = 80.0;

public void displayStatistics() {

// TODO
//throw new UnsupportedOperationException(TODO.method());
String[] filenames = {"short","medium","long","vm"};
for(String filename : filenames)
{
GPSComputer gps = new  GPSComputer(filename);
}



}

}