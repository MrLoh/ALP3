package a3;


public class Viereck implements Figur, Comparable<Figur>{

	protected Point a, b, c, d;			//Kapselung
	protected double ab, bc, cd, ad;	//Kapselung

	public Viereck(Point a, Point b, Point c, Point d){

		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;

		this.ab = Math.sqrt(Math.abs(a.x-b.x)+Math.abs(a.y-b.y));
		this.bc = Math.sqrt(Math.abs(b.x-c.x)+Math.abs(b.y-c.y));
		this.cd = Math.sqrt(Math.abs(c.x-d.x)+Math.abs(c.y-d.y));
		this.ad = Math.sqrt(Math.abs(a.x-d.x)+Math.abs(a.y-d.y));

	}

	public double umfang(){
		return (ab+bc+cd+ad);
	}

	public double flaeche(){
		return 0.5*((a.y-c.y)*(d.x-b.x)+(b.y-d.y)*(a.x-c.x));
	}

	public int compareTo(Figur figur){
		if(flaeche()<figur.flaeche())
			return -1;
		else if (flaeche()==figur.flaeche())
			return 0;
		else
			return 1;
	}
}
