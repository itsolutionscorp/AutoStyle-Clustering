package ngordnet;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph wordNet;
    private HashMap<Integer, Set<String>> synsetMap;
    private Set<String> nounSet;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        synsetMap = new HashMap<Integer, Set<String>>();
        nounSet = new HashSet<String>();

        //read through all lines of SYNSETFILENAME and store data in SYNSETMAP
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();
            String[] splittedLine = line.split(",");
            int synsetId = Integer.parseInt(splittedLine[0]);
            String synsetString = splittedLine[1];
            Set<String> synset = new HashSet<>();
            String[] splittedSynset = synsetString.split(" ");
            for (String s : splittedSynset) {
                nounSet.add(s);
                synset.add(s);
            }
            synsetMap.put(synsetId, synset);
        }

        //Instantiate new digraph with size of SYNSETMAP as number of vertices
        wordNet = new Digraph(synsetMap.size());

        //read through all lines of HYPONYMFILENAME and addEdges accordingly.
        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            ArrayList<Integer> listIds = new ArrayList<Integer>();
            String id = "";
            //seperate ids from each other into an arraylist,
            //with synsetid of hyponym at index 0
            for (int i = 0; i < line.length(); i++) {
                if (",".equals(line.charAt(i) + "")) {
                    listIds.add(Integer.parseInt(id));
                    id = "";
                } else {
                    id = id + line.charAt(i);
                }
            }
            listIds.add(Integer.parseInt(id));

            //add edges
            for (int i = 1; i < listIds.size(); i++) {
                wordNet.addEdge(listIds.get(0), listIds.get(i));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Collection<Set<String>> col = synsetMap.values();
        for (Set<String> s : col) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new HashSet<String>();
        Set<Integer> ids = new TreeSet<>();
        Set<Map.Entry<Integer, Set<String>>> synsetSet = synsetMap.entrySet();
        for (int key : synsetMap.keySet()) {
            if (synsetMap.get(key).contains(word)) {
                ids.add(key);
                for (String w : synsetMap.get(key)) {
                    hyponymSet.add(w);
                }
            }
        }
        for (int descendant : GraphHelper.descendants(wordNet, ids)) {
            for (String w : synsetMap.get(descendant)) {
                hyponymSet.add(w);
            }
        }
        return hyponymSet;
    }
}
