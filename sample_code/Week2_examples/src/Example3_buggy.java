import java.util.Arrays;

public class Example3_buggy {

	public static void main(String[] args) {
		
		String geom; //initialize geometry string

		int Min = 1; // minimum values = 1
		int Max = 10; // maximum values = 10
		
		int length = Min + (int)(Math.random() * ((Max - Min) + 1)) ; // random number between 1 and 10
		
		System.out.println("Number of Values = " + length); // print number of values
		
		int pairs = length / 2; // How many pairs are there?
		
		if(pairs % 2 != 0 ) { // If there is an odd number of pairs then subtract 1
			pairs = pairs - 1 ;	
		}
				
		System.out.println("Number of pairs = " + pairs); // print number of pairs
		
		if(pairs == 1) {
			geom = "POINT"; // if one pair declare a POINT()
		} else if(pairs == 2) {
			geom = "LINESTRING"; // if two pairs declare a LINESTRING()
		} else if ( pairs >= 3){
			geom = "POLYGON"; // if three or more pairs declare a POLYGON()
		} else {
			geom = "INVALID";
		}

		System.out.println("Geometry Type = " + geom); // print geometry type
		
		int[] coords = new int[pairs*2]; // initialize an array for all coordinate pairs the length of pairs *2
		
		for(int i=0; i < pairs * 2; i++ ) {
		
			coords[i] = (int) (Math.random() * 100); // fill the array with random values
		}
		
		System.out.println(geom + " " + Arrays.toString(coords)); // print psuedo WKT string
			
		}
		
		
	}