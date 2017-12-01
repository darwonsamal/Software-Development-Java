package Classes;

/**
 * This class is used to define an Object that is called a WeatherReading. The
 * instance variables that define a WeatherReading are an Integer Site Id,
 * Integer year, Integer month, Integer date, Integer hour, Integer windspeed, and
 * Double temperature. Every WeatherReading object belongs to a WeatherStation object.
 * 
 * 
 *
 * @author Darwon Rashid - Matric Number: 40280334
 *
 */

public class WeatherReading {
	private int siteId;
	private int year;
	private int month;
	private int date;
	private int hour;
	private int windSpeed;
	private double temperature;
	// The parameters that define the object WeatherReading.
	public WeatherReading(int siteId, int year, int month, int date, int hour, int windSpeed, double temperature) {
        
		this.siteId = siteId;
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.windSpeed = windSpeed;
		this.temperature = temperature;
	}
    /**This method returns an Integer siteId
     * 
     * @return Integer siteId
     */
	public int getSiteId() {
		return siteId;
	}
    /**
     * This method returns an Integer year
     * 
     * @return Integer year
     */
	public int getYear() {
		return year;
	}
    /**
     * This method returns an Integer month
     * 
     * @return Integer month
     */
	public int getMonth() {
		return month;
	}
    /**
     * This method returns an Integer date
     * 
     * @return Integer date
     */
	public int getDate() {
		return date;
	}
    /**
     * This method returns an Integer hour
     * @return Integer hour
     * 
     */
	public int getHour() {
		return hour;
	}
    /**
     * This method returns an Integer windspeed
     * @return Integer windspeed
     */
	public int getWindSpeed() {
		return windSpeed;
	}
    /**
     * This method returns a Double temperature
     * @return Double temperature
     */
	public double getTemperature() {
		return temperature;
	}

	public String toString() {
		return "WeatherReading : The year is " + year + ", The month is " + month + ", The date is " + date
				+ ", The hour is " + hour + ", The windspeed is " + windSpeed + ", The temperature is " + temperature;
	}

}
