package ngordnet;
import edu.princeton.cs.introcs.In;
import java.io.File;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    //Implements code from: http://stackoverflow.com/questions/2788080/reading-a-text-file-in-java
    private HashMap<Integer, ArrayList<String>> synMap;
    private ArrayList<String> n;
    private Digraph dig;

    public WordNet(String synsetFilename, String hyponymFilename) {
        n = new ArrayList<String>(); 
        synMap = new HashMap<Integer, ArrayList<String>>();
        In synset = new In(new File(synsetFilename));
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] parts = line.split(",");
            Integer key = Integer.parseInt(parts[0]);
            ArrayList<String> value = new ArrayList<String>(Arrays.asList(parts[1].split(" ")));
            for (String str : value) {
                n.add(str);
            }
            synMap.put(key, value);
        }
        synset.close();

        dig = new Digraph(synMap.size());
        HashMap<Integer, ArrayList<Integer>> hypMap = new HashMap<Integer, ArrayList<Integer>>();

        In hyponyms = new In(new File(hyponymFilename));
        while (hyponyms.hasNextLine()) {
            String hypline = hyponyms.readLine();
            String[] partz = hypline.split(",");
            Integer sset = Integer.parseInt(partz[0]);
            ArrayList<Integer> intlist = new ArrayList<Integer>();
            for (int i = 1; i < partz.length; i += 1) {
                intlist.add(Integer.parseInt(partz[i]));
                Integer intid = Integer.parseInt(partz[i]);
                dig.addEdge(sset, intid);
            }
            hypMap.put(sset, intlist);
        }
    } 



      // Returns true if NOUN is a word in some synset. 
    public boolean isNoun(String noun) {
        if (n.contains(noun)) {
            return true;
        } 
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounset = new HashSet<String>(n);
        return nounset;
    }

      /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    private ArrayList<String> getSynonyms(HashMap<Integer, ArrayList<String>> synMp, Integer key) {
        ArrayList<String> lst = new ArrayList<String>();
        for (String str : synMp.get(key)) {
            lst.add(str);
        }
        return lst;
    }


    public Set<String> hyponyms(String word) {
        ArrayList<String> lst = new ArrayList<String>();
        Set<Integer> ids = new HashSet<Integer>();
        for (Integer key : synMap.keySet()) {
            if (synMap.get(key).contains(word)) {
                ids.add(key);
                lst.addAll(getSynonyms(synMap, key));
            }
        }
        Set<Integer> descendantset = GraphHelper.descendants(dig, ids);
        for (Integer d : descendantset) {
            lst.addAll(getSynonyms(synMap, d));
        }
        ArrayList<String> finalist = new ArrayList<String>();
        for (String str : lst) {
            if (!finalist.contains(str)) {
                finalist.add(str);
            }
        }
        Set<String> synonymset = new HashSet<String>(finalist);
        return synonymset;
    }
}
