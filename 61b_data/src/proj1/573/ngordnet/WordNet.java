package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.Collection;


public class WordNet {
    private Digraph hyponymdigraph;
    private HashMap<Integer, String[]> synsetmap;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        In d = new In(hyponymFilename);
        ArrayList<String> wholefile = new ArrayList<String>();
        int maxInt = 0;
        String line;
        while (d.hasNextLine()) {
            line = d.readLine();
            String[] rawtokens = line.split(",");
            wholefile.add(line);
            for (int i = 0; i < rawtokens.length; i++) {
                int current = Integer.parseInt(rawtokens[i]);
                if (current > maxInt) {
                    maxInt = current;
                }
            }
        }
        hyponymdigraph = new Digraph(maxInt + 1);
        for (int j = 0; j < wholefile.size(); j++) {
            String[] rtk2 = (wholefile.get(j)).split(",");
            for (int k = 1; k < rtk2.length; k++) {
                hyponymdigraph.addEdge(Integer.parseInt(rtk2[0]), Integer.parseInt(rtk2[k]));
            }
        }

        In c = new In(synsetFilename);
        synsetmap = new HashMap<Integer, String[]>();
        int count = 0;
        String line2;
        while (c.hasNextLine()) {
            line2 = c.readLine();
            String[] rawtokens3 = line2.split(",");
            String[] synsets = (rawtokens3[1]).split(" ");
            synsetmap.put(count, synsets);
            count += 1;
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Collection<String[]> setofnouns = synsetmap.values();
        for (String[] i: setofnouns) {
            for (int j = 0; j < i.length; j++) {
                if (i[j].contains(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

/* Returns the set of all nouns. */
    public Set<String> nouns() {

        Collection<String[]> collectionofnouns = synsetmap.values();
        Set<String> setofnouns = new HashSet<String>(); 

        for (String[] i: collectionofnouns) {
            for (int j = 0; j < i.length; j++) {
                setofnouns.add(i[j]);
            } 
        }
        return setofnouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDs = new HashSet<Integer>();
        Set<Integer> vertexofhyponyms;
        Set<String> hyponymsset = new HashSet<String>();

        for (int i = 0; i < (synsetmap.keySet()).size(); i++) {
            if (Arrays.asList(synsetmap.get(i)).contains(word)) {
                synsetIDs.add(i);
            }
        }
            
        vertexofhyponyms = GraphHelper.descendants(hyponymdigraph, synsetIDs);
        for (Integer i: vertexofhyponyms) {
            for (int j = 0; j < (synsetmap.get(i)).length; j++) {
                hyponymsset.add((synsetmap.get(i))[j]);
            }
        }
            
        return hyponymsset;
    }

}
