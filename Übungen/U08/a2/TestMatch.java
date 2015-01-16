import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.*;

public class TestMatch {
	static Random rnd = new Random();

	public static String getRandomString(int size, int b) {
		String out = "";
		for ( int i=0; i<size; i++ ) {
			out += (char) rnd.nextInt(b);
		}
		return out;
	}


	public static void main(String[] args) {

		boolean calcPlots = true;
		if ( calcPlots ) {
			String t; String p;
			int iterations = 10;
			int[] b = {2, 8, 23};
			int[] l = {9, 3, 2};
			int[] q = {7, 29, 131};
			for ( int k=0; k<3; k++ ) {
				System.out.println("BASIS: "+b[k]);
				System.out.println("size, t_bf, t_bm, t_rk");

				for ( int size=10000; size<=100000; size+=5000 ) {
					double t_bf = 0;
					double t_bm = 0;
					double t_rk = 0;
					// double t_rk2 = 0;
					for ( int i=0; i<iterations+5; i++ ) {
						t = getRandomString(size,b[k]);
						p = getRandomString(l[k],b[k]);
						long time;

						// System.out.println(Match.bf_match(t,p));
						time = System.nanoTime()/1000;
						Match.bf_match(t,p);
						time = System.nanoTime()/1000-time;
						if ( i>5 ) {
							// System.out.println(time);
							t_bf += ((double)time)/1000;
						}


						// System.out.println(Match.bm_match(t,p));
						time = System.nanoTime()/1000;
						Match.bm_match(t,p);
						time = System.nanoTime()/1000-time;
						if ( i>5 ) {
							// System.out.println(time);
							t_bm += ((double)time)/1000;
						}

						// System.out.println(Match.rk_match(t,p,b[k]));
						time = System.nanoTime()/1000;
						Match.rk_match(t,p,b[k]);
						time = System.nanoTime()/1000-time;
						if ( i>5 ) {
							// System.out.println(time);
							t_rk += ((double)time)/1000;
						}

						// System.out.println(Match.rk_match(t,p,b[k],q[k]));
						// time = System.nanoTime()/1000;
						// Match.rk_match(t,p,b[k],q[k]);
						// time = System.nanoTime()/1000-time;
						// if ( i>5 ) {
						// 	// System.out.println(time);
						// 	t_rk2 += ((double)time)/1000;
						// }

					}
					System.out.printf("%d, ",size);
					System.out.printf("%f, ",t_bf/iterations);
					System.out.printf("%f, ",t_bm/iterations);
					System.out.printf("%f, ",t_rk/iterations);
					// System.out.printf("%f",t_rk2/iterations);
					System.out.println();
				}
			}
		}

		System.out.println();
		String t = "";
		try {
			Scanner strm = new Scanner(new File("mobydick.txt"));
			t = strm.useDelimiter("\\Z").next();
			strm.close();
		} catch(Exception e) {
			throw new RuntimeException("Error on reading file into string.");
		}

		System.out.println(String.format("Read %d characters from Moby Dick.\n",t.length()));

		LinkedList<Integer> captain_matches;
		LinkedList<Integer> moby_matches;
		long time;

		System.out.println("Brute-Force-Match:");
		time = System.nanoTime()/1000;
		captain_matches = Match.bf_match(t, "captain");
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'captain' in %.2f milli seconds",captain_matches.size(),(double)time/1000 ));
		time = System.nanoTime()/1000;
		moby_matches = Match.bf_match(t, "Moby");
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'Moby' in %.2f milli seconds",moby_matches.size(),(double)time/1000 ));
		System.out.println();

		System.out.println("Boyer-Moore-Match:");
		time = System.nanoTime()/1000;
		captain_matches = Match.bm_match(t, "captain");
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'captain' in %.2f milli seconds",captain_matches.size(),(double)time/1000 ));
		time = System.nanoTime()/1000;
		moby_matches = Match.bm_match(t, "Moby");
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'Moby' in %.2f milli seconds",moby_matches.size(),(double)time/1000 ));
		System.out.println();

		System.out.println("Rabin-Karp-Match:");
		time = System.nanoTime()/1000;
		captain_matches = Match.rk_match(t, "captain",128);
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'captain' in %.2f milli seconds",captain_matches.size(),(double)time/1000 ));
		time = System.nanoTime()/1000;
		moby_matches = Match.rk_match(t, "Moby",128);
		time = System.nanoTime()/1000-time;
		System.out.println(String.format("Found %d Matches for 'Moby' in %.2f milli seconds",moby_matches.size(),(double)time/1000 ));
		System.out.println();

	}
}
