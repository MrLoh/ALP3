package a3;

public class Raute extends Drache{
	//Vererbung von Viereck, Drache

	public Raute(Point a, Point b, Point c, Point d){
		super(a,b,c,d);
	}

	public double umfang(){ //Polymorphie: Methodenueberschreibung
		return 4*ab;
	}

}
