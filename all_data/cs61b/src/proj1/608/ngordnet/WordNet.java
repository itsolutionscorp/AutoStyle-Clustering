package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.TreeMap;
public class WordNet {
    private TreeMap<Integer, TreeMap<String, TreeSet<String>>> synset
        = new TreeMap<Integer, TreeMap<String, TreeSet<String>>>();
    private TreeMap<Integer, TreeSet<Integer>> hyponym 
        = new TreeMap<Integer, TreeSet<Integer>>();
    private TreeMap<String, TreeSet<Integer>> ID 
        = new TreeMap<String, TreeSet<Integer>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] parts = line.split(",");
            String[] set = parts[1].split(" ");
            Integer id = new Integer(Integer.parseInt(parts[0]));
            TreeMap<String, TreeSet<String>> current = new TreeMap<String, TreeSet<String>>();
            for (int i = 0; i < set.length + 1; i++) {
                TreeSet<String> putt = new TreeSet<String>();
                if (i != set.length) {
                    if (ID.containsKey(set[i])) {
                        ID.get(set[i]).add(id);
                    } else {
                        TreeSet<Integer> insert = new TreeSet<Integer>();
                        insert.add(id);
                        ID.put(set[i], insert);
                    }
                }
                for (int j = 0; j < set.length; j++) {
                    if (i != j) {
                        putt.add(set[j]);
                    }
                }
                if (i == set.length) {
                    current.put("all", putt);
                } else {
                    current.put(set[i], putt);
                }
            }
            synset.put(id, current);
        }
        In hyp = new In(hyponymFilename);
        while (hyp.hasNextLine()) {
            String hline = hyp.readLine();
            String[] hparts = hline.split(",");
            TreeSet<Integer> cur = new TreeSet<Integer>();
            for (int i = 1; i < hparts.length; i++) {
                cur.add(Integer.parseInt(hparts[i]));
                if (hyponym.containsKey(Integer.parseInt(hparts[0]))) {
                    hyponym.get(Integer.parseInt(hparts[0])).addAll(cur);
                } else {
                    hyponym.put(Integer.parseInt(hparts[0]), cur);
                }
            }
        }
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (!ID.containsKey(word)) {
            return null;
        }
        TreeSet<Integer> ids = ID.get(word);
        TreeSet<String> hypos = new TreeSet<String>();
        hypos.add(word);
        for (Integer id: ids) {
            TreeSet<String> synonyms = synset.get(id).get(word);
            hypos.addAll(synonyms);
            hypos.addAll(hypohelper(id));
        }
        return hypos;
    }
    private TreeSet<String> hypohelper(Integer id) {
        TreeSet<String> hypos = new TreeSet<String>();
        if (hyponym.containsKey(id)) {
            for (Integer hypId: hyponym.get(id)) {
                TreeSet<String> hyponyms = synset.get(hypId).get("all");
                hypos.addAll(hyponyms);
                if (hyponym.containsKey(hypId)) {
                    hypos.addAll(hypohelper(hypId));
                }
            }
        }
        return hypos;
    }
    public boolean isNoun(String noun) {
        return ID.containsKey(noun);
    }
    public Set<String> nouns() {
        return ID.keySet();

    }
}
