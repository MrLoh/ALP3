public class Test {
	public static void main(String[] args) {
		int len = 10;

		Wort w1 = new Wort("Test1");
		Wort w2 = new Wort("Test2");
		Wort w3 = new Wort("Test3");
		Wort w4 = new Wort("Test4");

		Wortposition wpos = new Wortposition(len);

		System.out.println("Einfügen:");
		wpos.einfuege(w1);
		wpos.einfuege(w2);
		wpos.einfuege(w3);
		wpos.einfuege(w4);
		System.out.println(wpos);
		System.out.println();

		System.out.println("Finden:");
		System.out.println(wpos.finde("Test4"));
		// System.out.println(wpos.finde("Test"));
		System.out.println();

		System.out.println("Streihen:");
		System.out.println(wpos);
		wpos.streiche("Test1");
		System.out.println(wpos);
		System.out.println();
	}
}
