import java.util.*;
import java.lang.*;

public final class Match {

	public static LinkedList<Integer> bf_match(String t, String p) {
		LinkedList<Integer> out = new LinkedList<Integer>();
		int m = p.length();
		for ( int start=0; start<=t.length()-m; start++ ) {
			int i = start;
			for ( char c : p.toCharArray() ) {
				if ( c != t.charAt(i) )
					break;
				i++;
			}
			if ( i == start+m )
				out.add(start);
		}
		return out;
	}


	public static long pow(int a, int n) {
		long out = 1;
		for ( int i=0; i<n; i++) {
			out *= a;
		}
		return out;
	}
	static long hash(String s, int b, int m, int q) {
		long out = 0;
		for ( int i=0; i<m; i++ )
			out += s.charAt(i) * pow(b, m-i-1);
		if ( q == 0 )
			return out;
		else
			return out%q;
	}
	public static LinkedList<Integer> rk_match(String t, String p, int b) {
		LinkedList<Integer> out = new LinkedList<Integer>();
		int m = p.length();

		long pv = hash(p, b, m, 0);
		long cv = hash(t.substring(0,m), b, m, 0);
		for ( int start=0; start<=t.length()-m; start++ ) {
			if ( cv == pv )
				out.add(start);
			if ( start<t.length()-m )
				cv = b*cv - t.charAt(start)*pow(b,m) + t.charAt(start+m);
		}
		return out;
	}
	static boolean match(int start, String t, String p) {
		int i = start;
		for ( char c : p.toCharArray() ) {
			if ( c != t.charAt(i) )
				return false;
			i++;
		}
		return true;
	}
	public static LinkedList<Integer> rk_match(String t, String p, int b, int q) {
		LinkedList<Integer> out = new LinkedList<Integer>();
		int m = p.length();

		long pv = hash(p, b, m, q);
		for ( int start=0; start<=t.length()-m; start++ ) {
			long cv = hash(t.substring(start,m+start), b, m, q);
			if ( cv == pv && match(start, t, p) )
				out.add(start);
		}
		return out;
	}



	public static LinkedList<Integer> bm_match(String t, String p) {
		LinkedList<Integer> out = new LinkedList<Integer>();
		int m = p.length();

		Hashtable<Character,Integer> tab = new Hashtable<Character,Integer>();
		for ( int i=0; i<m; i++ ) {
			tab.put(p.charAt(i), i);
		}

		int start = 0;
		int i = m-1;
		char c = t.charAt(start+i);
		while ( start <= t.length()-m ) {
			c = t.charAt(start+i);
			// System.out.println("i = "+i+", start = "+start+", c["+(start+i)+"] = "+c);
			if ( c == p.charAt(i) ) {
				i--;
			} else {
				// System.out.println(t+"\n"+(new String(new char[start]).replace('\0', ' '))+p);
				if ( tab.containsKey(c) && i>tab.get(c))
					start += i-tab.get(c);
				else
					start += (i==0) ? 1 : i;
				i = m-1;
			}
			if ( i<0 ) {
				out.add(start);
				// System.out.println("i = "+i+", adding "+start);
				start++;
				i = m-1;
			}
		}
		return out;
	}


	public static void main(String[] args) {
		String t; String p; String p1; String p2;
		t = "Hallo Welt & Weltalll";
		p1 = "ll";
		p2 = "Welt";
		System.out.println("t = "+t);
		System.out.println("p1 = "+p1);
		System.out.println("p2 = "+p2);
		System.out.println();

		System.out.println(bf_match(t,p1));
		System.out.println(bf_match(t,p2));
		System.out.println();

		System.out.println(rk_match(t,p1,128));
		System.out.println(rk_match(t,p2,128));
		System.out.println();

		System.out.println(rk_match(t,p1,128,131));
		System.out.println(rk_match(t,p2,128,131));
		System.out.println();

		System.out.println(bm_match(t,p1));
		System.out.println(bm_match(t,p2));
		System.out.println();

		t = "10000001101100000001001100";
		p = "0000";
		System.out.println("t = "+t);
		System.out.println("p = "+p);
		System.out.println(bm_match(t,p));
		System.out.println(bf_match(t,p));
		System.out.println();

		t = "011010010110100001001";
		p = "0010";
		System.out.println("t = "+t);
		System.out.println("p = "+p);
		System.out.println(bm_match(t,p));
		System.out.println(bf_match(t,p));
		System.out.println();

		System.out.println(hash("Moby", 94, 4, 0));
		System.out.println(hash("captain", 128, 7, 0));
	}
}
