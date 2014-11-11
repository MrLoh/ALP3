package a3;

public class Rechteck extends Parallelogramm{
	//Vererbung von Viereck, Trapez, Parallelogramm

	public Rechteck(Point a, Point b, Point c, Point d){
		super(a,b,c,d);
	}

	public double flaeche(){ //Polymorphie: Methodenueberschreibung
		return ab*bc;
	}
}
