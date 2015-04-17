package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private Digraph digraph;
    private Map<String, String> synflipped = new HashMap<String, String>();
    private Map<String, String> syn = new HashMap<String, String>();
    private Set<String> nounset = new HashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetfile = new In(synsetFilename);
        In hypsetfile = new In(hyponymFilename);

        while (synsetfile.hasNextLine()) {
            String curr;
            String[] currb, currab;
            curr = synsetfile.readLine();
            currb = curr.split(",");
            if (synflipped.containsKey(currb[1])) {
                synflipped.put(currb[1], currb[0] + "," + synflipped.get(currb[1]));
            } else {
                synflipped.put(currb[1], currb[0]);
            }
            syn.put(currb[0], currb[1]);
            currab = currb[1].split(" ");
            for (String things : currab) {
                nounset.add(things);
            }
        }

        digraph = new Digraph(nounset.size());

        while (hypsetfile.hasNextLine()) {
            String current;
            String[] currentb;
            current = hypsetfile.readLine();
            currentb = current.split(",");
            for (String thingsb : currentb) {
                if (!thingsb.equals(currentb[0])) {
                    digraph.addEdge(Integer.parseInt(currentb[0]),
                            Integer.parseInt(thingsb));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounset.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounset;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        String id;
        String tmp3;
        String[] tmp4, idset1;
        Set<Integer> idset = new HashSet();
        Set<Integer> resultset = new HashSet();
        Set<String> result = new HashSet();
        Set<String> keyset;

        keyset = synflipped.keySet();
        for (String s : keyset) {
            String u = s;
            String[] tmp = s.split(" ");
            for (String t : tmp) {
                if (t.equals(word)) {
                    id = synflipped.get(u);
                    idset1 = id.split(",");
                    for (String thingss: idset1) {
                        idset.add(Integer.parseInt(thingss));
                    }
                }
            }
        }

        resultset = GraphHelper.descendants(digraph, idset);

        for (Integer y : resultset) {
            tmp3 = syn.get(Integer.toString(y));
            tmp4 = tmp3.split(" ");
            for (String w : tmp4) {
                result.add(w);
            }
        }
        return result;
    }
}
