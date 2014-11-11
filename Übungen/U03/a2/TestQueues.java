package a2;

import java.util.Random;

public class TestQueues {

	public static void main(String[] args) {
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(10);
		ListQueue<Integer> listQueue = new ListQueue<Integer>();
		Random rand = new Random();
		Integer number = rand.nextInt(31);

		System.out.println("ArrayQueue:");
		System.out.println("Die Warteschlange ist leer: " + arrayQueue.isEmpty());
		System.out.println("Es werden 12 Zahlen zwischen 0 und 30 in die Warteschlange eingefuegt:");
		for(int i=0; i<12; i++){
			arrayQueue.enqueue(number);
			System.out.println(arrayQueue);
			number = rand.nextInt(20);
		}
		System.out.println("Es werden die ersten 5 Zahlen entfernt:");
		for(int i=0; i<5; i++){
			arrayQueue.dequeue();
			System.out.println(arrayQueue);
		}
		System.out.println("Die Warteschlange hat nun die Länge: " + arrayQueue.size());

		System.out.println("ListQueue:");
		System.out.println("Die Warteschlange ist leer: " + listQueue.isEmpty());
		System.out.println("Es werden 8 Zahlen zwischen 0 und 30 in die Warteschlange eingefuegt:");
		for(int i=0; i<8; i++){
			listQueue.enqueue(number);
			System.out.println(listQueue);
			number = rand.nextInt(20);
		}
		System.out.println("Es werden die ersten 3 Zahlen entfernt:");
		for(int i=0; i<3; i++){
			listQueue.dequeue();
			System.out.println(listQueue);
		}
		System.out.println("Die Warteschlange hat nun die Länge: " + listQueue.size());
	} // end of main

} //end of class TestQueues


