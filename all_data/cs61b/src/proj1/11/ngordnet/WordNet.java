package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class WordNet {

    private ArrayList<String[]> synsets;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {

        synsets = buildSynsets(synsetFilename);
        graph = buildHyponyms(hypernymFilename);

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] synset : synsets) {
            for (String word : synset[1].split(" ")) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }

        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> set = new HashSet<String>();
        for (String[] synset : synsets) {
            for (String noun : synset[1].split(" ")) {
                set.add(noun);
            }
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> set = new HashSet<String>();

        set.add(word);
        Set<Integer> ids = new HashSet<Integer>();

        for (String[] synset : synsets) {
            if (synset[1].contains(word)) {
                ids.add(Integer.parseInt(synset[0]));
            }
        }

        Set<Integer> descendants = GraphHelper.descendants(graph, ids);

        for (String[] synset : synsets) {

            if (descendants.contains(Integer.parseInt(synset[0]))) {
                for (String str : synset[1].split(" ")) {
                    set.add(str);
                }
            }
                        
        }

        return set;

    }

    private ArrayList<String[]> buildSynsets(String filename) {
        //from stackoverflow, with modifications by yours truly
        In in  = new In(filename);

        ArrayList<String[]> list = new ArrayList<String[]>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            list.add(parts);
        }
        
        return list;
    }

    private Digraph buildHyponyms(String filename) {
        //from stackoverflow, with modifications by yours truly
        In in  = new In(filename);
        Digraph agraph = new Digraph(synsets.size());

        while (in.hasNextLine()) {
            String line = in.readLine();

            String[] parts = line.split(",", 2);
            int id = Integer.parseInt(parts[0]);
            String[] toConnect = parts[1].split(",");
            for (String toid : toConnect) {
                agraph.addEdge(id, Integer.parseInt(toid.trim()));
            }
        }

        return agraph;
    }
}
