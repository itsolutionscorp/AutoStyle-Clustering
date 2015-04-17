package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, String> intToSynset;
    private Map<String, Integer> synsetToInt;
    private Digraph wordNet;
    private Set<String> nouns;


    public WordNet(String synsetFilename, String hyponymFilename) {
        intToSynset = new TreeMap<Integer, String>();
        synsetToInt = new TreeMap<String, Integer>();
        nouns = new HashSet<String>();
        int numWords = 0;
        In synsetList = new In(synsetFilename);
        while (synsetList.hasNextLine()) {
            String newLine = synsetList.readLine();
            String[] synset = newLine.split(",");
            int c = Integer.parseInt(synset[0]);
            String v = synset[1];
            String[] many = v.split(" ");
            if (many.length > 1) {
                for (int i = 0; i < many.length; i += 1) {
                    nouns.add(many[i]);
                }
            } else {
                nouns.add(v);
            }
            intToSynset.put(c, v);
            synsetToInt.put(v, c);
            numWords += 1;
        }
        wordNet = new Digraph(numWords);
        In hyponymList = new In(hyponymFilename);
        while (hyponymList.hasNextLine()) {
            String hVals = hyponymList.readLine();
            String[] hyponym = hVals.split(",");
            int key = Integer.parseInt(hyponym[0]);
            for (int i = 1; i < hyponym.length; i += 1) {
                int val = Integer.parseInt(hyponym[i]);
                wordNet.addEdge(key, val);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        Collection<String> allNouns = intToSynset.values();
        Set<String> toReturn = new HashSet<String>();
        for (String a : allNouns) {
            String[] isitmany = a.split(" ");
            if (isitmany.length == 1) {
                toReturn.add(a);
            } else {
                for (int i = 0; i < isitmany.length; i += 1) {
                    toReturn.add(isitmany[i]);
                }
            }
        }
        return toReturn;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> keys = intToSynset.keySet();
        Set<Integer> hypernyms = new HashSet<Integer>();
        for (Integer key : keys) {
            String s = intToSynset.get(key);
            String[] multiple = s.split(" ");
            if (multiple.length == 1) {
                if (s.equals(word)) {
                    hypernyms.add(key);
                }
            } else {
                for (int k = 0; k < multiple.length; k += 1) {
                    if (word.equals(multiple[k])) {
                        hypernyms.add(key);
                    }
                }
            }
        }
        Set<Integer> hyps = GraphHelper.descendants(wordNet, hypernyms);
        Set<String> hyponyms = new HashSet<String>();
        for (Integer hyp : hyps) {
            String h = intToSynset.get(hyp);
            String[] many = h.split(" ");
            if (many.length > 1) {
                for (int i = 0; i < many.length; i += 1) {
                    hyponyms.add(many[i]);
                }
            } else {
                hyponyms.add(h);
            }
        }
        return hyponyms;
    }
}
