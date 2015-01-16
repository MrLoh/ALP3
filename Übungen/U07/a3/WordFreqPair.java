public class WordFreqPair {
	public String word;
	public int freq;
	public WordFreqPair(String word, int freq) {
		this.word = word;
		this.freq = freq;
	}

	public String toString() {
		return String.format("%6d : %s", this.freq, this.word.trim());
	}
}
