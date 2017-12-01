package Classes;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * This class is used to define an Object that is called a Postcode.
 * The instance variables that define a Weather Station are String PostcodeId, latitude and longitude.
 * Postcode extends another class called Coordinate, it inherits it's instance variables which are
 * Latitude and longitude and it also inherits it's methods. We use
 * this object for Section 3 of the course work.
 * 
 * @author Darwon Rashid - Matric Number: 40280334
 *
 */

public class Postcode extends Coordinate {
	private String postcodeId;

	// The public parameters of a Postcode, to create a new Postcode, it must take in a 
	//Latitude and a longitude.
	//And since it extends the class Coordinate, it inherits the instance variables 
	//From class Coordinate which for this context are Latitude and Longitude.
	public Postcode(double lat, double lon, String postcodeId) {
		super(lat, lon);

		this.postcodeId = postcodeId;
	}
	
    
	/**
	 * A method that returns a String PostcodeId for use.
	 * @return String PostcodeId
	 */
	public String getPostcodeId() {
		return postcodeId;
	}

	@Override
	/**
	 * A method that prints out the information of the Postcode, A string representation of the Object
	 */
	public String toString() {
		return "Postcode [PostcodeId=" + postcodeId + ", getLat()=" + getLat() + ", getLon()=" + getLon() + "]";
	}

}
