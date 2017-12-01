package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import weather.WeatherData;

/**
 * This class returns the Weather Data(which consists of Weather Stations that
 * have weather readings) all into one single Hash Map that you can choose to
 * call to use in your methods.
 * 
 * @author Darwon Rashid - Matric Number: 40280334
 *
 */
public class Data {

	/**
	 * You split the data and then put them all in a hashmap, but first, you
	 * have to put in the weather stations, and then later on add the weather
	 * readings into the weather stations.
	 * 
	 * 
	 * * @return HashMap that consists of a key that is a Site ID and the value
	 * is a weather station that has an array list of weather readings.
	 */

	public static HashMap<Integer, WeatherStation> getWeatherStation() {
		// Create a very cool HashMap
		HashMap<Integer, WeatherStation> weatherstations = new HashMap<>();

		// Get the funky data as an array of strings
		String[] data = WeatherData.getData();

		// Loop through each element of the array except index 0 cause it ain't
		// cool for they are headers
		for (int i = 1; i < data.length; i++) {

			// get the next line
			String line = data[i];

			// Split the string
			String[] elements = line.split(",");

			// The Site ID and Site Name are the first two elements, we have to
			// convert siteid to type Integer my Padawan
			String siteidstring = elements[0];
			String sitename = elements[1];

			int siteid = Integer.parseInt(siteidstring);

			// Lat and lon are type double, so we have to convert
			String latstring = elements[2];
			String lonstring = elements[3];

			double lat = Double.parseDouble(latstring);
			double lon = Double.parseDouble(lonstring);

			// create a new WeatherStation instance of sort mate to add the
			// parameters properties in it.
			WeatherStation ws = new WeatherStation(siteid, sitename, lat, lon);
			// Add to HashMap
			weatherstations.put(siteid, ws);

		}
		// Now for the weather reading to be added into WeatherStation
		for (int i = 1; i < data.length; i++) {

			// get the next line
			String line = data[i];

			// Split the string
			String[] elements = line.split(",");

			// The Site ID is the first element, we have to
			// convert siteid to type Integer my Padawan
			String siteidstring = elements[0];

			int siteid = Integer.parseInt(siteidstring);
			// These are for the WeatherReading,
			// While latitude and longitude were the third and forth elements,
			// The rest are as follows (We start from element[0])
			String yearstring = elements[4];
			String monthstring = elements[5];
			String datestring = elements[6];
			String hourstring = elements[7];
			String windspeedstring = elements[8];
			String temperaturestring = elements[9];
			// Convert to their right type
			int year = Integer.parseInt(yearstring);
			int month = Integer.parseInt(monthstring);
			int date = Integer.parseInt(datestring);
			int hour = Integer.parseInt(hourstring);
			int windspeed = Integer.parseInt(windspeedstring);
			double temp = Double.parseDouble(temperaturestring);
			// Make a new WeatherStation Instance and populate it
			WeatherStation weather = weatherstations.get(siteid);
			// Make a new WeatherReading Instance and add the parameter properties into it
			WeatherReading reading = new WeatherReading(siteid, year, month, date, hour, windspeed, temp);
			//Add object WeatherReading reading into WeatherStation weather
			weather.addReading(reading);

		}

		return weatherstations;
	}

	/**
	 * Gets all the post code information into a hashmap for use in the methods.
	 * 
	 * @return
	 * 
	 * 		HashMap that consists of a String as a key and a Postcode object
	 *         as a value.
	 */

	public static HashMap<String, Postcode> getPostcode() {

		// We make a new hashmap to put our stuff in
		HashMap<String, Postcode> posthash = new HashMap<>();

		// A try catch block is needed to catch any file input errors
		try {

			// We use a Buffered Reader to read the file line by line
			BufferedReader reader = new BufferedReader(new FileReader(new File("postcodes.csv")));

			String line;

			// continue reading until null is returned when the end of the file
			// has been reached
			while ((line = reader.readLine()) != null) {

				// we can use the split method of String to extract the
				// individual fields from the comma delimited line to an array
				String[] data = line.split(",");
				// fields 0 hold the postcode name
				// fields 1 and 2 hold the latitude and longitude which need
				// converted to type double
				// create a new Coordinate and add it to a list of coordinates
				String postcodename = data[0];
				String latstring = data[1];
				String lonstring = data[2];

				double postlat = Double.parseDouble(latstring);
				double postlon = Double.parseDouble(lonstring);

				Postcode pc = new Postcode(postlat, postlon, postcodename);
				posthash.put(postcodename, pc);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return posthash;

	}

	/**
	 * Gets only the EH post code information into a HashMap for use in the
	 * methods.
	 * 
	 * @return HashMap that consists of a String as a key and a EH Postcode
	 *         object as a value.
	 * 
	 */
	public static HashMap<String, Postcode> getEHpostcode() {

		// We make a new hashmap to put our stuff in
		HashMap<String, Postcode> ehpostcode = new HashMap<>();

		// A try catch block is needed to catch any file input errors
		try {

			// We use a Buffered Reader to read the file line by line
			BufferedReader reader = new BufferedReader(new FileReader(new File("eh.csv")));

			String line;

			// continue reading until null is returned when the end of the file
			// has been reached
			while ((line = reader.readLine()) != null) {

				// we can use the split method of String to extract the
				// individual fields from the comma delimited line to an array
				String[] data = line.split(",");
				// fields 0 hold the EH postcode name
				// fields 1 and 2 hold the latitude and longitude which need
				// converted to type double
				// create a new Coordinate and add it to a list of coordinates
				String postcodename = data[0];
				String latstring = data[1];
				String lonstring = data[2];

				double postlat = Double.parseDouble(latstring);
				double postlon = Double.parseDouble(lonstring);

				Postcode pc = new Postcode(postlat, postlon, postcodename);
				ehpostcode.put(postcodename, pc); 

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return ehpostcode;

	}

}
