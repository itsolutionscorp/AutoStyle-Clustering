package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, HashSet<String>> intToString; //all words in synset of id
    private HashMap<String, HashSet<Integer>> stringToInt; //id of each synset word belongs to
    private Digraph d;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        
        In countLines = new In(synsetFilename);
        int numLines = 0;
        while (countLines.hasNextLine()) {
            countLines.readLine();
            numLines += 1;
        }
        d = new Digraph(numLines);
        intToString = new HashMap<Integer, HashSet<String>>();
        stringToInt = new HashMap<String, HashSet<Integer>>();

        In readSynset = new In(synsetFilename);
        while (!readSynset.isEmpty()) {
            String nextLine = readSynset.readLine();
            String[] fields = nextLine.split(",");
            int ind = Integer.parseInt(fields[0]);

            HashSet<String> synonyms = new HashSet<String>();
            String[] allSyn = fields[1].split(" ");
            int synIndex = 0;
            while (synIndex < allSyn.length) {
                String syn = allSyn[synIndex];
                synonyms.add(syn);
                if (stringToInt.containsKey(syn)) {
                    stringToInt.get(syn).add(ind); 
                } else {
                    HashSet<Integer> newSet = new HashSet<Integer>();
                    newSet.add(ind);
                    stringToInt.put(syn, newSet);
                }
                synIndex += 1;
            }
            intToString.put(ind, synonyms);
        }
        
        In readHyp = new In(hyponymFilename);
        while (!readHyp.isEmpty()) {
            String nextLine = readHyp.readLine();
            String[] edges = nextLine.split(",");
            int orig = Integer.parseInt(edges[0]);
            int hypIndex = 1;
            while (hypIndex < edges.length) {
                int nextInd = Integer.parseInt(edges[hypIndex]);
                d.addEdge(orig, nextInd);
                hypIndex += 1;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return stringToInt.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return stringToInt.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (stringToInt.containsKey(word)) {
            HashSet<Integer> indices = stringToInt.get(word);
            Set<Integer> descendants = GraphHelper.descendants(d, indices);
            HashSet<String> toReturn = new HashSet<String>();
            for (Integer i : indices) {
                for (String j : intToString.get(i)) {
                    toReturn.add(j);
                }
            }
            for (Integer k : descendants) {
                for (String l : intToString.get(k)) {
                    toReturn.add(l);
                }
            }
            return toReturn;
        }
        return null;
    }

}
