package ngordnet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

	private Hashtable<Integer, ArrayList> synset;
	private Hashtable<Integer, ArrayList> hyposet;
	private HashSet<String> all_words;
	private Digraph digraph;

	public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
		In in = new In(synsetFilename);
		String[] lines = in.readAllLines(); // reads text file into lines

		Hashtable<Integer, ArrayList> synset = new Hashtable<Integer, ArrayList>();
		Hashtable<Integer, ArrayList> hyposet = new Hashtable<Integer, ArrayList>();
		HashSet<String> all_words = new HashSet<String>();


		for (int i = 0; i < lines.length; i = i + 1) {
			String[] line_parts = lines[i].split(",");

			int key = Integer.parseInt(line_parts[0]);
			String value = line_parts[1];
			// In case there is more than one word e.g. jump parachuting

			String[] words = value.split(" ");

			ArrayList<String> element = new ArrayList<String>(Arrays.asList(words));
			synset.put(key, element);
		}


		In read_hypo = new In(hyponymFilename);
		String[] lines_hyponyms = read_hypo.readAllLines();

        Digraph digraph = new Digraph(synset.size());
        
		for (int k = 0; k < lines_hyponyms.length; k = k + 1) {
			String[] hyp_line_parts = lines_hyponyms[k].split(",");
			int hkey = (Integer.parseInt(hyp_line_parts[0]));
			ArrayList<Integer> helement = new ArrayList<Integer>();
			for (int j = 1; j < hyp_line_parts.length; j = j + 1) {
				helement.add(Integer.parseInt(hyp_line_parts[j]));
				digraph.addEdge(hkey, Integer.parseInt(hyp_line_parts[j]));
			}
			hyposet.put(hkey, helement);
		}

		/*
		Digraph digraph = new Digraph(lines_hyponyms.length);
			for (Map.Entry<Integer, ArrayList> entry : hyposet.entrySet()) {
				for (int count = 0; count < hyposet.get(entry).size(); count = count + 1) {
					digraph.addEdge(entry, hyposet.get(entry).get(count));
				}
			}*/

		for (int a = 0; a < lines.length; a = a + 1) {					// Adds all the words to an ArrayList
			for (int b = 0; b < synset.get(a).size(); b = b + 1) {
				String to_add = (String) synset.get(a).get(b);
				if (all_words.contains(to_add) == false) {
					all_words.add((String) to_add);
				}
			}
		}

		this.all_words = all_words;
		this.synset = synset;
		this.hyposet = hyposet;
		this.digraph = digraph;
	}

	public static void main(java.lang.String[] args) {
		//WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
		/*
    	System.out.println("Hypnoyms of increase:");
        for (String noun : wn.hyponyms("increase")) {
            System.out.println(noun);
        }
		*/
        /*
        System.out.println("Hypnoyms of jump:");
        for (String noun : wn.hyponyms("jump")) {
            System.out.println(noun);
        }  
        */

        /* The code below should print the following (maybe not in this order):
            Hypnoyms of change:
            alteration
            saltation
            modification
            change
            variation
            increase
            transition
            demotion
            leap
            jump        
        */

        /** From: http://goo.gl/EGLoys */

        


	}
	// Returns true if argument is a noun
	public boolean isNoun(java.lang.String noun) {
	if (this.all_words.contains(noun))
		return true;
	else
		return false;
	
	}

	public java.util.Set<java.lang.String> nouns() {
		return all_words;
	}

	public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
		// Find the key that the word refers to
		// Add synoynms if any
		// Find the hyponym keys from the text file (the keys after the first key)
		// Add the hyponyms (and their hyponyms) to the collection.
		Set<String> hyponyms = new HashSet<String>();

		// Iterate through the Hashmap
		Set<Integer> keys = new HashSet<Integer>();
		for (Map.Entry<Integer, ArrayList> entry : synset.entrySet()) {					// Returns keys that correspond to word
			if (entry.getValue().contains(word)) {
				keys.add(entry.getKey());
			}
		}
		Set<Integer> reachable = new TreeSet<Integer>();
		for (Integer key1 : keys) {
			reachable.add(key1);
		}
		Set<Integer> descend = new HashSet<Integer>();
		descend = GraphHelper.descendants(this.digraph, reachable);
		
		Set<Integer> newkeys = new HashSet<Integer>(keys);
		newkeys.addAll(descend);



		// Iterate through the Hashset and add the corresponding words and synonyms.
		for (Integer key1 : descend) {
			ArrayList list1 = synset.get(key1);					// List of synonyms
			for (int z = 0; z < list1.size(); z = z + 1) {
				hyponyms.add((String) list1.get(z));
			}

			// Add hyponyms
			// Need to look up key in hyposet. Then take the keys provided and look those up in synset.
			// In hyposet: 5 = (6, 7)
			/*
			ArrayList hyposet_keys = hyposet.get(key1); 					// returns the array of hyponyms (6,7)
			System.out.println(hyposet_keys);
			if (hyposet_keys != null) {
				for (int y = 0; y < hyposet_keys.size(); y = y + 1) {			// For 6, For 7
					for (int x = 0; x < synset.get(hyposet_keys.get(y)).size(); x = x + 1) { // For the arraylist that 6 refers to
						hyponyms.add((String) synset.get(hyposet_keys.get(y)).get(x));
					}
					
				}	
			}
			*/
			//next_hyponym = 

		}
		return hyponyms;
	}
}