package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.princeton.cs.introcs.In;

/**
 * An object that stores the WordNet graph in a manner useful for extracting all
 * hyponyms of a word.
 * 
 * @author Administrator
 * 
 */
public class WordNet {
    private Map<Integer, List<String>> synsets;
    private Map<String, List<Integer>> words;
    private Map<Integer, List<Integer>> hyponyms;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     * @param synsetFilename
     * @param hyponymFilename
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new HashMap<String, List<Integer>>();
        synsets = new TreeMap<Integer, List<String>>();

        In synsetIn = new In(synsetFilename);
        String line = "";
        String[] lineSplit;
        while (synsetIn.hasNextLine()) {
            line = synsetIn.readLine();
            lineSplit = line.split(",");
            int id = Integer.parseInt(lineSplit[0]);
            String[] syn = lineSplit[1].split(" ");
            List<String> syns = new ArrayList<String>();
            for (String s : syn) {
                syns.add(s);
                if (words.containsKey(s)) {
                    words.get(s).add(id);
                } else {
                    List<Integer> ids = new ArrayList<Integer>();
                    ids.add(id);
                    words.put(s, ids);
                }
            }
            synsets.put(id, syns);
        }

        hyponyms = new TreeMap<Integer, List<Integer>>();
        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            line = hyponymIn.readLine();
            lineSplit = line.split(",");
            int id = Integer.parseInt(lineSplit[0]);

            List<Integer> hyps;
            if (hyponyms.containsKey(id)) {
                hyps = hyponyms.get(id);
            } else {
                hyps = new ArrayList<Integer>();
            }
            for (int i = 1; i < lineSplit.length; i++) {
                hyps.add(Integer.parseInt(lineSplit[i]));
            }
            hyponyms.put(id, hyps);
        }
    }

    public boolean isNoun(String noun) {
        return words.keySet().contains(noun);
    }

    public Set<String> nouns() {
        return words.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     * 
     * @param word
     * @return
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypons = new HashSet<String>();

        if (isNoun(word)) {
            List<Integer> ids = words.get(word);
            for (int id : ids) {
                hypons.addAll(synsets.get(id));

                Set<Integer> lst = getHypomymsByID(id);
                for (int i : lst) {
                    hypons.addAll(synsets.get(i));
                }
            }
        }
        return hypons;
    }

    private Set<Integer> getHypomymsByID(int id) {
        Set<Integer> hypons = new HashSet<Integer>();
        List<Integer> lst = hyponyms.get(id);
        if (lst != null) {
            hypons.addAll(lst);
            for (int i : lst) {
                hypons.addAll(getHypomymsByID(i));
            }
        } else {
            return hypons;
        }
        return hypons;
    }

    private static void main(String[] args) {
        WordNet wn = new WordNet("./wordnet/synsets.txt",
                "./wordnet/hyponyms.txt");

        //System.out.println("Hypnoyms of animal:");
        //for (String noun : wn.hyponyms("animal")) {
            //System.out.println(noun);
        // .. 
    }
}
