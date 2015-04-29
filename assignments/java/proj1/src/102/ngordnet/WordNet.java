package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In synInput;
    private In hypoInput;
    private Map<Integer, Set<String>> synSetDict = new HashMap<Integer, Set<String>>();
    private Map<Integer, Set<Integer>> hyponymsDict = new HashMap<Integer, Set<Integer>>();
    private final int bsInt = 10000000;
    public WordNet(String synsetFilename, String hyponymFilename) {
        synInput = new In(synsetFilename);
        hypoInput = new In(hyponymFilename);
        if (!(synInput.exists() && hypoInput.exists())) {
            throw new IllegalArgumentException("File not found or not valid");
        }
        while (synInput.hasNextLine()) {
            String synLine = synInput.readLine();
            int endString1 = 1;
            while (!(synLine.substring(endString1, endString1 + 1).equals(","))) {
                endString1 += 1;
            }
            String synLineFirst = synLine.substring(0, endString1);
            int synNum = Integer.parseInt(synLineFirst);
            int startString2 = endString1 + 1;
            int endString2 = startString2 + 1;
            while (!(synLine.substring(endString2, endString2 + 1).equals(","))) {
                endString2 += 1;
            }
            String synWords = synLine.substring(startString2, endString2);
            Set<String> synWordsSet = new HashSet<String>();
            int j = 0;
            for (int i = 0; i < synWords.length(); i += 1) {
                if (synWords.substring(i, i + 1).equals(" ")) {
                    synWordsSet.add(synWords.substring(j, i));
                    j = i + 1; 
                } else if ((i + 1) == synWords.length()) {
                    synWordsSet.add(synWords.substring(j, i + 1));
                    j = i + 1; 
                }
            }
            synSetDict.put(synNum, synWordsSet);
        }
        while (hypoInput.hasNextLine()) {
            String hypoLine = hypoInput.readLine();
            int j = 0;
            int hypoNum = 0;
            for (int i = 0; i < hypoLine.length(); i += 1) {
                if (hypoLine.substring(i, i + 1).equals(",")) {
                    hypoNum = Integer.parseInt(hypoLine.substring(j, i));
                    if (hyponymsDict.containsKey(hypoNum)) {
                        hypoNum += bsInt;
                    }
                    j = i + 1;
                    break;
                }
            }
            Set<Integer> hypoNumsSet = new HashSet<Integer>();
            int k = j;
            for (int i = j; i < hypoLine.length(); i += 1) {
                if (hypoLine.substring(i, i + 1).equals(",")) {
                    hypoNumsSet.add(Integer.parseInt(hypoLine.substring(k, i)));
                    k = i + 1;
                } else if ((i + 1) == hypoLine.length()) {
                    hypoNumsSet.add(Integer.parseInt(hypoLine.substring(k, i + 1)));
                    k = i + 1;
                }
            }
            hyponymsDict.put(hypoNum, hypoNumsSet);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> keys = synSetDict.keySet();
        for (int a: keys) {
            if (synSetDict.get(a).contains(noun)) {
                return true;
            }
        }
        return false;
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>(); 
        Set<Integer> keys = synSetDict.keySet();
        for (int a: keys) {
            Iterator<String> generator3 = synSetDict.get(a).iterator();
            while (generator3.hasNext()) {
                String nextWord = generator3.next();
                allNouns.add(nextWord);
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new HashSet<String>();
        allHyponyms.add(word);
        Set<Integer> hypokeys = hyponymsDict.keySet();
        Set<Integer> synkeys = synSetDict.keySet();
        Set<String> synonyms = new HashSet<String>();
        Set<Integer> lowerhypoNums = new HashSet<Integer>();
        Set<Integer> wordIDs = new HashSet<Integer>();
        for (int a: synkeys) {
            if (synSetDict.get(a).contains(word)) {
                wordIDs.add(a);
                for (String b: synSetDict.get(a)) {
                    synonyms.add(b);
                }
            }
        }
        Iterator<String> generator1 = synonyms.iterator();
        while (generator1.hasNext()) {
            String x = generator1.next();
            allHyponyms.add(x);
        }
        for (int a: hypokeys) {
            if (wordIDs.contains(a) || wordIDs.contains(a - bsInt)) {
                for (int x : hyponymsDict.get(a)) {
                    lowerhypoNums.add(x);
                }
            }
        }
        for (int a : lowerhypoNums) {
            Iterator<String> generator2 = synSetDict.get(a).iterator();
            while (generator2.hasNext()) {
                String y = generator2.next();
                allHyponyms.add(y);
            }
            if (hypokeys.contains(a)) {
                for (String s : hyponymsHelper(a)) {
                    allHyponyms.add(s);
                }
            }
            if (hypokeys.contains(a + bsInt)) {
                for (String s : hyponymsHelper(a + bsInt)) {
                    allHyponyms.add(s);
                }
            }
        }
        return allHyponyms;
    }
    private Set<String> hyponymsHelper(int hyponum) {
        Set<String> allHyponyms = new HashSet<String>();
        Set<Integer> hypokeys = hyponymsDict.keySet();
        Set<Integer> synkeys = synSetDict.keySet();
        Set<Integer> lowerhypoNums = hyponymsDict.get(hyponum);
        for (int a: lowerhypoNums) {
            Iterator<String> generator2 = synSetDict.get(a).iterator();
            while (generator2.hasNext()) {
                String y = generator2.next();
                allHyponyms.add(y);
            }
            if (hypokeys.contains(a)) {
                for (String s : hyponymsHelper(a)) {
                    allHyponyms.add(s);
                }
            }
            if (hypokeys.contains(a + bsInt)) {
                for (String s : hyponymsHelper(a + bsInt)) {
                    allHyponyms.add(s);
                }
            }
        }
        return allHyponyms;
    }
}
