package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;

public class WordNet {
    private TreeMap<Integer, TreeMap<String, String>> synsetMap;
    private TreeMap<Integer, TreeSet<Integer>> hyponymMap;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFilereader = new In(synsetFilename);
        In hyponymFilereader = new In(hyponymFilename);

        String[] synsetArray = synsetFilereader.readAllLines();
        String[] synset, synonyms;
        TreeMap<String, String> singlesynset;
        synsetMap = new TreeMap<Integer, TreeMap<String, String>>();
        for (int i = 0; i < synsetArray.length; i++) {
            synset = synsetArray[i].split(",");
            singlesynset = new TreeMap<String, String>();
            synonyms = synset[1].split(" ");
            for (int nouns = 0; nouns < synonyms.length; nouns++) {
                singlesynset.put(synonyms[nouns], synset[2]);
            }

            synsetMap.put(Integer.parseInt(synset[0]), singlesynset);
        }

        Integer parsed;
        String[] hyponymSet;
        TreeSet<Integer> hyponyms;
        String[] hyponymArray = hyponymFilereader.readAllLines();
        hyponymMap = new TreeMap<Integer, TreeSet<Integer>>();
        for (int i = 0; i < hyponymArray.length; i++) {
            hyponymSet = hyponymArray[i].split(",");
            hyponyms = new TreeSet<Integer>();
            for (int hyponymCount = 1; hyponymCount < hyponymSet.length; hyponymCount++) {
                hyponyms.add(Integer.parseInt(hyponymSet[hyponymCount]));
            }
            parsed = Integer.parseInt(hyponymSet[0]);
            if (hyponymMap.containsKey(parsed)) {
                hyponymMap.get(parsed).addAll(hyponyms);
            } else {
                hyponymMap.put(parsed, hyponyms);
            }
        }
    }

    public boolean isNoun(String noun) {
        Iterator<TreeMap<String, String>> nounCheck = synsetMap.values().iterator();
        while (nounCheck.hasNext()) {
            if (nounCheck.next().keySet().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> nounCollection = new TreeSet<String>();
        Iterator<TreeMap<String, String>> nounCheck = synsetMap.values().iterator();
        while (nounCheck.hasNext()) {
            nounCollection.addAll(nounCheck.next().keySet());
        }
        return nounCollection;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<Integer> synonymIndexes = new TreeSet<Integer>();
        Integer synonymIndex; /*marks indexes where word is found*/
        TreeSet<String> hyponymCollection = new TreeSet<String>();
        Iterator<Integer> nounCheck = synsetMap.keySet().iterator();
        while (nounCheck.hasNext()) { /*adding synonyms*/
            synonymIndex = nounCheck.next();
            if (synsetMap.get(synonymIndex).keySet().contains(word)) {
                synonymIndexes.add(synonymIndex);
                hyponymCollection.addAll(synsetMap.get(synonymIndex).keySet());
            }
        }

        Iterator<Integer> myHyponyms = synonymIndexes.iterator();
        while (myHyponyms.hasNext()) {
            hyponymCollection.addAll(hyponymDeep(myHyponyms.next()));
        }
        return hyponymCollection;
    }

    private Set<String> hyponymDeep(Integer index) {
        Set<String> nestedHyponyms = new TreeSet<String>();
        TreeSet<Integer> hiddenHyponyms = hyponymMap.get(index);
        if (hiddenHyponyms != null) {
            nestedHyponyms.addAll(synsetMap.get(index).keySet());
            Iterator<Integer> hyponymIterator = hiddenHyponyms.iterator();
            while (hyponymIterator.hasNext()) {
                nestedHyponyms.addAll(hyponymDeep(hyponymIterator.next()));
            } 
        } else {
            return synsetMap.get(index).keySet();
        }
        return nestedHyponyms;
    }
}
