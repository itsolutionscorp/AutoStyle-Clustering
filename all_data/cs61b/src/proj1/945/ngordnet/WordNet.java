package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

// some help from Ian and Ruijing 
public class WordNet {
    private HashMap<Integer, ArrayList<String>> listForward;
    private HashMap<ArrayList<String>, Integer> listReverse;

    private ArrayList<String> readSynset;
    private ArrayList<String> readHypo;

    private int vertices;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        listForward = new HashMap<Integer, ArrayList<String>>();
        listReverse = new HashMap<ArrayList<String>, Integer>();
        readSynset = new ArrayList<String>();
        readHypo = new ArrayList<String>();
        while (synset.hasNextLine()) {
            String syn = synset.readLine();
            readSynset.add(syn);
        }
        while (hyponym.hasNextLine()) {
            String hypo = hyponym.readLine();
            readHypo.add(hypo);
        }
        for (String item : readSynset) {
            String[] rawTokens = item.split(",");
            String[] listHypo = rawTokens[1].split(" ");
            ArrayList<String> arrayList = new ArrayList<String>(
                    Arrays.asList(listHypo));
            listForward.put(Integer.parseInt(rawTokens[0]), arrayList);
            listReverse.put(arrayList, Integer.parseInt(rawTokens[0]));
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> set = new TreeSet<String>();
        for (ArrayList<String> item : this.listReverse.keySet()) {
            for (String splitString : item) {
                set.add(splitString);
            }
        }
        return set;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        TreeSet<String> set = new TreeSet<String>();
        Set<Integer> setIdsHyper = new TreeSet<Integer>();
        Set<Integer> setIdsHypo = new TreeSet<Integer>();
        Digraph findHypo = new Digraph(this.listForward.size());
        for (String item : readHypo) {
            String[] rawTokens = item.split(",");
            for (int i = 1; i < rawTokens.length; i++) {
                findHypo.addEdge(Integer.parseInt(rawTokens[0]),
                        Integer.parseInt(rawTokens[i]));
            }
        }

        for (ArrayList<String> item : listReverse.keySet()) {
            if (item.contains(word)) {
                setIdsHyper.add(listReverse.get(item));
            }
        }

        setIdsHypo = GraphHelper.descendants(findHypo, setIdsHyper);

        for (int id : setIdsHypo) {
            ArrayList<String> desList = listForward.get(id);
            for (String item : desList) {
                set.add(item);
            }
        }

        return set;
    }
}
