import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.*;

public class FindWordFreqs {
	public static WordFreqPair maxFreqWord(Trie trie) {
		WordFreqPair[] arr = trie.toArray();
		WordFreqPair max = arr[0];
		for ( WordFreqPair wf : arr ) {
			if ( max.freq < wf.freq )
				max = wf;
		}
		return max;
	}
	public static WordFreqPair[] maxFreqWords(Trie trie, int n) {
		WordFreqPair[] out = new WordFreqPair[n];
		for ( int i=0; i<n; i++ ) {
			WordFreqPair max = maxFreqWord(trie);
			trie.deleteAll(max.word);
			out[i] = max;
		}
		return out;
	}

	public static Trie readFileToTrie(String filename) {
		ArrayList<String> wordList = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new File(filename));
			// String pattern = "[a-zA-Z]";
			while ( sc.hasNext() ) {
				String s = sc.next();
				s = Normalizer.normalize(s, Normalizer.Form.NFD);
				s = s.trim().toLowerCase();
				s = s.replaceAll("[^a-z]", "");
				wordList.add(s);
			}
			sc.close();
		} catch(Exception e) {;}
		Trie trie = new Trie();
		for ( String s : wordList ) {
			trie.insert(s);
		}
		return trie;
	}





	public static void main(String[] args) {
		// Trie trie = new Trie();
		// String[] words = {"test", "man", "tell", "top", "stop", "run", "test", "test", "top"};
		// for( String w : words ) {
		// 	trie.insert(w);
		// }
		// System.out.println(trie+"\n");

		// for ( WordFreqPair wf : maxFreqWords(trie, 3) ) {
		// 	System.out.println(wf);
		// }

		// Trie text = readFileToTrie("test.txt");
		// System.out.println(text);


		Trie novelle = readFileToTrie("novelle.txt");
		System.out.println("Read "+novelle.getSize()+" words from the novella into a trie:\n");
		// System.out.println(novelle);
		System.out.println("The 50 most used words in the novella are:\n");
		for ( WordFreqPair wf : maxFreqWords(novelle, 50) ) {
			System.out.println(wf);
		}
	}
}
