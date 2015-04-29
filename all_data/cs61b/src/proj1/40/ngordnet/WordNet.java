package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {
    /** Create a synsets map and a hyponyms map. */
    private HashSet<String> nouns = new HashSet<String>();
    private HashMap<String, HashSet<String>> synonyms = new HashMap<String, HashSet<String>>();
    private HashMap<String, HashSet<Integer>> subgroups = new HashMap<String, HashSet<Integer>>();
    private HashMap<Integer, String> synsets = new HashMap<Integer, String>();
    private HashMap<String, Integer> rsynsets = new HashMap<String, Integer>();
    private Digraph g;

    /******* 
      For every entry in the synsets, the first is the id, corresponding to that synset.
      Each word in the synset is a hyponym of the other words in the set

      For every entry in the hyponyms, the first is the id of the synset
      Each id thereafter represents are the ids of the synset's direct hyponyms.
    */

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Load the files one-by-one. */
        In synsetsFile = new In(synsetFilename);
        In hyponymsFile = new In(hyponymFilename);
        String entry;

        while (synsetsFile.hasNextLine()) {
            entry = synsetsFile.readLine();
            String[] split = entry.split(",");

            Integer id = Integer.parseInt(split[0]);
            String names = split[1];

            String[] splice = names.split(" ");
            if (splice.length > 1) {
                // we must add a subgroup
                HashSet<Integer> groupIDs;
                for (int i = 0; i < splice.length; i++) {
                    groupIDs = new HashSet<Integer>();
                    try {
                        groupIDs.addAll(subgroups.get(splice[i]));
                    } catch (NullPointerException e) { /* Ignore NULLPOINTEREXCEPTION */ }
                    groupIDs.add(id);
                    subgroups.put(splice[i], groupIDs);
                }
            }

            for (int i = 0; i < splice.length; i++) {
                HashSet<String> running = new HashSet<String>();

                for (int j = 0; j < splice.length; j++) { 
                    running.add(splice[j]);
                }

                try {
                    running.addAll(synonyms.get(splice[i]));
                } catch (NullPointerException e) { /* Ignore NULLPOINTEREXCEPTION */ }

                synonyms.put(splice[i], running);

            }

            for (String name : splice) { 
                nouns.add(name);
            }

            synsets.put(id, names);
            if (!rsynsets.containsKey(names)) {
                rsynsets.put(names, id);
            }
        }

        g = new Digraph(synsets.size());

        while (hyponymsFile.hasNextLine()) {
            entry = hyponymsFile.readLine();
            String[] split = entry.split(",");
            Integer id = Integer.parseInt(split[0]);

            for (int i = 1; i < split.length; i++) { 
                Integer subid = Integer.parseInt(split[i]);
                g.addEdge(id, subid);
            }

            String s = synsets.get(id);
            if (rsynsets.containsKey(s)) {
                for (int i = 1; i < split.length; i++) {
                    Integer value = Integer.parseInt(split[i]);
                    g.addEdge(rsynsets.get(s), value);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> conversion = new HashSet<String>();
        if (rsynsets.containsKey(word) || subgroups.containsKey(word)) {
            Set<Integer> wordID = new HashSet<Integer>();
            Integer wordhasID = rsynsets.get(word);
            if (wordhasID != null) {
                // word DOES have unique ID
                wordID.add(wordhasID);
            }


            HashSet<Integer> wordHasSubgroups = subgroups.get(word);
            if (wordHasSubgroups != null) {
                // word IS part of a subgroup
                for (Integer i : wordHasSubgroups) {
                    wordID.add(i);
                }
            }

            Set<Integer> result = GraphHelper.descendants(g, wordID);

            for (Integer i : result) {
                String block = synsets.get(i);
                for (String s : block.split(" ")) {
                    conversion.add(s);
                }
            }
        }
        try {
            conversion.addAll(synonyms.get(word));
        } catch (NullPointerException e) { /* Ignore NULLPOINTEREXCEPTION */ }
        return conversion;
    }
}
