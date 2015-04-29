package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    // Integer => key, HasSet => value
    private static HashMap<Integer, HashSet<String>> map;
    private static HashMap<Integer, HashSet<Integer>> hmap;
    private static HashSet<String> set;
    private static HashSet<Integer> hyponymSet;
    private static Digraph dg;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        String line;
        int sid; // synset id
        String defn;
        String[] synonyms;
        String[] hyponyms;
        int hid; // hyposet id
        /* --- map with all the entries --- */
        map = new HashMap<Integer, HashSet<String>>();
        hmap = new HashMap<Integer, HashSet<Integer>>();
        /* --- Place all the synonyms into a HashMap --- */
        try {
            Scanner sc = new Scanner(new File(synsetFilename));
            /* --- Read line by line --- */
            while (sc.hasNextLine()) {
                /* --- sets with all synonyms --- */
                set = new HashSet<String>();
                line = sc.nextLine();
                String[] items = line.split(",");
                sid = Integer.parseInt(items[0]);
                synonyms = items[1].split(" ");
                defn = items[2];
                /* --- add all the synonyms to current set --- */
                for (int i = 0; i < synonyms.length; i++) {
                    set.add(synonyms[i]);
                }
                map.put(sid, set);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        /* --- Place all hyponyms into a Digraph ---*/
        try {
            Scanner sch = new Scanner(new File(hyponymFilename));
            // Allocate digprah wihth map.size() vertices
            dg = new Digraph(map.size());
            /* --- Read line by line --- */
            while (sch.hasNextLine()) {
                /* ifPlace all the hyponyms in the digraph --- */
                line = sch.nextLine();
                hyponyms = line.split(",");
                hid = Integer.parseInt(hyponyms[0]);
                hyponymSet = new HashSet<Integer>();
                /* --- add all the synonyms to current set --- */
                for (int i = 1; i < hyponyms.length; i++) {
                    int hyp = Integer.parseInt(hyponyms[i]);
                    hyponymSet.add(hyp);
                    dg.addEdge(hid, hyp);
                }
                hmap.put(hid, hyponymSet);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (int i = 0; i < map.size(); i++) {
            allNouns.addAll(map.get(i));
        }
        return allNouns;
    }
    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.
    public Set<String> hyponyms(String word) {
        HashSet<String> all = new HashSet<String>();
        Set<String> synAccum = new HashSet<String>();
        HashSet<Integer> hypIds = new HashSet<Integer>(); 
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).contains(word)) {
                synAccum.addAll(map.get(i));
                synAccum.remove(word);
                hypIds.add(i);
            }
        }
        Set<Integer> allVertices = GraphHelper.descendants(dg, hypIds);
        Integer[] allVerticesArray = allVertices.toArray(new Integer[allVertices.size()]);
        for (int k = 0; k < allVerticesArray.length; k++) {
            all.addAll(map.get(allVerticesArray[k]));
        }
        return all;
    }
}
