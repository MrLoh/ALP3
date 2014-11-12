import java.util.*;

public class Wortposition {

	private int len;
	private int size = 0;
	private Wortlist[] arr;
	private int[] freq;
	private int hashtype = 0;


	public Wortposition(int len) {
		this.len = len;
		this.freq = new int[len];
		this.arr = new Wortlist[len];
		for ( int i=0; i<len; i++ ) {
			arr[i] = new Wortlist();
		}
	}
	public Wortposition(int len, int hashtype) {
		this(len);
		this.hashtype = hashtype;
	}


	private int hashF1(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++){
			sum += (int) s.charAt(i);
		}
		return sum;
	}
	public int hash(String k) {
		int h;
		switch ( hashtype ) {
			case 1: h = hashF1(k); break;
			default: h = k.hashCode(); break;
		}
		return h % len;
	}


	public void einfuege (Wort w) {
		this.size++;
		int h = hash(w.k);
		arr[h].add(w);
		freq[h]++;
	}
	public void streiche (String s) {
		int h = hash(s);
		for ( Wort w : arr[h] ) {
			if ( s.equals(w.k) ) {
				arr[h].remove(w);
				size--;
				freq[h]--;
				return;
			}
		}
	}
	public Wort finde(String s) {
		int h = hash(s);
		for ( Wort w : arr[h] ) {
			if ( s.equals(w.k) ) return w;
		}
		throw new RuntimeException("Nicht im Buch");
	}


	public int groesse() {
		return size;
	}
	public int[] frequencies() {
		return freq;
	}
	public boolean istLeet() {
		if ( size==0 ) return false;
		else return true;
	}
	public Wort[] eintraege() {
		Wort[] out = new Wort[size];
		int j = 0;
		for ( int i=0; i<len; i++ ) {
			for ( Wort w : arr[i] ) {
				out[j] = w;
				j++;
			}
		}
		return out;
	}
		public String toString() {
		String out = "|";
		for ( int i=0; i<len; i++ ) {
			for ( Wort w : arr[i] ) {
				out += " " + w + " |";
			}
		}
		return out;
	}

}
