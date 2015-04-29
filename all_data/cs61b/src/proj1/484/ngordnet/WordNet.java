package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;


public class WordNet {
    private Map<Integer, HashSet<String>> entry = new HashMap<Integer, HashSet<String>>();
    private Map<String, HashSet<Integer>> query = new HashMap<String, HashSet<Integer>>();
    private Map<Integer, HashSet<Integer>> column = new HashMap<Integer, HashSet<Integer>>();
    private HashSet<String> nouns = new HashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String sysnetsFile, String hyponymsFile) {
        In sysnets = new In(sysnetsFile);
        In hyponyms = new In(hyponymsFile);
        while (sysnets.hasNextLine()) {
            String[] temp1 = sysnets.readLine().split(",");
            Integer index = Integer.parseInt(temp1[0]);
            HashSet<String> synonyms = new HashSet<String>(Arrays.asList(temp1[1].split("\\s+")));
            for (String word:synonyms) {
                HashSet<Integer> qv = query.get(word);
                if (qv != null) {
                    qv.add(index);
                } else {
                    qv = new HashSet<Integer>();
                    qv.add(index);
                    query.put(word, qv);
                }

            }
            nouns.addAll(synonyms);            
            entry.put(index, synonyms);
        }
        while (hyponyms.hasNextLine()) {
            String[] temp2 = hyponyms.readLine().split(",");
            Integer[] hypoArray = new Integer[temp2.length - 1];
            Integer selfIndex = Integer.parseInt(temp2[0]);
            for (int i = 1; i < temp2.length; i++) {
                hypoArray[i - 1] = Integer.parseInt(temp2[i]);
            }
            HashSet<Integer> hypoSet = new HashSet<Integer>(Arrays.asList(hypoArray));
            HashSet<Integer> cv = column.get(selfIndex);
            if (cv != null) {
                cv.addAll(hypoSet);
            } else {
                column.put(selfIndex, hypoSet);
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
        if (!query.containsKey(word)) {
            throw new IllegalArgumentException();
        }
        HashSet<String> temp = new HashSet<String>();
        for (Integer index : query.get(word)) {
            addSubset(index, temp);
        }
        return temp;
        
    }


    private void addSubset(Integer index, Set<String> results) {
        if (!column.containsKey(index)) {
            results.addAll(entry.get(index));
            return;
        }
        results.addAll(entry.get(index));
        for (Integer subsetIndex : column.get(index)) {
            addSubset(subsetIndex, results);
        }
    }
}

