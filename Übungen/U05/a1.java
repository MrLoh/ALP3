import java.util.*;

public class a1 {

	static int binSearch(int[] arr, int key) {
		return binSearch(arr, key, 0, arr.length-1 );
	}
	static int binSearch(int[] arr, int key, int l, int r) {
		if ( l>r )
			throw new RuntimeException("key not in array");
		int m = l+(r-l)/2;
		System.out.println("l: "+l+"\tr: "+r+"\tm: "+m);
		if ( key==arr[m] )
			return arr[m];
		else if ( key<arr[m] )
			return binSearch(arr, key, l, m-1);
		else
			return binSearch(arr, key, m+1, r);
	}


	static int intplSearch(int[] arr, int key) {
		return intplSearch(arr, key, 0, arr.length-1 );
	}
	static int intplSearch(int[] arr, int key, int l, int r) {
		if ( l>r )
			throw new RuntimeException("key not in array");
		int m = l+(r-l)*(key-arr[l])/(arr[r]-arr[l]);
		System.out.println("l: "+l+"\tr: "+r+"\tm: "+m);
		if ( key==arr[m] )
			return arr[m];
		else if ( key<arr[m] )
			return intplSearch(arr, key, l, m-1);
		else
			return intplSearch(arr, key, m+1, r);
	}


	static int[] newArray(int len){
		int[] arr = new int[len];
		for ( int i=0; i<len ; i++ )
			arr[i] = i;
		return arr;
	}
	public static void main(String[] args) {
		int[] arr = newArray(50);

		System.out.println(Arrays.toString(arr));
		System.out.println(binSearch(arr, 24));
		System.out.println(binSearch(arr, 0));
		System.out.println(binSearch(arr, 36));
		System.out.println();

		System.out.println(intplSearch(arr, 24));
		System.out.println(intplSearch(arr, 2));
		System.out.println(intplSearch(arr, 40));
		System.out.println();
	}
}
