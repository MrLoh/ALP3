import java.util.*;
import java.security.MessageDigest;

public class Wortbuch {

	private int len;
	private int size = 0;
	private Wortlist[] arr;
	private int[] freq;
	private int hashtype = 0;


	public Wortbuch(int len) {
		this.len = len;
		this.freq = new int[len];
		this.arr = new Wortlist[len];
		for ( int i=0; i<len; i++ ) {
			arr[i] = new Wortlist();
		}
	}
	public Wortbuch(int len, int hashtype) {
		this(len);
		this.hashtype = hashtype;
	}


	private int hashF0(String s) {
		return 0;
	}
	private int hashF1(String s) {
		return (int) s.charAt(0);
	}
	private int hashF2(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++){
			sum += (int) s.charAt(i);
		}
		return sum;
	}
	private int hashF3(String s) {
		int sum = 7;
		for (int i = 0; i < s.length(); i++){
			sum = sum*31 + (int) s.charAt(i);
		}
		return Math.abs(sum);
	}
	private int hashF4(String s) {
		return Math.abs(s.hashCode());
	}
	private int hashF5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] b = md.digest(s.getBytes("UTF-8"));
			return Math.abs (b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24);
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}
	public int hash(String k) {
		int h;
		switch ( hashtype ) {
			case 0: h = hashF0(k); break;
			case 1: h = hashF1(k); break;
			case 2: h = hashF2(k); break;
			case 3: h = hashF3(k); break;
			case 4: h = hashF4(k); break;
			case 5: h = hashF5(k); break;
			default: h = hashF5(k); break;
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
		String out = "{ ";
		for ( int i=0; i<len; i++ ) {
			out += i + ":[";
			for ( Wort w : arr[i] ) {
				out += w + "";
			}
			out += "] ";
		}
		return out + "}";
	}


	public int[] frequenzen() {
		return freq;
	}
	public double kollisionen() {
		int out = 0;
		for ( int i : freq ) {
			if ( i>1 ) out += i-1;
		}
		return (double)out;
	}
	public double abweichung() {
		double out = 0;
		double average = ((double)size)/len;
		for ( int i : freq ) {
			out += Math.pow(((double)i)-average,2);
		}
		return Math.sqrt(out/len);
	}

}
