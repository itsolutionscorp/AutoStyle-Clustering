package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {

	private Map<Integer, Synset> synsets;
	private Map<Integer, HashSet<Integer>> hyponyms;
	private Digraph synset_graph;

	/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
	public WordNet(String synsetFilename, String hyponymFilename) {
		synsets = new HashMap<Integer, Synset>();
		hyponyms = new HashMap<Integer, HashSet<Integer>>();

		In synset_file = new In(synsetFilename);
		In hyponym_file = new In(hyponymFilename);

		String[] synset_lines = synset_file.readAllLines();
		String[] hyponym_lines = hyponym_file.readAllLines();

		synset_graph = new Digraph(synset_lines.length);

		for (int i = 0; i < synset_lines.length; i++) {
			String[] id_elem_def = synset_lines[i].split(",");
			Synset ith_synset = new Synset();
			ith_synset.setDefinition(id_elem_def[2]);
			String[] ith_synset_elem = id_elem_def[1].split(" ");
			for (int j = 0; j < ith_synset_elem.length; j++) {
				ith_synset.addElement(ith_synset_elem[j]);
			}
			synsets.put(Integer.parseInt(id_elem_def[0]), ith_synset);
		}

		for (int i = 0; i < hyponym_lines.length; i++) {
			String[] id_hyponyms = hyponym_lines[i].split(",");
			HashSet<Integer> ith_hyponyms = new HashSet<Integer>();
			for (int j = 1; j < id_hyponyms.length; j++) {
				ith_hyponyms.add(Integer.parseInt(id_hyponyms[j]));
			}
			if (!hyponyms.containsKey(Integer.parseInt(id_hyponyms[0]))) {
				hyponyms.put(Integer.parseInt(id_hyponyms[0]), ith_hyponyms);
			} else {
				hyponyms.get(Integer.parseInt(id_hyponyms[0])).addAll(ith_hyponyms);
			}
		}

		for (Integer id : hyponyms.keySet()) {
			for (Integer hypo : hyponyms.get(id)) {
				synset_graph.addEdge(id, hypo);
			}
		}

	}

	/* Returns true if NOUN is a word in some synset. */
	public boolean isNoun(String noun) {
		for (Integer id : synsets.keySet()) {
			if (synsets.get(id).elements.contains(noun)) {
				return true;
			}
		}
		return false;
	}

	/* Returns the set of all nouns */
	public Set<String> nouns() {
		Set<String> nouns = new HashSet<String>();
		for (Integer id : synsets.keySet()) {
			nouns.addAll(synsets.get(id).elements);
		}
		return nouns;
	}

	/* Returns the set of all hyponyms of WORD including WORD itself */
	public Set<String> hyponyms(String word) {
		Set<String> word_hyponyms = new HashSet<String>();
		Set<Integer> synset_ids = new HashSet<Integer>();
		if (!isNoun(word)) {
			word_hyponyms.add(word);
			return word_hyponyms;
		}

		for (Integer id : synsets.keySet()) {
			if (synsets.get(id).elements.contains(word)) {
				synset_ids.add(id);
			}
		}

		for (Integer id2 : GraphHelper.descendants(synset_graph, synset_ids)) {
			word_hyponyms.addAll(synsets.get(id2).elements);
		}
		return word_hyponyms;
	}

	private class Synset {
		private Set<String> elements;
		private String definition;

		public Synset() {
			elements = new HashSet<String>();
			definition = "";
		}

		public void addElement(String elem) {
			elements.add(elem);
		}

		public void setDefinition(String def) {
			definition = def;
		}

		private String getDefinition() {
			return this.definition;
		}
	}
}
