package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<String, HashSet<String>> wn;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In s = new In(synsetFilename);
        In h = new In(hyponymFilename);
        HashMap<Integer, String[]> synsets = buildSynsets(s);
        HashMap<String, HashSet<Integer>> reverseSynsets = buildReverseSynsets(synsets, h);
        wn = new HashMap<String, HashSet<String>>();
        for (String word : reverseSynsets.keySet()) {
            HashSet<String> value = new HashSet<String>();
            HashSet<Integer> hyponymGroups = reverseSynsets.get(word);
            for (Integer i : hyponymGroups) {
                String[] hyponyms = synsets.get(i);
                for (int j = 0; j < hyponyms.length; j += 1) {
                    value.add(hyponyms[j]);
                }
            }
            wn.put(word, value);
        }
    }

    private HashMap<Integer, String[]> buildSynsets(In s) {
        HashMap<Integer, String[]> result = new HashMap<Integer, String[]>();
        while (s.hasNextLine()) {
            String[] terms = s.readLine().split(",");
            String[] words = terms[1].split(" ");
            result.put(Integer.parseInt(terms[0]), words);
        }
        return result;
    }

    private HashMap<String, HashSet<Integer>> buildReverseSynsets(
        HashMap<Integer, String[]> synsets, In h) {
        HashMap<String, HashSet<Integer>> result = 
            new HashMap<String, HashSet<Integer>>();
        for (Integer num : synsets.keySet()) {
            String[] words = synsets.get(num);
            for (int i = 0; i < words.length; i += 1) {
                if (!result.containsKey(words[i])) {
                    HashSet<Integer> nums = new HashSet<Integer>();
                    nums.add(num);
                    result.put(words[i], nums);
                } else {
                    result.get(words[i]).add(num);
                }
            }
        }
        HashMap<Integer, HashSet<Integer>> hyponyms = new HashMap<Integer, HashSet<Integer>>();
        while (h.hasNextLine()) {
            String[] terms = h.readLine().split(",");
            int key = Integer.parseInt(terms[0]);
            HashSet<Integer> values = new HashSet<Integer>();
            boolean contain = hyponyms.keySet().contains(key);
            for (int j = 1; j < terms.length; j += 1) {
                if (contain) {
                    hyponyms.get(key).add(Integer.parseInt(terms[j]));
                } else {
                    values.add(Integer.parseInt(terms[j]));
                }
            }
            if (!contain) {
                hyponyms.put(key, values);
            }
            String[] hypers = synsets.get(key);
            for (int i = 0; i < hypers.length; i += 1) {
                for (int j = 1; j < terms.length; j += 1) {
                    result.get(hypers[i]).add(Integer.parseInt(terms[j]));
                }
            }
        }
        result = updateMap(result, hyponyms);
        return result;
    }

    private HashMap<String, HashSet<Integer>> updateMap(
        HashMap<String, HashSet<Integer>> result, 
        HashMap<Integer, HashSet<Integer>> hyponyms) {
        boolean changed = false;
        for (String word : result.keySet()) {
            HashSet<Integer> current = new HashSet<Integer>();
            for (Integer val : result.get(word)) {
                current.add(val);
            }
            for (Integer value : current) {
                if (hyponyms.get(value) != null) {
                    for (Integer subvalue : hyponyms.get(value)) {
                        if (!result.get(word).contains(subvalue)) {
                            result.get(word).add(subvalue);
                            changed = true;
                        }
                    }
                }
            }
        }
        if (changed) {
            return updateMap(result, hyponyms);
        }
        return result;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wn.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wn.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        return wn.get(word);
    }

}
