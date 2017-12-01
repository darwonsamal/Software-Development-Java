package Classes;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * This class is used to define an Object that is called a WeatherStation. The
 * instance variables that define a Weather Station are an Integer Site Id,
 * String Sitename, Double latitude, and Double longitude. Every Weather Station
 * also has an ArrayList of type WeatherReading, but it doesn't define the
 * object WeatherStation. WeatherStation extends another object called
 * Coordinate which comes From a different class. It inherits it's instance
 * variables and methods. It inherits latitude and longitude and the getters for
 * them.
 * 
 * @author Darwon Rashid - Matric Number: 40280334
 *
 */
public class WeatherStation extends Coordinate {
	private int siteId;
	private String siteName;
	private ArrayList<WeatherReading> weatherReadingList = new ArrayList<>();

	// The parameters that define the object WeatherStation.
	public WeatherStation(int siteId, String siteName, double lat, double lon) {
		super(lat, lon);
		this.siteId = siteId;
		this.siteName = siteName;
	}

	/**
	 * This method returns the Integer SiteId
	 * 
	 * @return Integer SiteId
	 */
	public int getSiteId() {
		return siteId;
	}

	/**
	 * This method returns the String SiteName
	 * 
	 * @return String SiteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * This method returns an ArrayList of type WeatherReading that belongs to
	 * the WeatherStation
	 * 
	 * @return ArrayList<WeatherReading> weatherReadingList
	 */
	public ArrayList<WeatherReading> getWeatherReadingList() {
		return weatherReadingList;
	}

	/**
	 * This method allows the WeatherStation add Objects of WeatherReading Into
	 * it's ArrayList of type WeatherReading
	 * 
	 * @param WeatherReading
	 */
	public void addReading(WeatherReading reading) {
		weatherReadingList.add(reading);
	}

	@Override
	public String toString() {
		return "WeatherStation [siteId=" + siteId + ", siteName=" + siteName + ", weatherReadingList="
				+ weatherReadingList + ", getSiteId()=" + getSiteId() + ", getSiteName()=" + getSiteName()
				+ ", getLat()=" + getLat() + ", getLon()=" + getLon() + ", toString()=" + super.toString() + "]";
	}

}
