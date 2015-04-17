package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.io.File;
import java.util.Set;

public class WordNet {
	private Synsets x;

	public WordNet(String synsetFile, String hyponymFile) {
		x = new Synsets(synsetFile, hyponymFile);
	}

	public boolean isNoun(String word) {
		if (nouns().contains(word)) return true;
		return false;
	}

	public Set<String> nouns() {
		return x.getNouns();
	}

	public Set<String> hyponyms(String word) {
		HashSet<String> hypos = new HashSet<String>();
		HashSet<Integer> wordIDs = x.getWordIDSet(word);
		for (int id: wordIDs) {
			x.findHyponyms(hypos, id);
		}
		return hypos;
	}


}

