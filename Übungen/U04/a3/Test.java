public class Test {
	public static void main(String[] args) {
		int len = 10;

		Wort w1 = new Wort("Test1");
		Wort w2 = new Wort("Test2");
		Wort w3 = new Wort("Test3");
		Wort w4 = new Wort("Test4");

		Wortbuch buch = new Wortbuch(len);

		System.out.println("Einfügen:");
		buch.einfuege(w1);
		buch.einfuege(w2);
		buch.einfuege(w3);
		buch.einfuege(w4);
		System.out.println(buch);
		System.out.println();

		System.out.println("Finden:");
		System.out.println(buch.finde("Test4"));
		// System.out.println(buch.finde("Test"));
		System.out.println();

		System.out.println("Streihen:");
		System.out.println(buch);
		buch.streiche("Test1");
		System.out.println(buch);
		System.out.println();
	}
}
