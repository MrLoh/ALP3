package a2;

public class ArrayQueue <E> implements Queue<E> {

	private E[] array;
	private int arraySize;
	private int length = 0;
	private int head = 0;
	private int tail = 0;

	public ArrayQueue (int size){
		this.array = (E[]) new Object[size];
		this.arraySize = size;
	}

	public int size(){
		return length;
	}

	public boolean isEmpty(){
		return (length == 0);
	}

	public void enqueue( E e){
		if (length == arraySize-1)
			resizeArray();
		array[tail] = e;
		if (tail == (arraySize-1))
			tail = 0;
		else tail++;
		length++;
	}

	public E dequeue () throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException();
		else{
			E e = array[head];
			if (head == (arraySize-1))
				head = 0;
			else head++;
			length--;
			return e;
		}
	}

	//Erzeugt ein Array mit doppelter Groeﬂe und kopiert das alte Array hinein
	private void resizeArray(){
		E[] temp = (E[]) new Object[arraySize*2];
		for (int i=0; i<arraySize-1; i++){
			temp[i] = array[i];
		}
		arraySize *= 2;
		array = temp;
	}

	public String toString(){
		int current = head;
		String queueStr = "<-";
		if (isEmpty())
			return "()";
		while(current != tail){
			queueStr = queueStr + " "+ array[current];
			if(current==arraySize-1) current= 0;
			else current++;
		}
		queueStr = queueStr +" <-";
		return queueStr;
	}

}//end of class ArrayQueue
