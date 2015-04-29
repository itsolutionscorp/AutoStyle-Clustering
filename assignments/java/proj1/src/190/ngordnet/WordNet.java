package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, List<String>> synsetMap;
    private Digraph WordNet;

	/** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	In synsetFile = new In(synsetFilename);
    	synsetMap = new HashMap<Integer, List<String>>();
    	while (synsetFile.hasNextLine()) {
    		String line = synsetFile.readLine();
    		String[] rawTokens = line.split(",");
    		int id = Integer.parseInt(rawTokens[0]);
    		String nouns = rawTokens[1];
    		String[] nounTokens = nouns.split(" ");
    		List<String> synsetList = new ArrayList<String>();
    		for (String s : nounTokens) {
    			synsetList.add(s);
    		}
    		synsetMap.put(id, synsetList);
    	}
    	In hyponymFile = new In(hyponymFilename);
    	WordNet = new Digraph(synsetMap.size());
    	while (hyponymFile.hasNextLine()) {
    		String line = hyponymFile.readLine();
    		String[] rawTokens = line.split(",");
    		int synsetID = Integer.parseInt(rawTokens[0]);
    		for (String s : rawTokens) {
    			WordNet.addEdge(synsetID,Integer.parseInt(s));
    		}
    	}
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
    	for (List<String> synsetList : synsetMap.values()) {
    		if (synsetList.contains(noun)) {
    			return true;
    		}
    	}
    	return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	Set<String> setOfNouns = new HashSet<String>();
    	for (List<String> synsetList : synsetMap.values()) {
    		for (String noun : synsetList) {
    			setOfNouns.add(noun);
    		}
    	}
    	return setOfNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
    	Set<Integer> w = new HashSet<Integer>();
    	Set<String> hList = new HashSet<String>();
    	hList.add(word);
    	int id = 0;
    	while (id < synsetMap.size()) {
    		List<String> synsetList = synsetMap.get(id);
    		if (synsetList.contains(word)) {
    			int j = 0;
    			while (j < synsetList.size()) {
    				hList.add(synsetList.get(j));
    				j++;
    			}
    			w.add(id);
    		}
    		id++;
    	}
    	w.addAll(GraphHelper.descendants(WordNet, w));
    	for (Integer i : w) {
    		hList.addAll(synsetMap.get(i));
    	}
    	return hList;
    }
}