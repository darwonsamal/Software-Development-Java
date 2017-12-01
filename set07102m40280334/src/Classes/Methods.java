package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import mapgui.MapGui;
import weather.WeatherData;

public class Methods {
	/**
	 * Methods that answers questions from section 3 of the Course work.
	 * 
	 * @author Darwon Rashid - Matric number : 40280334.
	 */

	public static void main(String[] args) {

		methodForAnswer11();
		methodForAnswer12();
		methodForAnswer13();
		methodForAnswer14();
		methodForAnswer15();		
	}

	/**
	 * Method used to find how many Postcodes are within a 5KM radius of the
	 * most northerly weather station and then to plot them on a map, we first
	 * have our variables that we are going to use for the code below, then we
	 * have the first part of the method to find the most northerly weather
	 * station. The second part is to find the Postcodes within 5KM of the
	 * weather station and to plot them.
	 */
	public static void methodForAnswer11() {
		/**
		 * We first must find the most northerly Weather Station and you do that
		 * using the code below, we have the variables below to use for the
		 * method.
		 */
		int count = 0;
		double comparelat = 0;
		double comparelon = 0;
		HashMap<Integer, WeatherStation> wshash = Data.getWeatherStation();
		double compare = 0;
		int siteidr = 0;
		String sitenamer = null;
		double postlat = 0;
		double postlon = 0;
		// For loop that takes the values from the hashmap wshash into a
		// WeatherStation object for use.
		for (WeatherStation wS : wshash.values()) {

			// We use the these variables for comparing.
			String sitename = wS.getSiteName();
			int siteid = wS.getSiteId();
			double lat = wS.getLat();
			// We use an if statement to compare to find the highest latitude
			// which indicates the most northerly weather station.
			// We use the siteidr and sitenamer to find which weather station
			// It is.
			if (lat > compare) {
				compare = lat;
				siteidr = siteid;
				sitenamer = sitename;

			}

		}
		System.out.println("The most northerly weather station is " + sitenamer + " And the site id is " + siteidr);

		/**
		 * Then we have to find the postcodes that are near the weather station,
		 * and plot them on the map, we do that by the code below
		 */

		// A list of coordinates that will be displayed on a map
		ArrayList<Coordinate> coordinates = new ArrayList<>();
		// We use the HashMap that has all the Postcodes in it.
		// It has a String ID as a key and a Postcode as a value.
		HashMap<String, Postcode> pchash = Data.getPostcode();
		// We make a for loop to go over the HashMap with all the Postcodes
		// inside it.
		for (String key : pchash.keySet()) {
			// We use the String key as a key to access the latitudes and
			// longitudes of the Postcodes.
			postlat = pchash.get(key).getLat();
			postlon = pchash.get(key).getLon();

			// We compare the latitudes and longitudes of the most northerly
			// weather station to all the
			// post codes within a 5 KM distance.

			// The latitude and longitdue of the most northerly weather station.
			double northlat = wshash.get(siteidr).getLat();
			double northlon = wshash.get(siteidr).getLon();
			// The distance method.
			double distance = WeatherData.getDistanceBetweenPoints(northlat, northlon, postlat, postlon);
			// If statement that when the condition is met, the counter goes up
			// by one each time and we use the comparelat and comparelon to find
			// The Postcode coordinates that are withtin a 5KM distance of the
			// most northerly weather station.

			if (distance <= 5) {
				count++;
				// We add the comparelat and comparelon into a new Coordinate
				// object of which we then add into an ArrayList of type
				// Coordinate.
				// We have this to later on pot the coordinates on the map.
				comparelat = postlat;
				comparelon = postlon;
				Coordinate coord1 = new Coordinate(comparelat, comparelon);
				coordinates.add(coord1);

			}

		}

		// Now we print out how many Postcodes are within a 5KM distance by
		// using the counter.
		System.out.println(

				"The number of postcodes within a 5KM distance of the most northerly weather station is " + count);
		// All the postcodes added into the ArrayList will now be plotted into
		// the map.
		MapGui.showMap(coordinates);
	}

	/**
	 * Method used to find which weather station has the most consecutive
	 * weather reading windpseeds over 50 First, You have to use a counter that
	 * Stores the windspeed readings over 50, and the second the next reading is
	 * below 50, the counter will reset to 0 so you can find which
	 * Weatherstation has the most Consecutive windspeed readings above 50. Also
	 * must plot it on the map.
	 */

	public static void methodForAnswer12() {

		HashMap<Integer, WeatherStation> wshash = Data.getWeatherStation();
		

		int count = 0;
		int memory = 0;
		int current = 0;
		String sitename = null;
		double lat = 0;
		double lon = 0;
		// for loop to get every weather station and it's values.
		for (WeatherStation wS : wshash.values()) {
			// A second for loop to access the values within the weather reading
			// list.
			for (int i = 0; i < wS.getWeatherReadingList().size(); i++) {
				double latb = wS.getLat();
				double lonb = wS.getLon();
				int windspeed = wS.getWeatherReadingList().get(i).getWindSpeed();
				String sitenameb = wS.getSiteName();
				// compare to find the answer.
				// if there is a windspeed reading that is above 50, the counter
				// will go up by one
				// As long as the windspeed is above 50, When the next windspeed
				// is less
				// The counter will reset to 0.
				if (windspeed > 50) {
					count++;
					memory = count;
				} else {
					count = 0;
				}
				// We now have to find the most consecutive windspeed reading
				// above 50
				// To do that, we have to compare to find the biggest counter
				// which is
				// memory below.
				// Then we use sitename, lat, and lon to find the
				// WeatherStation.
				if (memory > current) {
					current = memory;
					sitename = sitenameb;
					lat = latb;
					lon = lonb;
				}

			}

		}
		// We print out the name of the WeatherStation
		System.out.println(
				"The weather station that has a wind speed of more than 50KMH for the most consecutive readings is "
						+ sitename);
		// Add the lat and lon of the WeatherStation into a new Coordinate
		Coordinate coord1 = new Coordinate(lat, lon);
		// Then we plot the Coordinate on the map
		MapGui.showMap(coord1);
	}

	/**
	 * Method to find the temperature medians of the weather stations 3768 and
	 * 3166 during the month July and then to see the difference between them,
	 * and of course to plot them. You first have to find the median of each
	 * weather station and then just minus them from each other to find the
	 * difference. Also to plot them on the map.
	 */

	public static void methodForAnswer13() {
		// Variables that are used for the method
		HashMap<Integer, WeatherStation> wshash = Data.getWeatherStation();
		double temp3768 = 0;
		double temp3166 = 0;

		ArrayList<Double> temp3768list = new ArrayList<>();
		ArrayList<Double> temp3166list = new ArrayList<>();
		double median3768 = 0;
		double median3166 = 0;
		// For loop for weather station 3768.
		for (int i = 0; i < wshash.get(3768).getWeatherReadingList().size(); i++) {

			int july3768 = wshash.get(3768).getWeatherReadingList().get(i).getMonth();
			// condition for going over the temperature readings
			if (july3768 == 7) {

				temp3768 = wshash.get(3768).getWeatherReadingList().get(i).getTemperature();
				// Add them to an arraylist
				temp3768list.add(temp3768);

			}

		}
		// Sort the temperature readings in the ArrayList in ascending order
		Collections.sort(temp3768list);
		// FInd the median
		int middlea = temp3768list.size() / 2;
		if (temp3768list.size() % 2 == 1) {
			median3768 = temp3768list.get(middlea);
		} else {

			median3768 = ((temp3768list.get(middlea - 1) + temp3768list.get(middlea)) / 2.0);
		}

		// For loop for weather station 3166
		for (int i = 0; i < wshash.get(3166).getWeatherReadingList().size(); i++) {
			int july3166 = wshash.get(3166).getWeatherReadingList().get(i).getMonth();
			// Condition for going over the temperature readings
			if (july3166 == 7) {
				temp3166 = wshash.get(3166).getWeatherReadingList().get(i).getTemperature();
				// Add them to an arraylist
				temp3166list.add(temp3166);

			}
		}
		// Sort the temperature readings in the arrat list in ascending order
		Collections.sort(temp3166list);
		// Find the median
		int middleb = temp3166list.size() / 2;
		if (temp3166list.size() % 2 == 1) {
			median3166 = temp3166list.get(middleb);
		} else {

			median3166 = ((temp3166list.get(middleb - 1) + temp3166list.get(middleb)) / 2.0);
		}
		// plot the weather stations and find the difference.
		// First, get the lats and lons of both WeatherStations.
		double lat3768 = wshash.get(3768).getLat();
		double lat3166 = wshash.get(3166).getLat();
		double lon3768 = wshash.get(3768).getLon();
		double lon3166 = wshash.get(3166).getLon();
		// Make two new Coordinate objects.
		Coordinate coord3768 = new Coordinate(lat3768, lon3768);
		Coordinate coord3166 = new Coordinate(lat3166, lon3166);
		// Make a new ArrayList of type Coordinate.
		ArrayList<Coordinate> c = new ArrayList<>();
		// Add Coordinate objects into the ArrayList.
		c.add(coord3166);
		c.add(coord3768);
		// Plot them on the map.
		MapGui.showMap(c);
		// Print the WeatherStations out.

		System.out.println("The median for weather station Farnborough is " + median3768);
		System.out.println("The median for weather station Gogarbank is " + median3166);
		// Print out the difference
		System.out.println("The difference between the weather station medians are " + (median3768 - median3166));

	}

	/**
	 * Method to find the most isolated EH Postcode. You have to go over each EH
	 * Postcode and find it's closest neighbor and then the one with the
	 * farthest distance to it's closest neighbor is the most isolated EH
	 * Postcode.
	 */
	public static void methodForAnswer14() {
		// Variables to be used for the method.
		HashMap<String, Postcode> ehhash = Data.getEHpostcode();

		double mind = 0;
		double maxd = 0;
		Postcode mostisolatedp = null;
		Postcode p1 = null;
		// For loop for the first EH postcode.
		for (String key : ehhash.keySet()) {

			String p1id = ehhash.get(key).getPostcodeId();
			double flat = ehhash.get(key).getLat();
			double flon = ehhash.get(key).getLon();

			p1 = new Postcode(flat, flon, p1id);
			mind = Double.MAX_VALUE;
			// Second for loop for the second EH postcode
			for (String keyb : ehhash.keySet()) {

				String p2id = ehhash.get(keyb).getPostcodeId();
				double slat = ehhash.get(keyb).getLat();
				double slon = ehhash.get(keyb).getLon();

				Postcode p2 = new Postcode(slat, slon, p2id);
				// Now to compare distances between one EH postcode and every
				// other one and so on.
				double d = WeatherData.getDistanceBetweenPoints(flat, flon, slat, slon);
				// We use an if statement so that we find the closest neighbor
				// and
				// make sure that the first Postcode doesn't compare to itself.
				if (d < mind && !p1.getPostcodeId().equals(p2.getPostcodeId())) {

					mind = d;

					if (mind < 0) {
						break;
					}

				}

			}
			// Then we compare to find the EH Postcode with the farthest
			// distance to it's closest neighbor
			if (maxd < mind) {

				maxd = mind;
				mostisolatedp = p1;
			}

		}
		// Finally, we plot and print it out.
		// We get the lat and lon of the EH Postcode
		double lat = mostisolatedp.getLat();
		double lon = mostisolatedp.getLon();
		// Make a new Coordinate with the lat and lon.
		Coordinate coord = new Coordinate(lat, lon);
		// Plot it on the map.
		MapGui.showMap(coord);
		// We print it out.
		System.out.println("The most isolated EH Postcode is " + mostisolatedp);
		System.out.println("The distance to its closet neighbor is " + maxd);

	}

	/**
	 * Method to find which EH Postcode is the most populated and to find that
	 * we we must find the EH Postcode that has the most neighbors around it in
	 * a 0.1 KM distance.
	 */
	public static void methodForAnswer15() {
		// Variables to use for the method.
		HashMap<String, Postcode> ehhash = Data.getEHpostcode();
		int count = 0;
		double lat = 0;
		double lon = 0;
		int compare = 0;
		int memory = 0;
		Postcode p1 = null;
		Postcode mostpopulated = null;
		String p1id = null;
		// Just like Method 14, we make a for loop for the first Postcode.
		for (String key : ehhash.keySet()) {
			// Make a Postcode, so we get PostcodeId, lat, and lon.
			p1id = ehhash.get(key).getPostcodeId();
			double flat = ehhash.get(key).getLat();
			double flon = ehhash.get(key).getLon();

			p1 = new Postcode(flat, flon, p1id);
			// Counter reset for the next EH Postcode
			count = 0;
			// We then make the second for loop for the second postcode.
			for (String keyb : ehhash.keySet()) {
				// Make a Postcode, so we get PostcodeId, lat, and lon.
				String p2id = ehhash.get(keyb).getPostcodeId();
				double slat = ehhash.get(keyb).getLat();
				double slon = ehhash.get(keyb).getLon();

				Postcode p2 = new Postcode(slat, slon, p2id);
				// Now to compare distances between the first EH postcode and
				// every other on and so on.
				double d = WeatherData.getDistanceBetweenPoints(flat, flon, slat, slon);
				// We use an if statement to find the most populated EH postcode
				// and
				// Make sure the given Postcode doesn't compare distances with
				// itself.
				if (d <= 0.1 && !p1.getPostcodeId().equals(p2.getPostcodeId())) {
					// Count to see how many EH Postcodes are within a 0.1 KM
					// distance of a
					// Given EH Postcode.
					count++;

				}
          		memory = count;
			}
			// Then we compare to see which has the most neighbors around it in
			// a 0.1KM distance, and we do that by finding the biggest counter.
			if (compare < memory) {
				compare = memory;

				mostpopulated = p1;
     
			}
		}
		// Finally to plot and print it out.
		// Get the lat and lon of the mostpopulated EH Postcode
		// for a new Coordinate.
		lat = mostpopulated.getLat();
		lon = mostpopulated.getLon();
		Coordinate coord = new Coordinate(lat, lon);
		// Plot it on the map.
		MapGui.showMap(coord);
		// Print it out.
		System.out.println("The most isolated EH postcode is " + mostpopulated);
	}
}