import java.util.*;

public class a2 {

	static int[] rndArray(){
		return rndArray(10);
	}
	static int[] rndArray(int size){
		int[] arr = new int[size];
		for ( int i=0; i<size; i++ ) {
			arr[i] = (int) (Math.random()*size*2);
		}
		return arr;
	}

	public static int factorial(int n) {
		if ( n==0 ) return 1;
		return n * factorial(n-1);
	}

	static boolean isSorted(int[] arr){
		for ( int i=1; i<arr.length; i++ ) {
			if ( arr[i-1] > arr[i] ) return false;
		}
		return true;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void permutationSort(int[] arr) {
		permutationSort(arr, 0);
	}
	static boolean permutationSort(int[] arr, int k) {
		for ( int i=k; i<arr.length; i++ ) {
			swap(arr, i, k);
			if ( permutationSort(arr, k+1) ) {
				return true;
			} else {
				swap(arr, k, i);
			}
		}
		if (k==arr.length-1) {
			if ( isSorted(arr) ) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	// Der Algorithmus wird mit dem Array der Länge n  aufgerufen und erstellt
	// zunächst alle Vertauschungen  der Stelle k mit den Stellen k <= i <= n.
	// Für jede dieser Vertauschungen ruft er sich rekursiv auf, um alle
	// Vertauschungen an der Stelle k+1 zu erstellen. Sobald der Algorithmus
	// einmal an der Stelle k = n angekommen ist, hat er eine Permutation
	// erzeugt. Nun prüft er, ob das Array bereits sortiert ist und gibt einen
	// entsprechenden Wahrheitswert zurück. Nachdem eine Rekursion abgearbeitet
	// ist, bricht der Algorithmus ab, wenn das Array bereits sortiert ist,
	// oder macht ein backtracking, so dass für den nächsten Schleifendurchlauf,
	// nur die Stellen i und k vertauscht sind.

	static void shuffle(int[] arr){
		for ( int swaps=0; swaps<arr.length ; swaps++ ) {
			int i = (int) (Math.random()*arr.length);
			int j = (int) (Math.random()*arr.length);
			swap(arr, i, j);
		}
	}

	static void bogoSort(int[] arr) {
		int shuffles = 0;
		while ( !isSorted(arr) ) {
			shuffle(arr);
			shuffles++;
		}
		// System.out.println(String.format("%,d ",shuffles)+"shuffles");
	}
	// Der Algorithmus shuffled das Array der Länge n zunächst. Dafür wählt
	// er n mal zwei Zufallszahlen zwischen 1 und n aus und vertauscht diese
	// beiden Stellen im Array. Danach tested er, ob das geshuffelte Array
	// sortiert ist, wenn ja beendet er, wenn nein, wiederholt er die Prozedur.

	static long measureTimeBS(int[] arr) {
		int[] arrC = new int[arr.length];
		long time;
		System.arraycopy(arr, 0, arrC, 0, arr.length);
		time = System.nanoTime();
		bogoSort(arrC);
		time = (System.nanoTime()-time)/1000000;
		return time;
	}

	static long measureTimePS(int[] arr) {
		int[] arrC = new int[arr.length];
		long time;
		System.arraycopy(arr, 0, arrC, 0, arr.length);
		time = System.nanoTime();
		permutationSort(arrC);
		time = (System.nanoTime()-time)/1000000;
		return time;
	}


	// MAIN \\
	public static void main(String[] args) {
		for ( int i=6; i<=12 ; i+=3 ) {
			int[] array = rndArray(i);
			System.out.println("\nLenth: "+i);
			System.out.println(Arrays.toString(array));
			System.out.println("PermutationSort:\t"+measureTimePS(array)+"ms");
			for ( int j=0; j<10; j++ ) {
				System.out.println("BogoSort:\t\t"+measureTimeBS(array)+"ms");
			}
		}
	}
}

