package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Arrays;

public class WordNet {

    private In synsetReader, hyponymReader;
    private HashMap<Integer, TreeSet<String>> synonyms;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetReader = new In(synsetFilename);
        hyponymReader = new In(hyponymFilename);
        synonyms = new HashMap<Integer, TreeSet<String>>();
        while (synsetReader.hasNextLine()) {
            String line = synsetReader.readLine();
            String[] strArr = line.split(",");
            int key = Integer.parseInt(strArr[0]);

            synonyms.put(key, new TreeSet<String>(Arrays.asList(strArr[1].split(" "))));
        }
        graph = new Digraph(synonyms.values().size());
        while (hyponymReader.hasNextLine()) {
            String line = hyponymReader.readLine();
            String[] strArr = line.split(",");
            int v0 = Integer.parseInt(strArr[0]);
            for (int i = 1; i < strArr.length; i++) {
                graph.addEdge(v0, Integer.parseInt(strArr[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (TreeSet<String> s : synonyms.values()) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> tss = new TreeSet<String>();
        for (TreeSet<String> s : synonyms.values()) {
            tss.addAll(s);
        }
        return tss;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> sysnetIds = new TreeSet<Integer>();
        for (Integer i : synonyms.keySet()) {
            TreeSet<String> s = synonyms.get(i);
            if (s.contains(word)) {
                sysnetIds.add(i);
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(graph, sysnetIds);
        Set<String> returnSet = new TreeSet<String>();
        for (Integer i : descendants) {
            returnSet.addAll(synonyms.get(i));
        }
        return returnSet;
    }
    
}
