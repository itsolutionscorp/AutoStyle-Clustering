package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
public class WordNet {
    private HashMap<Integer, String> mapped;
    private HashMap<Integer, String[]> connections;
    private In sfile;
    private In hfile;
    private String val;
    private String[] track;
    private int count = 0;
    private Digraph graph;
    private String[] splittedNoun;
    private ArrayList<String> nounRecord = new ArrayList<String>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        mapped = new HashMap<Integer, String>();
        connections = new HashMap<Integer, String[]>();
        sfile = new In(synsetFilename);
        hfile = new In(hyponymFilename);
        while (sfile.hasNextLine()) {
            val = sfile.readLine();
            track = val.split(","); // cite stack overflow for ex of split
            mapped.put(Integer.parseInt(track[0]), track[1]);
            splittedNoun = track[1].split(" ");
            connections.put(Integer.parseInt(track[0]), splittedNoun);
            for (int i = 0; i < splittedNoun.length; i++) {
                nounRecord.add(splittedNoun[i]);
            }
            count += 1;
        }
        graph = new Digraph(count);
        while (hfile.hasNextLine()) {
            val = hfile.readLine();
            track = val.split(",");
            for (int i = 1; i < track.length; i++) {
                graph.addEdge(Integer.parseInt(track[0]), Integer.parseInt(track[i]));
            }
        }
    }

        /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounRecord.contains(noun)) {
            return true;
        }
        return false;
    }

        /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        nouns.addAll(nounRecord);
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
            * WORD. If WORD belongs to multiple synsets, return all hyponyms of
            * all of these synsets. See http://goo.gl/EGLoys for an example.
            * Do not include hyponyms of synonyms.
            */
    public Set<String> hyponyms(String word) {
        Set<Integer> indices = new HashSet<Integer>();
        Set<Integer> temp = new HashSet<Integer>();
        Set<String> hyponyms = new HashSet<String>();
        String[] vals;
        boolean contains = false;
        Iterator checker = connections.entrySet().iterator(); // stack overflow for iteration
        // for hyponyms
        for (Integer i : connections.keySet()) {
            for (int j = 0; j < connections.get(i).length; j++) {
                if (connections.get(i)[j].equals(word)) {
                    indices.add(i);
                    temp.add(i);
                }
            }
        }
        for (Integer i: temp) {
            hyponymHelper(i, indices);
        }
        for (Integer i : indices) {
            for (Integer j : graph.adj(i)) {
                for (String k : connections.get(j)) {
                    hyponyms.add(k);
                }
            }
        }
        // for synonyms
        while (checker.hasNext()) {
            Map.Entry pair = (Map.Entry) checker.next();
            vals = (String[]) pair.getValue();
            for (int i = 0; i < vals.length; i++) {
                if (vals[i].equals(word)) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                for (int i = 0; i < vals.length; i++) {
                    hyponyms.add(vals[i]);
                }
            }
            contains = false;
        }
        // just in case there's no synset for this word
        hyponyms.add(word);
        return hyponyms;
    }

    private void hyponymHelper(Integer i, Set<Integer> indices) {
    // find connected indices
        for (Integer j : graph.adj(i)) {
            indices.add(j);
            hyponymHelper(j, indices);
        }
    }
}

