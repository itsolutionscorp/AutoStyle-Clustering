package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Iterator;

public class WordNet {
    /** Creates a WordNet using files for m SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String> synToIds;
    private HashMap<String, ArrayList<Integer>> nounsToIds;
    private HashMap<String, String> definitions;
    private HashMap<String, HashSet<String>> synonyms;
    private Digraph synNet;
    private HashSet<String> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        definitions = new HashMap<String, String>();
        synToIds = new HashMap<Integer, String>();
        nouns = new HashSet<String>();
        synonyms = new HashMap<String, HashSet<String>>();
        nounsToIds = new HashMap<String, ArrayList<Integer>>();
        In synset = new In(synsetFilename);
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] valuesSepByComma = line.split(",");
            int id = new Integer(valuesSepByComma[0]);
            synToIds.put(id, valuesSepByComma[1]);
            definitions.put(valuesSepByComma[1], valuesSepByComma[2]);
            String[] syns = valuesSepByComma[1].split("\\s+");
            for (String syn:syns) {
                nouns.add(syn);
                if (nounsToIds.containsKey(syn)) {
                    nounsToIds.get(syn).add(id);
                } else  {
                    ArrayList<Integer> arrayThingy = new ArrayList<Integer>();
                    arrayThingy.add(id);
                    nounsToIds.put(syn, arrayThingy);
                }
                if (synonyms.containsKey(syn)) {
                    for (String synonymWords:syns) {
                        synonyms.get(syn).add(synonymWords);
                    }
                } else  {
                    HashSet<String> setThingy = new HashSet<String>();
                    for (String synonymWords:syns) {
                        setThingy.add(synonymWords);
                    }
                    synonyms.put(syn, setThingy);
                }
            }
        }
        synNet = new Digraph(synToIds.size());
        In hyponyms = new In(hyponymFilename);
        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] valuesSepByComma = line.split(",");
            Integer wordVal = new Integer(valuesSepByComma[0]);
            for (int i = 1; i < valuesSepByComma.length; i++) {
                Integer hyp = new Integer(valuesSepByComma[i]);
                synNet.addEdge(wordVal,  hyp);
            }
        }
    }

    /* Returns true if  NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> wordIDs = nounsToIds.get(word);
        Set<Integer> idsToGet = new HashSet<Integer>(wordIDs);
        for (Integer i:wordIDs) {
            idsToGet.addAll(hyponyms(i));
        }
        Set<String> returnMe = new HashSet<String>();
        for (Integer i:idsToGet) {
            String[] brokenSyns = synToIds.get(i).split("\\s+");
            for (String wordFromBroken:brokenSyns) {
                returnMe.add(wordFromBroken);
            }
        }
        return returnMe;
    }

    private Set<Integer> hyponyms(Integer vert) {
        Iterator<Integer> adjVerts = synNet.adj(vert).iterator();
        Set<Integer> subVerts = new HashSet<Integer>();
        while (adjVerts.hasNext()) {
            Integer nex = adjVerts.next();
            subVerts.add(nex);
            subVerts.addAll(hyponyms(nex));
        }
        return subVerts;
    }
}
