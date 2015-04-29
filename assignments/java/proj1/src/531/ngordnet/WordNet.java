
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {

	private Map<String, Set<Integer>> strings = new HashMap<String, Set<Integer>>();
	private Map<Integer, Set<String>> integers = new HashMap<Integer, Set<String>>();
	private Digraph digraph;	

/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
	public WordNet(String synsetFilename, String hyponymFIlename) {
		
		In sFile = new In(synsetFilename);
		In hFile = new In(hyponymFIlename);
		String[] syns = sFile.readAllLines();
		String[] hypos = hFile.readAllLines();
		digraph = new Digraph(syns.length);
		for (int i = 0; i < syns.length; i += 1) {
			String synset = syns[i];
			String[] split = synset.split(",");
			int number = Integer.parseInt(split[0]);
			String[] rest = split[1].split(" ");
			Set<String> hashSet = new HashSet<String>();
			for (String string : rest) {
				if (strings.keySet().contains(string) == false) {
					strings.put(string, new HashSet<Integer>());
					strings.get(string).add(number);
				}
				hashSet.add(string);
			}
			integers.put(number, hashSet);
		}
		for (int i = 0; i < hypos.length; i += 1) {
			String hypo = hypos[i];
			String[] split = hypo.split(",");
			int length = split.length;
			int[] list = new int[length];
			for (int j = 0; j < length; j += 1) {
				Integer.parseInt(split[j]);
			}
			for (int k = 0; k < list.length - 1; k += 1) {
				digraph.addEdge(list[0], list[k]);
			}
		}
	 }

	/** Returns the set of all hyponyms of WORD as well as all synonyms of
	* WORD. If WORD belongs to multiple synsets, return all hyponyms of
	* all of these synsets. See http://goo.gl/EGLoys for an example.
	* Do not include hyponyms of synonyms.
	*/
	public Set<String> hyponyms(String word) {
		
		Set<String> result = new HashSet<String>();
		Set<Integer> numbers = new HashSet<Integer>();
		Set<String> stringset = new HashSet<String>();
		numbers = strings.get(word);
		for (int number : numbers) {
			stringset = integers.get(number);
			for (String string : stringset) {
				result.add(string);
			}
		}
		return result;
	}
	
	/* Returns true if NOUN is a word in some synset. */
	public boolean isNoun(String noun) {
		
		for (Set<String> string : integers.values()) {
			for (String nouns : string) {
				if (nouns.equals(noun)) {
					return true;
				}
			}
		}
		return false;
	}

	/* Returns the set of all nouns. */
	public Set<String> nouns() {
		
		Set<String> result = new HashSet<String>();
		Set<Integer> words = integers.keySet();
		Set<String> word;
		for (int id : words) {
			word = integers.get(id);
			for (String string : word) {
				result.add(string);
			}
		}
		return result;
	}
}
