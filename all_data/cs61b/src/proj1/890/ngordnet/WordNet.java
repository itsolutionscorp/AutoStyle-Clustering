package ngordnet;

import edu.princeton.cs.algs4.Digraph;


import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.introcs.In; 
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;


public class WordNet {
    private class ComparableTreeSet<S> extends TreeSet<S> 
        implements Comparable<ComparableTreeSet<S>> {
        private int num = 0;

        public int compareTo(ComparableTreeSet<S> cs) {
            return num - cs.num;
        }
    }

    private Set<String> nouns = new ComparableTreeSet<String>();
    private LinkedList<String> synonyms = new LinkedList<String>();
    private TreeMap<Integer, LinkedList<String>> synsets = 
        new TreeMap<Integer, LinkedList<String>>();
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
         * Task 1: store all synsets in ADT.
         * Task 2: create hyponym digraph from synset ADT */

        //Task1
        In syn = new In(synsetFilename);
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] elements = line.split(",");
            Integer num = Integer.parseInt(elements[0]);
            String[] words = elements[1].split(" ");
            for (String word : words) {
                nouns.add(word);
                synonyms.add(word);
            }
            synsets.put(num, synonyms);
            synonyms = new LinkedList<String>();
        }

        //Task 2
        hyponyms = new Digraph(synsets.size());
        In hypoScan = new In(hyponymFilename);
        while (hypoScan.hasNextLine()) {
            String line = hypoScan.readLine();
            String[] currArray = line.split(",");
            for (int k = 1; k < currArray.length; k++) {
                int index0 = Integer.parseInt(currArray[0]);
                int indexi = Integer.parseInt(currArray[k]);
                hyponyms.addEdge(index0, indexi);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
         * If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
         * See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms. */

        TreeSet<String> allHypos = new TreeSet<String>();
        TreeSet<Integer> ids = new TreeSet<Integer>(); 
        for (Map.Entry<Integer, LinkedList<String>> item : synsets.entrySet()) {
            if (item.getValue().contains(word)) {
                ids.add(item.getKey()); // add index of sets containing word
            }
        }
        Set<Integer> hypoIDs = GraphHelper.descendants(hyponyms, ids);
        for (int n : hypoIDs) {
            allHypos.addAll(synsets.get(n));
        }
        return new TreeSet<String>(allHypos);
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }
}
