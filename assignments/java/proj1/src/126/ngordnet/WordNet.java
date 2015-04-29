package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private Map<Integer, String[]> words;
    private Digraph g;
    /** Creates a WordNet using files for m SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new HashMap<Integer, String[]>();
        In input = new In(synsetFilename);
        int num = -1;
        while  (input.hasNextLine()) {
            String line = input.readLine();
            String[] eachSet = line.split(",");
            num = Integer.parseInt(eachSet[0]);
            String[] eachWord = eachSet[1].split(" ");
            String[] all = new String[eachWord.length + 1];
            int index = 0;
            for (String temp: eachWord) {
                all[index] = temp;
                index++;
            }
            all[index] = eachSet[2];
            words.put(num, all);
        }
        input = new In(hyponymFilename);
        g = new Digraph(num + 1);
        while (input.hasNextLine()) {
            String line = input.readLine();
            String[] each = line.split(",");
            int keep = Integer.parseInt(each[0]);
            for (int i = 1; i < each.length; i++) {
                int id = Integer.parseInt(each[i]);
                g.addEdge(keep, id);
            }
        }
    }

    /* Returns true if  NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> nums = words.keySet();
        for (int key: nums) {
            int index = 0;
            for (String each: words.get(key)) {
                if (each.equals(noun) && index < words.get(key).length - 1) {
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<Integer> nums = words.keySet();
        Set<String> all = new TreeSet<String>();
        for (int key: nums) {
            int index = 0;
            while  (index < words.get(key).length - 1) {
                all.add(words.get(key)[index]);
                index++;
            }
        }
        return all;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. if  WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for  an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> nums = words.keySet();
        Set<Integer> wordID = new TreeSet<Integer>();
        Set<Integer> synIDs = new TreeSet<Integer>();
        for (int key: nums) {
            for (String temp: words.get(key)) {
                if (temp.equals(word)) {
                    wordID.add(key);
                    if (words.get(key).length != 2) {
                        synIDs.add(key);
                    }
                }
            }
        }
        Set<Integer> hypIDs = GraphHelper.descendants(g, wordID);
        Set<String> hyp = new TreeSet<String>();
        
        for (int key: hypIDs) {
            int index = 0;
            for (String hyponyms: words.get(key)) {
                if (index != words.get(key).length - 1) {
                    hyp.add(words.get(key)[index]);
                }
                index++;
            }
        }

        for (int ids: synIDs) {
            int index = 0;
            for (String synonyms: words.get(ids)) {
                if (index != words.get(ids).length - 1) {
                    hyp.add(words.get(ids)[index]);
                }
                index++;
            }
        }
        return hyp;
    }
}
