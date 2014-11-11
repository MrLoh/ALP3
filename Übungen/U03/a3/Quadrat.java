package a3;

public class Quadrat extends Rechteck{
	//Vererbung von Viereck, Trapez, Parallelogramm, Rechteck

	public Quadrat(Point a, Point b, Point c, Point d){
		super(a,b,c,d);
	}

	public double umfang(){ //Polymorphie: Methodenueberschreibung
		return 4*ab;
	}

	public double flaeche(){ //Polymorphie: Methodenueberschreibung
		return ab*ab;
	}
}
