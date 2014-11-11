package a3;


public class Parallelogramm extends Trapez{
	//Vererbung von Viereck und Trapez

	public Parallelogramm(Point a, Point b, Point c, Point d){
		super(a,b,c,d);
	}

	public double umfang(){ //Polymorphie: Methodenueberschreibung
		return 2*(ab+bc);
	}
}
