import java.util.*;

public class a2 {
	static Random rand = new Random();

	static int[] randomArray(int size){
		int[] arr = new int[size];
		for ( int i=0; i<size; i++ ) {
			arr[i] = rand.nextInt(size);
		}
		return arr;
	}

	static void shuffle(int[] arr){
		for ( int swaps=0; swaps<arr.length ; swaps++ ) {
			int i = rand.nextInt(arr.length);
			int j = rand.nextInt(arr.length);
			swap(arr, i, j);
		}
	}

	static int[] shuffledArray(int size){
		int[] arr = new int[size];
		for ( int i=0; i<size; i++ ) {
			arr[i] = i;
		}
		shuffle(arr);
		return arr;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static int quickSelect(int[] arr, int k){
		int[] arrC = new int[arr.length];
		System.arraycopy(arr, 0, arrC, 0, arr.length);
		return quickSelect(arrC, k, 0, arr.length-1);
	}
	static int quickSelect(int[] arr, int k, int fst, int lst){
		int size = lst-fst;
		if ( size == 0 ) {
			return arr[fst];
		}
		// int pivot = arr[fst];
		int p = fst + rand.nextInt(size);
		int pivot = arr[p];
		int j = fst;
		swap(arr, p, lst);
		for ( int i=fst; i<lst; i++ ) {
			if ( arr[i] < pivot ) {
				swap(arr, i, j);
				j++;
			}
		}
		swap(arr, lst, j);
		if ( j < k ) {
			return quickSelect(arr, k, j+1, lst);
		} else if ( j == k ) {
			return pivot;
		} else {
			return quickSelect(arr, k, fst, j-1);
		}
	}

	public static void main(String[] args) {
		int[] arr = shuffledArray(10);
		System.out.println(Arrays.toString(arr));
		for ( int i=0; i<5 ; i++ ) {
			System.out.println("select 4th => " + quickSelect(arr, 4));
			System.out.println("select 4th => " + quickSelect(arr, 0));
		}
	}

}


// def quick_select(S,k):
//     if n==1: return S[0]
//     pivot = S[rand(size(S))]
//     S_le = [x in S if x<pivot]
//     S_gr = [x in S if x>pivot]
//     S_eq = [x in S if x==pivot]
//     if size(S_le) >= k: return quick_select(S_le,k)
//     if size(S_gr)+size(S_eq) <= k: return pivot
//     return quick_select(S_gr, k-size(S_le)-size(S_eq))
