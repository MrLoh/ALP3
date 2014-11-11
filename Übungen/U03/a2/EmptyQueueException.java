package a2;

public class EmptyQueueException extends RuntimeException  {

	public EmptyQueueException (){
		super("Die Warteschlange ist leer!");
	}
}
