package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/** WordNet class
 *  @author Amy Ji
 */

public class WordNet {
    private Map<Integer, Set<String>> synset = new HashMap<Integer, Set<String>>();
    private Map<Integer, Set<Integer>> hyponym = new HashMap<Integer, Set<Integer>>();
    private boolean hasdonehypo = false;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In readersyn = new In(synsetFilename);
        String line;
        while ((line = readersyn.readLine()) != null) {
            //split the line by comma
            String[] def = line.split(",");
            //first element should be the id
            int key = Integer.parseInt(def[0]);
            //second element should be the sunset, may contain many words
            Set<String> val = new HashSet<String>();
            String[] syn = def[1].split("\\s+");
            for (int i = 0; i < syn.length; i++) {
                val.add(syn[i]);
            }
            //put key and val into map
            synset.put(key, val);

        }

        String line2;
        In readerhyp = new In(hyponymFilename);
        while ((line2 = readerhyp.readLine()) != null) {
            //splits line by comma
            String[] ids = line2.split(",");
            //gets the key
            int key2 = Integer.parseInt(ids[0]);
            //gets all hyp ids and put into set
            Set<Integer> val2 = new HashSet<Integer>();
            for (int i = 1; i < ids.length; i++) {
                int vall = Integer.parseInt(ids[i]);
                val2.add(vall);
            }
            //put into map
            if (hyponym.containsKey(key2)) {
                for (int i : val2) {
                    hyponym.get(key2).add(i);
                }
            } else {
                hyponym.put(key2, val2);
            }
        }
    }
    public boolean isNoun(String noun) {
        //String[] ss = noun.split("\\s+");
        return (nouns().contains(noun));
    }
    public Set<String> nouns() {
        Set<String> n = new HashSet<String>();
        for (int k : synset.keySet()) {
            for (String i : synset.get(k)) {
                n.add(i);
            }
        }
        return n;
    }
    public Set<String> hyponyms(String word) {
        Set<Integer> temp;
        Set<String> h = new HashSet<String>();
        for (int a : synset.keySet()) {
            if (synset.get(a).contains(word)) {
                //up to here get all synonyms
                h.addAll(synset.get(a));
                //get the id of set of all hyponyms of the synset
                temp = hyponym.get(a);
                //nullpointer check
                if (temp != null) {
                    for (int s : temp) {
                        //up to here i got the hyponyms
                        for (String p : synset.get(s)) {
                            h.add(p);
                            // get the hyponyms of hyponyms
                            h.addAll(hyponyms(p));
                        }
                    }
                }
            }
        }
        return h;
    }
}
