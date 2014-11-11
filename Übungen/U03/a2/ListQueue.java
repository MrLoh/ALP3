package a2;

import java.util.LinkedList;

public class ListQueue<E> implements Queue<E>{

	private LinkedList<E> list;

	public ListQueue(){
		list = new LinkedList<E>();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return (size() == 0);
	}

	public void enqueue( E e){
		list.add(e);
	}

	public E dequeue () throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException();
		else
			return list.remove();
	}

	public String toString(){
		return list.toString();
	}
}
