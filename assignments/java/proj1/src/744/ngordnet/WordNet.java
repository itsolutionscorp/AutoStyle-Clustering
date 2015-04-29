package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import edu.princeton.cs.introcs.In;
//Worked with Finn Howell and Nate Holmes

public class WordNet {
    private int size;
    private In synset;
    private In hyponym;
    private Map<Integer, String[]> keyID;
    private Map<String, HashSet<Integer>> keyNoun;
    private GraphHelper helper;
    private Digraph net;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        keyID = new HashMap<Integer, String[]>();
        keyNoun = new HashMap<String, HashSet<Integer>>();
        String line;
        String[] n;
        String[] individual;
        helper = new GraphHelper();
        size = 0;
        while (synset.hasNextLine()) {
            size += 1;
            line = synset.readLine();
            n = line.split(","); //reads different parts of each line
            individual = n[1].split(" "); //string array of syns of particular synset
            keyID.put(Integer.valueOf(n[0]), individual); //adds key-ID and value-synset to map
            for (int i = 0; i < individual.length; i++) { //adds to keyNoun the ID of the synset
                if (!keyNoun.containsKey(individual[i])) {
                    keyNoun.put(individual[i], new HashSet<Integer>());
                    keyNoun.get(individual[i]).add(Integer.parseInt(n[0]));
                } else {
                    keyNoun.get(individual[i]).add(Integer.parseInt(n[0]));
                }
            }
        }
        net = new Digraph(size);
        while (hyponym.hasNextLine()) { //creates Digraph
            line = hyponym.readLine();
            n = line.split(",");
            for (int i = 1; i < n.length; i++) {
                net.addEdge(Integer.parseInt(n[0]), Integer.parseInt(n[i]));
            }
        }
    } 

    /** Returns true if NOUN is a word in some synset.*/
    public boolean isNoun(String noun) {
        return keyNoun.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return keyNoun.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> associateWords = new HashSet<String>();
        associateWords.add(word);
        String[] syn;
        Integer id;
        if (isNoun(word)) {
            Iterator<Integer> iterator = keyNoun.get(word).iterator();
            while (iterator.hasNext()) {
                id = iterator.next(); //goes through ID's of synsets
                syn = keyID.get(id); //recieves string[] of nouns of synset ID
                for (int w = 0; w < syn.length; w++) { //adds those synomyms and their hyponyms
                    associateWords.add(syn[w]);
                }
            }
            Set<Integer> hyps = helper.descendants(net, keyNoun.get(word));
            Iterator<Integer> iterator2 = hyps.iterator();
            while (iterator2.hasNext()) {
                id = iterator2.next(); //goes through ID's of synsets which are hyponyms of word
                syn = keyID.get(id); //recieves string[] of nouns of the particular synset ID
                for (int w = 0; w < syn.length; w++) { //adds those synomyms and their hyponyms
                    associateWords.add(syn[w]);
                }
            }
        }
        return associateWords;
    }
}
