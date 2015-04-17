package ngordnet;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private int netSize;
    private Digraph net;
    private Set<String> nounSet = new TreeSet<String>();
    private Map<Integer, String[]> synsetIDMap = new TreeMap<Integer, String[]>();

    /* WordNet class constructor */
    public WordNet(String synsetFilename, String hyponymFilename) {
        netSize = netSize(synsetFilename);
        net = new Digraph(netSize);
        net = digraphSetUp(net, hyponymFilename);
        synsetIDMap = makeIDTSMap(synsetIDMap, synsetFilename);
        nounSet = allNouns(synsetIDMap);
    } 

    /* Returns the size the Digraph should be */
    private int netSize(String synsetFilename) {
        In synsetIn = new In(synsetFilename);
        String last = new String();
        while (synsetIn.hasNextLine()) {
            last = synsetIn.readLine();
        }
        String[] lastLineArray = last.split(",");
        return Integer.parseInt(lastLineArray[0]) + 1;
    }

    /* Returns a digraph which links relationships between hyponyms */
    private Digraph digraphSetUp(Digraph D, String hyponymFilename) {
        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String[] elements = hyponymIn.readLine().split(",");
            int vertex = Integer.parseInt(elements[0]);
            for (int i = 1; i < elements.length; i += 1) {
                D.addEdge(vertex, Integer.parseInt(elements[i]));
            }
        }
        return D;
    }

    /* Returns a map from a noun's ID to a Set of its Synonyms, including itself */
    private Map<Integer, String[]> makeIDTSMap(Map<Integer, String[]> synIDMap, String synFile) {
        In synsetIn = new In(synFile);
        while (synsetIn.hasNextLine()) {
            String[] rawElements = synsetIn.readLine().split(",");
            int id = Integer.parseInt(rawElements[0]);
            String[] synonyms = rawElements[1].split(" ");
            synIDMap.put(id, synonyms);
        }
        return synIDMap;
    }

    /* For use in the constructor; Returns the set of all nouns. */
    private Set<String> allNouns(Map<Integer, String[]> synIDMap) {
        Set<String> nouns = new TreeSet<String>();
        for (int i = 0; i < netSize; i += 1) {
            String[] synonyms = synIDMap.get(i);
            for (int j = 0; j < synonyms.length; j += 1) {
                nouns.add(synonyms[j]);
            }
        }
        return nouns;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
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
        Set<String> output = new TreeSet<String>();
        Set<Integer> synsetIDs = new TreeSet<Integer>();
        for (int i = 0; i < netSize; i += 1) {
            for (int j = 0; j < synsetIDMap.get(i).length; j += 1) {
                if (synsetIDMap.get(i)[j].equals(word)) {
                    synsetIDs.add(i);
                }
            }
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(net, synsetIDs);
        for (Integer id : hyponymIDs) {
            String[] finalHyps = synsetIDMap.get(id);
            for (int i = 0; i < finalHyps.length; i += 1) {
                output.add(finalHyps[i]);
            }
        }
        return output;

    }

}
