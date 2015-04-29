package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordNet {

    private int numVertices = 0; // num lines in synset
    private Map<Integer, String[]> vertices = new HashMap<Integer, String[]>();
    private Map<String, Set<Integer>> nouns = new HashMap<String, Set<Integer>>();
    private Digraph dig;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // part 1: constructing two hashMaps
        try {
            BufferedReader syn = new BufferedReader(new FileReader(
                    synsetFilename));
            String currentLine;
            while ((currentLine = syn.readLine()) != null) {
                String[] tokens = currentLine.split(",");
                numVertices += 1;
                String[] synonyms = tokens[1].split(" "); // splits into
                                                          // synonyms
                int synId = Integer.parseInt(tokens[0]);
                vertices.put(synId, synonyms); // add entry to HashMap
                for (String noun : synonyms) {
                    if (!nouns.containsKey(noun)) { // if key not in there, add
                        nouns.put(noun, new HashSet<Integer>());
                    }
                    nouns.get(noun).add(synId); // add value to our key
                }
            }
            syn.close(); // got help from
                         // http://stackoverflow.com/questions/1277880/
                         // how-can-i-get-the-count-of-line-in-a-file-in-an-efficient-way
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("omg IO exception for synset file");
        }
        // part 2: constructing Digraph
        dig = new Digraph(numVertices);

        try {
            BufferedReader hyp = new BufferedReader(new FileReader(
                    hyponymFilename));
            String currentLine;
            while ((currentLine = hyp.readLine()) != null) {
                String[] tokens = currentLine.split(",");
                int i = 1;
                while (i < tokens.length) {
                    dig.addEdge(Integer.parseInt(tokens[0]),
                            Integer.parseInt(tokens[i]));
                    i += 1;
                }
            }
            hyp.close(); // got help from
                         // http://stackoverflow.com/questions/1277880/
                         // how-can-i-get-the-count-of-line-in-a-file-in-an-efficient-way
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("omg IO exception for hyponym file");
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /**
     * Returns the set of all hyponyms(descendants) of WORD as well as all
     * synonyms of WORD. If WORD belongs to multiple synsets, return all
     * hyponyms of all of these synsets. See http://goo.gl/EGLoys for an
     * example. Do not include hyponyms of synonyms.
     */

    public Set<String> hyponyms(String word) {
        if (word == null || !isNoun(word)) { // if word not in our dataset
            throw new IllegalArgumentException();
        }
        Set<Integer> childrenIds = ngordnet.GraphHelper.descendants(dig,
                nouns.get(word));
        Set<String> hypoSet = new HashSet<String>();
        for (int num : childrenIds) {
            String[] synArray = vertices.get(num);
            for (String newWord : synArray) {
                hypoSet.add(newWord);
            }
        }
        return hypoSet;
    }
}
