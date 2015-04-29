package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class WordNet {
    private Set<String> synsets = new HashSet<String>();
    private Map<String, Set<String>> sMap = new HashMap<String, Set<String>>();
    private Set<String> sMapValues = new HashSet<String>();
    private Map<String, Set<String>> invertsMap = new HashMap<String, Set<String>>();
    private Set<String> invertsMapIndices = new HashSet<String>();
    private Set<String> hyponyms = new HashSet<String>();
    private Map<String, Set<String>> hMap = new HashMap<String, Set<String>>();
    private Set<String> nounList = new HashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        In nounFile = new In("./wordnet/synsets.txt");
        while (synsetFile.hasNextLine()) {
            sMapValues = new HashSet<String>();
            String[] sLine = synsetFile.readLine().split(",");
            String[] words = sLine[1].split(" ");
            if (sMap.keySet().contains(sLine[0])) {
                for (String w : words) {
                    sMap.get(sLine[0]).add(w);
                    sMap.put(sLine[0], sMap.get(sLine[0]));
                }
            } else {
                for (String w : words) {
                    sMapValues.add(w);
                    sMap.put(sLine[0], sMapValues);
                }
            }
            for (String word : words) {
                invertsMapIndices = new HashSet<String>();
                if (invertsMap.keySet().contains(word)) {
                    invertsMap.get(word).add(sLine[0]);
                    invertsMap.put(word, invertsMap.get(word));
                } else {
                    invertsMapIndices.add(sLine[0]);
                    invertsMap.put(word, invertsMapIndices);
                }
            }
            for (String w : words) {
                synsets.add(w);
            }
        }
        while (hyponymFile.hasNextLine()) {
            String[] hLine = hyponymFile.readLine().split(",");
            Set<String> hValues = new HashSet<String>();
            for (int i = 1; i < hLine.length; i += 1) {
                hValues.add(hLine[i]);
            }
            if (hMap.keySet().contains(hLine[0])) {
                hMap.get(hLine[0]).addAll(hValues);
                hMap.put(hLine[0], hMap.get(hLine[0]));
            } else {
                hMap.put(hLine[0], hValues);
            }
        }
        while (nounFile.hasNextLine()) {
            String[] nLine = nounFile.readLine().split(",");
            String[] words = nLine[1].split(" ");
            for (String w : words) {
                nounList.add(w);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (synsets.contains(noun) && nounList.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Iterator<String> iter = synsets.iterator();
        Set<String> nouns = new HashSet<String>();
        while (iter.hasNext()) {
            String a = iter.next();
            String b = a.toLowerCase();
            if (nounList.contains(a) || nounList.contains(b)) {
                nouns.add(a);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        for (String key : hMap.keySet()) {
            Set<String> hMapIndices = new HashSet<String>();
            for (String item: hMap.get(key)) {
                hMapIndices.add(item);
            }
        }
        hyponyms = new HashSet<String>();
        hyponyms.add(word);
        if (invertsMap != null) {
            Set<String> indices = invertsMap.get(word);
            for (String index : indices) {
                for (String synonyms : sMap.get(index)) {
                    hyponyms.add(synonyms);
                }
            }
            if (hMap != null) {
                Set<String> wordHyponyms = new HashSet<String>();
                for (String index : indices) {
                    if (hMap.get(index) != null) {
                        wordHyponyms = recurse(wordHyponyms, index, hMap);
                    }
                }
                if (wordHyponyms != null) {
                    for (String w : wordHyponyms) {
                        Set<String> hypoSynonyms = sMap.get(w);
                        for (String s : hypoSynonyms) {
                            hyponyms.add(s);
                        }
                    }
                }
            }
        }
        return hyponyms;
    }

    private Set<String> recurse(Set<String> hyp, String index, Map<String, Set<String>> hypMap) {
        if (hypMap.get(index) == null) {
            hyp.add(index);
            return hyp;
        } else {
            hyp.add(index);
            Iterator<String> iter = hypMap.get(index).iterator();
            while (iter.hasNext()) {
                hyp.addAll(recurse(hyp, iter.next(), hypMap));
            }
        }
        return hyp;
    }
}
