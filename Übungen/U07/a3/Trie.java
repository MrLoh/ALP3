import java.util.*;
import java.lang.*;

public class Trie implements Iterable<WordFreqPair> {
	private TrieNode root = new TrieNode('\n', null);
	private int size = 0;

	static int alphabetPos(char c) {
		return (int)(c-'a');
	}
	static char alphabetChar(int i){
		return (char)(i+'a');
	}

	class TrieNode {
		private char c;
		private TrieNode parent;
		private TrieNode[] children = new TrieNode[26];
		private int ending = 0;
		// marking word endings with $ is pretty useless, when we are determening word counts, so this will not be done here.

		TrieNode(char c, TrieNode parent) {
			this.c = c;
			this.parent = parent;
		}
		public String toString(){
			return ""+this.c;
		}
	}

	public boolean empty() {
		return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean find(String w) {
		TrieNode current = this.root;
		w = w.toLowerCase().trim();
		for ( char c : w.toCharArray() ) {
			TrieNode next = current.children[alphabetPos(c)];
			if ( next == null )
				return false;
			else
				current = next;
		}
		if ( current.ending > 0 )
			return true;
		else
			return false;
	}

	public int count(String w) {
		TrieNode current = this.root;
		w = w.toLowerCase().trim();
		for ( char c : w.toCharArray() ) {
			TrieNode next = current.children[alphabetPos(c)];
			if ( next != null )
				current = next;
			else
				return 0;
		}
		return current.ending;
	}

	public void insert(String w) {
		TrieNode current = this.root;
		w = w.toLowerCase().trim();
		for ( char c : w.toCharArray() ) {
			TrieNode next = current.children[alphabetPos(c)];
			if ( next != null ) {
				current = next;
			} else {
				next = new TrieNode(c, current);
				current.children[alphabetPos(c)] = next;
				current = next;
			}
		}
		current.ending++;
		this.size++;
	}

	public void delete(String w) {
		TrieNode current = this.root;
		w = w.toLowerCase().trim();
		for ( char c : w.toCharArray() ) {
			TrieNode next = current.children[alphabetPos(c)];
			if ( next != null )
				current = next;
			else
				return;
		}
		if ( current.ending > 0 ) {
			current.ending--;
			this.size--;
		}
		// there is no garbage collection of empty leaves here, but since it is pretty useless for determening word frequencies, that should be ok.
	}
	public void deleteAll(String w) {
		TrieNode current = this.root;
		w = w.toLowerCase().trim();
		for ( char c : w.toCharArray() ) {
			TrieNode next = current.children[alphabetPos(c)];
			if ( next != null )
				current = next;
			else
				return;
		}
		if ( current.ending > 0 ) {
			this.size -= current.ending;
			current.ending = 0;
		}
	}





	public Iterator<WordFreqPair> iterator() {
		return new TrieIterator();
	}
	private class TrieIterator implements Iterator<WordFreqPair> {
		private Stack<WordFreqPair> stack = new Stack<WordFreqPair>();
		private int diffWords = 0;
		TrieIterator() {
			this.fillStack(root, "");
		}
		private void fillStack(TrieNode current, String pref) {
			if ( current != null ) {
				for ( TrieNode child : current.children ) {
					fillStack(child, pref+current.c);
				}
				if ( current.ending > 0 ) {
					this.stack.push(new WordFreqPair(pref+current.c, current.ending));
					this.diffWords++;
				}
			}
		}

		public int count() {
			return this.diffWords;
		}
		public boolean hasNext() {
			return !stack.isEmpty();
		}
		public WordFreqPair next() {
			if ( !hasNext() )
				throw new NoSuchElementException();
			return this.stack.pop();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public String toString(){
		if ( this.empty() )
			return "-----EMPTY-TRIE-----";
		String out = "--------TRIE--------\n";
		for ( WordFreqPair wf : this ) {
			out += String.format("%6d : %s\n", wf.freq, wf.word.trim());
		}
		return out + "--------------------";
	}
	public WordFreqPair[] toArray(){
		TrieIterator iter = new TrieIterator();
		WordFreqPair[] arr = new WordFreqPair[iter.count()];
		int i = 0;
		while ( iter.hasNext() ) {
			// System.out.println(""+i+it);
			arr[i] = iter.next();
			i++;
		}
		return arr;
	}





	public static void main(String[] args) {
		System.out.println("alphabetPos('z') = "+alphabetPos('z'));
		System.out.println("alphabetChar(0) = '"+alphabetChar(0)+"'");

		System.out.println("\ntrie = new Trie!");
		Trie trie = new Trie();
		System.out.println(trie);
		System.out.println("trie empty? "+trie.empty());

		System.out.println("\ntrie.insert(test)!");
		trie.insert("test");
		System.out.println(trie);
		System.out.println("trie.size?\t\t"+trie.getSize());
		System.out.println("trie.find(test)?\t"+trie.find("test"));
		System.out.println("trie.count(test)?\t"+trie.count("test"));

		System.out.println("\ntrie.insert(test)!");
		trie.insert("test");
		System.out.println("\ntrie.insert(top)!");
		trie.insert("top");
		System.out.println("\ntrie.insert(tell)!");
		trie.insert("tell");
		System.out.println(trie);
		System.out.println("trie.count(test)?\t"+trie.count("test"));
		System.out.println("trie.size?\t\t"+trie.getSize());

		System.out.println("\ntrie.delete(top)!");
		trie.delete("top");
		System.out.println(trie);
		System.out.println("trie.count(test)?\t"+trie.count("test"));
		System.out.println("trie.size?\t\t"+trie.getSize());

		System.out.println("\ntrie.deleteAll(test)!");
		trie.deleteAll("test");
		System.out.println(trie);
		System.out.println("trie.count(test)?\t"+trie.count("test"));
		System.out.println("trie.size?\t\t"+trie.getSize());
	}
}
