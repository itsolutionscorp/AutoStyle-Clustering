package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private ArrayList<Integer> synsetIDs;
    private ArrayList<String> synsetnouns; // can have spaces
    private ArrayList<String> synsetdef;
    private Map<Integer, ArrayList<Integer>> hyponymKeys;
    private ArrayList<String> hyponyms; // for hyponyms method
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetIDs = new ArrayList<Integer>();
        synsetnouns = new ArrayList<String>();
        synsetdef = new ArrayList<String>();
        hyponymKeys = new HashMap<Integer, ArrayList<Integer>>();
        makeSynsets(synsetFilename);
        makeHyponyms(hyponymFilename);
        makeDigraph();
    }

    private void makeSynsets(String synsetFilename) {
        In synsets = new In(synsetFilename);

        while (synsets.hasNextLine()) {
            String synsetline = synsets.readLine();
            String delims = "[,]";
            String[] tokens = synsetline.split(delims);
            synsetIDs.add(Integer.parseInt(tokens[0]));
            synsetnouns.add(tokens[1]);
            synsetdef.add(tokens[2]);
        }
    }

    private void makeHyponyms(String hyponymFilename) {
        In hyponymsfile = new In(hyponymFilename);

        while (hyponymsfile.hasNextLine()) {
            String hyponymline = hyponymsfile.readLine();
            String delims = "[,]";
            String[] tokens = hyponymline.split(delims);
            ArrayList<Integer> vals = new ArrayList<Integer>();
            String[] pointer = tokens;

            int key = 0;
            int counter = 0;
            while (counter < pointer.length) { 
                if (counter == 0) {
                    key = Integer.parseInt(pointer[0]);

                } else {
                    vals.add(Integer.parseInt(pointer[counter]));
                }
                counter += 1;
            }
            // add the key and add the vals to the map
            if (hyponymKeys.containsKey(key)) {
                ArrayList<Integer> valExisting = hyponymKeys.get(key);
                for (int num : valExisting) {
                    vals.add(num);
                }
            }
            hyponymKeys.put(key, vals);
        }
    }

    private void makeDigraph() {
        int numVertices = synsetnouns.size();
        digraph = new Digraph(numVertices);
        // iterate through the keys & connect key with vals
        Set<Integer> keys = hyponymKeys.keySet();
        for (int key : keys) {
            ArrayList<Integer> vals = hyponymKeys.get(key);
            for (int val : vals) {
                digraph.addEdge(key, val);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String string : synsetnouns) {
            String[] nounwords = string.split(" ");
            for (String splitstring : nounwords) {
                if (splitstring.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }


    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        for (String string : synsetnouns) {
            String[] nounsarray = string.split(" ");
            for (String noun : nounsarray) {
                nouns.add(noun);
            }
        }
        return nouns;
    }

    private ArrayList<Integer> hyponymshelperGetIndexes(String word) {
        ArrayList<Integer> indexesOfWord = new ArrayList<Integer>();
        String key = " ";
        int counter = 0;
        for (String string : synsetnouns) {
            String[] nounwords = string.split(" ");
            for (String splitstring : nounwords) {
                if (splitstring.equals(word)) { // find the string inside of synset
                    key = splitstring;
                    for (String x : nounwords) {
                        hyponyms.add(x);
                    }
                    indexesOfWord.add(counter); // get index of synset
                }
            }
            counter += 1;
        }
        hyponyms.add(key); // add the word to the set of hyponyms
        return indexesOfWord;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        hyponyms = new ArrayList<String>();
        ArrayList<Integer> indexesOfWord = hyponymshelperGetIndexes(word);
        ArrayList<Integer> synsetIDshypo = new ArrayList<Integer>();
        for (int index : indexesOfWord) {
            synsetIDshypo.add(synsetIDs.get(index));
        }
        Set<Integer> vertices = new HashSet<Integer>();
        for (int element : synsetIDshypo) {
            vertices.add(element);
        }

        Set<Integer> allhyponyms = GraphHelper.descendants(digraph, vertices);

        for (int hyponym : allhyponyms) { // integers
            int indexval = synsetIDs.indexOf(hyponym);
            String[] listofwords = synsetnouns.get(indexval).split(" ");
            for (String syn : listofwords) {
                hyponyms.add(syn);
            }
        }
        HashSet<String> vals2 = new HashSet<String>(hyponyms);  // return words as a set
        return vals2;
    } 
}
