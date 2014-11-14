import java.util.*;
import java.lang.*;

public class Hashtests {
	static Random rnd = new Random();
	static String rndString() {
		return Long.toString(Math.abs(rnd.nextLong()), 36);
	}

	public static void main(String[] args) {
		int repeats = 100;
		Wortbuch buch;

		for ( int len : new int[]{10,100,1000} ) {
			for ( int entries : new int[]{len/2,len,len*2} ) {
				System.out.println("length: "+len+" | entries: "+entries);
				for ( int hashtype : new int[]{0,1,2,3,4,5} ) {
					System.out.println("hashfunktion"+hashtype+":");
					double colls = 0;
					double devia = 0;
					for ( int n=0; n<repeats; n++ ) {
						buch = new Wortbuch(len, hashtype);
						for ( int i=0; i<entries; i++) {
							String s = rndString();
							try { //avoid duplicate strings
								buch.finde(s);
							} catch ( RuntimeException e ) {
								buch.einfuege(new Wort(s));
							}
						}
						colls += buch.kollisionen();
						devia += buch.abweichung();
					}
					double percentcolls = colls/repeats/entries*100;
					double absdevia = devia/repeats;
					System.out.printf( "kollis:  %.2f", percentcolls );
					System.out.println(" %");
					System.out.printf( "stdabw:  %.2f\n", absdevia );
				}
				System.out.println("\n");
			}
		}
		System.out.println("hashfunktion0:");
		System.out.println("Da alles auf 0 gehasht wird, haben wir nur Kollisionen, bis aufs erste Hashen.\n");

		System.out.println("hashfunktion1:");
		System.out.println("Da nur der int Wert des ersten Buchstabens des Strings verwendet wird, haben wir nur einen sehr begrenzten Wertebereich und bei vielen Hashes wieder fast 100% Kollisionen.\n");

		System.out.println("hashfunktion2:");
		System.out.println("Der Wertebereich der Quersummen der int Werte der Chars ist zwar deutlich größer, aber trotzdem noch sehr beschränkt, da es nur wenige Chars gibt, so dass immernoch sehr viele Kollisionen auftreten.\n");

		System.out.println("hashfunktion3:");
		System.out.println("Die Queersumme wird hier um unterschiedliche Primfaktoren erweitert, mit denen jeder int Wert eines Chars multipliziert wird. Dadurch erhalten wir einen sehr großen Wertebereich und große Zufälligkeit der Werte. Es treten trodzdem noch einige Kollisionen auf, da alles noch sehr klar von den int Werten der einzelnen Chars abhängt.\n");

		System.out.println("hashfunktion4:");
		System.out.println("Die standard Java hashfunktion für strings scheint sich ähnlich wie hashfunktion3 zu verhalten.\n");

		System.out.println("hashfunktion5:");
		System.out.println("Eigentlich erwarten wir weniger Kollisionen, da SHA-256 einen völlig zufälligen String erzeugen sollte. Vielleicht liegt es an der zufälligen String Erzeugung oder an der Modulo Rechnung.\n");
	}
}
