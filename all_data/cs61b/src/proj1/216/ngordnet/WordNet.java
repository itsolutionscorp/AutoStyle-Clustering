package ngordnet;

import edu.princeton.cs.introcs.In;

import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class WordNet {

    private TreeMap<Integer, String[]> nouns;
    private TreeMap<Integer, ArrayList<Integer>> hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new TreeMap<Integer, String[]>();
        hyponyms = new TreeMap<Integer, ArrayList<Integer>>();

        // Read in synsets file and create TreeMap of <Integer, String[]> pairs.
        In synsetReader = new In(synsetFilename);
        while (synsetReader.hasNextLine()) {
            String line = synsetReader.readLine();
            int start = -1;
            int end = -1;
            for (int i = 0; i < line.length() && end == -1; i += 1) {
                if (line.charAt(i) == ',') {
                    if (start == -1) {
                        start = i;
                    } else {
                        end = i;
                    }
                }
            }
            Integer index = Integer.parseInt(line.substring(0, start));
            String[] arr = line.substring(start + 1, end).split(" ");
            nouns.put(index, arr);
        }

        // Read in hyponyms file and create TreeMap of <Integer, Integer[]> pairs.
        In hyponymReader = new In(hyponymFilename);
        while (hyponymReader.hasNextLine()) {
            String line = hyponymReader.readLine();
            String[] arr = line.split(",");
            Integer index = Integer.parseInt(arr[0]);

            ArrayList<Integer> next;
            ArrayList<Integer> current = hyponyms.get(index);
            if (current == null) {
                next = new ArrayList<Integer>();
                for (int i = 0; i < arr.length - 1; i += 1) {
                    next.add(Integer.parseInt(arr[i + 1]));
                }
                hyponyms.put(index, next);
            } else {
                for (int i = 0; i < arr.length - 1; i += 1) {
                    current.add(Integer.parseInt(arr[i + 1]));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer key : nouns.keySet()) {
            String[] arr = nouns.get(key);
            for (int i = 0; i < arr.length; i += 1) {
                if (arr[i].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> set = new TreeSet<String>();
        for (Integer key : nouns.keySet()) {
            String[] arr = nouns.get(key);
            for (int i = 0; i < arr.length; i += 1) {
                set.add(arr[i]);
            }
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> set = new TreeSet<String>();

        for (Integer key : nouns.keySet()) {
            String[] arr = nouns.get(key);

            boolean match = false;
            for (int i = 0; i < arr.length; i += 1) {
                if (arr[i].equals(word)) {
                    match = true;
                }
            }

            if (match) {
                for (int i = 0; i < arr.length; i += 1) {
                    set.add(arr[i]);
                }
                set.addAll(findChildHyponyms(key));
            }
        }
        return set;
    }

    private Set<String> findChildHyponyms(Integer key) {
        Set<String> set = new TreeSet<String>();
        ArrayList<Integer> indices = hyponyms.get(key);

        if (indices == null) {
            return set;
        }

        for (Integer index : indices) {
            String[] arr = nouns.get(index);
            for (int i = 0; i < arr.length; i += 1) {
                set.add(arr[i]);
            }

            set.addAll(findChildHyponyms(index));
        }

        return set;
    }
}
