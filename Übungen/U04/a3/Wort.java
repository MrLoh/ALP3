public class Wort implements Eintrag<String,Integer> {
	static int counter = 0;
	String k;
	Integer v;

	public Wort(String k){
		counter ++;
		this.k = k;
		this.v = counter;
	}

	public String getSchluessel(){
		return this.k;
	}

	public Integer getWert(){
		return this.v;
	}

	public String toString() {
		return this.k + ":" + this.v;
	}
}
