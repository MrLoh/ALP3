import java.util.*;
import java.io.*;

public class a1 {
	static Random rnd = new Random();
	static double calls = 0;

	static int binSearch(int[] arr, int key) {
		calls = 0;
		return binSearch(arr, key, 0, arr.length-1 );
	}
	static int binSearch(int[] arr, int key, int l, int r) {
		calls ++;
		if ( l>r )
			throw new RuntimeException("key not in array");
		int m = l+(r-l)/2;
		// System.out.println("l: "+l+"\tr: "+r+"\tm: "+m);
		if ( key==arr[m] )
			return arr[m];
		else if ( key<arr[m] )
			return binSearch(arr, key, l, m-1);
		else
			return binSearch(arr, key, m+1, r);
	}


	static int intplSearch(int[] arr, int key) {
		calls = 0;
		return intplSearch(arr, key, 0, arr.length-1 );
	}
	static int intplSearch(int[] arr, int key, int l, int r) {
		calls ++;
		if ( l>r )
			throw new RuntimeException("key not in array");
		int m = l+(int)(((double)key-arr[l])/(arr[r]-arr[l])*(r-l));
		// System.out.println("l: "+l+"\tr: "+r+"\tm: "+m);
		if ( key==arr[m] )
			return arr[m];
		else if ( key<arr[m] )
			return intplSearch(arr, key, l, m-1);
		else
			return intplSearch(arr, key, m+1, r);
	}


	static int[] newArray(int len, int range){
		int[] arr = new int[len];
		for ( int i=0; i<len ; i++ )
			arr[i] = rnd.nextInt(range);
		Arrays.sort(arr);
		return arr;
	}
	static double median(double[] arr) {
		double out = 0;
		for ( double i : arr ) {
			out += i;
		}
		return out/arr.length;
	}
	static double stdev(double[] arr) {
		double out = 0;
		double m = median(arr);
		for ( double i : arr ) {
			out += Math.pow(i-m,2);
		}
		return Math.sqrt(out/arr.length);
	}


	public static void main(String[] args) {

		boolean printa = false;
		if ( printa ) {
			try {
				PrintWriter writer = new PrintWriter("a1.csv", "UTF-8");
				int range;
				int found;
				int iterations = 100;
				System.out.println("len, median BS, stdev BS, median IS, stdev IS");
				writer.println("len, median BS, stdev BS, median IS, stdev IS");
				for ( int len=100; len<100000; len+=100 ) {
					range = len;
					// System.out.println("len: "+len+" range: "+range);
					System.out.print(len+", ");
					int i = 0;
					double[] callsBS = new double[iterations];
					double[] callsIS = new double[iterations];
					while ( i<iterations ) {
						int[] arr = newArray(len, range);
						// System.out.println(Arrays.toString(arr));
						int key = rnd.nextInt(range);
						try {
							found = binSearch(arr, key);
							callsBS[i] = calls;
							// System.out.println("found "+found+" in "+calls+" calls");
							found = intplSearch(arr, key);
							callsIS[i] = calls;
							// System.out.println("found "+found+" in "+calls+" calls");
							i++;
						} catch( RuntimeException e ){};
					}
					System.out.print(median(callsBS)+", "+stdev(callsBS)+", ");
					System.out.println(median(callsIS)+", "+stdev(callsIS));
					writer.println(len+","+median(callsBS)+","+stdev(callsBS)+","+median(callsIS)+","+stdev(callsIS));
				}
				// System.out.println("\n\n");
				writer.close();
			} catch( Exception e ) {
				System.out.println("File Error");
			}
		}


		int[] badArr = {1,1,1,1,1,1,1,1,2,99};
		System.out.println("Take the array: "+Arrays.toString(badArr));
		System.out.println("then: (key-arr[l])/(arr[r]-arr[l]) = (2-1)/(3-1) = 0");
		System.out.println("=> m = l");
		intplSearch(badArr, 2);
		System.out.println("therefore we have n-1 = "+calls+" calls");
	}
}
