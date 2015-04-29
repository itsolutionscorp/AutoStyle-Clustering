package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

//Collaborated with Clay Smyth on data structure ideas and concepts.
public class WordNet {
    private Map<Integer, String[]> synsetnames; //key is synset ID
    private Map<String, Set<Integer>> nameset;
    private int digraphsize;
    private Digraph net;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        digraphsize = 0;
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        synsetnames = new HashMap<Integer, String[]>();
        nameset = new HashMap<String, Set<Integer>>();
        while (synsets.hasNextLine()) {
            digraphsize += 1;
            String line = synsets.readLine();
            String[] splitted = line.split(",");
            String[] words = splitted[1].split(" "); //array of words part of the synset
            synsetnames.put(Integer.valueOf(splitted[0]), words);
            for (int i = 0; i < words.length; i++) {
                if ((nameset.keySet()).contains(words[i])) { //if key is already in map?
                    (nameset.get(words[i])).add(Integer.valueOf(splitted[0])); 
                } else {
                    Set<Integer> keys = new HashSet<Integer>();
                    keys.add(Integer.valueOf(splitted[0]));
                    nameset.put(words[i], keys);  
                }
            }
        }
        net = new Digraph(digraphsize);
        while (hyponyms.hasNextLine()) {
            String hypline = hyponyms.readLine();
            String[] splitted = hypline.split(",", -1);
            int i = 1;
            while (i < splitted.length) {
                net.addEdge(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[i]));
                i += 1;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> allkeys = nameset.keySet();
        return allkeys.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nameset.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!(nameset.containsKey(word))) {
            Set<String> nothing = new HashSet<String>();
            return nothing;
        }
        Set<String> hypandsyn = new HashSet<String>();
        Set<Integer> synsetids = nameset.get(word); //id's associated with word
        Set<Integer> vertexes = GraphHelper.descendants(net, synsetids);
        Object[] idlist = synsetids.toArray();
        for (int i = 0; i < idlist.length; i++) {  //going through ID's 
            String[] syns = synsetnames.get(idlist[i]); //names in synset
            for (int x = 0; x < syns.length; x++) {
                hypandsyn.add(syns[x]);
            }
        }
        Object[] vertexids = vertexes.toArray();
        for (int j = 0; j < vertexids.length; j++) {
            String[] hyps = synsetnames.get(vertexids[j]); //names in synset
            for (int x = 0; x < hyps.length; x++) {
                hypandsyn.add(hyps[x]);
            }
        }
        hypandsyn.add(word);  //add own word?
        return hypandsyn;
    }
}
