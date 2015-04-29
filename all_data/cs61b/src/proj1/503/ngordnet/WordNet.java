package ngordnet;

import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
	private HashMap<Integer, String> synstrings; // Dictionary of all ID's
													// matched to their sets of
													// synstrings
	private HashMap<String, Integer> wordconverter; // Dictionary of all
													// synonyms matched to their
													// ID's
	private Digraph diegraff; // directed graph tracking the is-a relationships
								// between words
	private HashSet<String> top = new HashSet();

	public WordNet(String syns, String hyps) {
		In synsets = new In(syns);
		In hypables = new In(hyps);
		synstrings = new HashMap();
		wordconverter = new HashMap();
		String[] all = synsets.readAllLines();
		// System.out.println(all[0]);
		// System.out.println(all[0].split(",")[0]);

		for (int i = 0; i < all.length; i++) {
			String[] values = all[i].split(",");
			synstrings.put(Integer.valueOf(values[0]), values[1]);
			wordconverter.put(values[1], Integer.valueOf(values[0]));
		}

		int v = synstrings.size();
		diegraff = new Digraph(v);

		String[] tree = hypables.readAllStrings();
		for (int i = 0; i < tree.length; i++) {
			String[] verteces = tree[i].split(",");
			// System.out.println(verteces[0]);
			for (int j = 0; j < verteces.length; j++) { // words that are
														// hypables of an ID
				if (j != 0) {
					diegraff.addEdge(Integer.valueOf(verteces[0]),
							Integer.valueOf(verteces[j]));
				}
			}
			top.add(verteces[0]);
		}
	}

	public Set<String> hyponyms(String word) { // return all the hyponyms of a
												// word. check if multiple words
												// in multiple sets, also if
												// word in multiple sets.
		int id = -1;
		HashSet setid = new HashSet();
		for (String x : wordconverter.keySet()) {
			String[] search = x.split(" "); // split synstrings by spaces to get
											// individual words
			for (String t : search) {
				if (t.equals(word)) {
					id = wordconverter.get(x); // get the id of the synstring
												// where the word is.
					setid.add(id);
					// Question do I need to check if there is some id? what
					// happens if I pass in a fake word?
				}
			}
		}
		// System.out.println(setid.isEmpty());
		Set<String> returnable = new HashSet<String>();
		Set<Integer> newset = GraphHelper.descendants(this.diegraff, setid);
		// while (!newset.isEmpty()) {
		// Set<Integer> willbenewset = new HashSet<Integer>();
		for (Integer x : newset) {
			String sett = synstrings.get(x);
			String[] newsearch = sett.split(" ");
			for (String t : newsearch) {
				returnable.add(t);
			}
			// if (top.contains(synstrings.get(x))) {
			// willbenewset.add(x);
			// }
		}
		// newset = willbenewset;
		// willbenewset.clear();
		// }
		return returnable;
	}

	/* Returns true if NOUN is a word in some synset. */
	public boolean isNoun(String noun) {
		return (this.nouns().contains(noun) == true);
	}

	/* Returns the set of all nouns. */
	public Set<String> nouns() {
		Set<String> ret = new HashSet<String>();
		for (String x : wordconverter.keySet()) { // This is a set of strings of
													// synsets.
			String[] add = x.split(" ");
			for (String t : add) {
				ret.add(t);
			}
		}
		return ret;
	}

}
