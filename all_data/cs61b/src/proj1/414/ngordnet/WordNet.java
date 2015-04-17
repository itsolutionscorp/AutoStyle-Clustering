package ngordnet;

import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

	private Digraph graph; //graph of connections
	private Map<Integer, Set<String>> syn; //map of index to synset, index is index of synset
	private Map<String, Set<Integer>> words; //map of words to set of indices of synsets

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	Scanner synfile;
    	Scanner hypfile;

    	try {
	    	synfile = new Scanner(new File(synsetFilename));
	    	hypfile = new Scanner(new File (hyponymFilename));
	    }
	    catch (Exception e) {
	    	System.out.println("ERROR");
	    	return;
	    }

    	syn = new HashMap<Integer, Set<String>>();
    	while (synfile.hasNext()) {
    		Set<String> set = new HashSet<String>();
    		String[] a = synfile.nextLine().split(",");
    		for (String word : a[1].split(" "))
    			set.add(word);
    		syn.put(Integer.parseInt(a[0]), set);
    	}

    	graph = new Digraph(syn.size());
    	while (hypfile.hasNext()) {
    		String[] a = hypfile.nextLine().split(",");
    		for (int i = 1; i < a.length; i++)
    			graph.addEdge(Integer.parseInt(a[0]), Integer.parseInt(a[i]));
    	}
    	
    	words = new HashMap<String, Set<Integer>>();
    	Iterator<Integer> iter = syn.keySet().iterator();
    	while (iter.hasNext()) {
    		int i = iter.next();
    		for (String s : syn.get(i)){
    			if (!words.keySet().contains(s))
    				words.put(s, new HashSet<Integer>());
    			words.get(s).add(i);
    		}
    	}
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
    	return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	return words.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
    	if (!isNoun(word))
    		return null;
    	Set<String> result = new HashSet<String>();
    	for (int i : words.get(word))
    		for (String s : syn.get(i))
    			result.add(s);
    	Set<Integer> desc = GraphHelper.descendants(graph, words.get(word));
    	for (int i : desc)
    		for (String s : syn.get(i))
    			result.add(s);
    	return result;
    }
}