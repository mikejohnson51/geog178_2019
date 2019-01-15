
public class Example2 {

		public static void main(String[] args) {
			// Locations of interest given as a String variables
			String loc1 = "UCSB";
			String loc2 = "Cheyenne Mountain"; // Enter your data!
			
			// The latitudes given as a double variable in radians
			// This is done using the 'toRadians' tool in the 'Math' package
			double lat1 = Math.toRadians(34.4139);
			double lat2 = Math.toRadians(38.8031); // Enter your data!
			
			// The longitudes given as a double variable in radians
			double lon1 = Math.toRadians(119.8489); 
			double lon2 = Math.toRadians(104.8572); // Enter your data!
					
			// Determine change in lat and long between locations:
			double d_lat = Math.abs(lat2 - lat1);
			double d_lon = Math.abs(lon2 - lon1);
			
			/* Apply the Haversine Formula 
				The Math package is used again for sin, cos, arctan2, and square root operators
				The 'Math.pow(variable, 2) is a method for squaring a number */
			
			double a = Math.pow(Math.sin(d_lat/2),2) + (Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(d_lon/2),2));
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			
			// To get the distance in miles we multiply by the radius of the earth - 3,961 miles
			double d = 3961 * c;
			
			//A print statement is used to provide our answer
			System.out.print(loc2 + " High School is " + d + " miles from " + loc1); 
			
		}
	}


