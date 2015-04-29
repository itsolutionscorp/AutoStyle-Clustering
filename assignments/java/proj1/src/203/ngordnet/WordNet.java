package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class WordNet {
    private Map<Integer, ArrayList<String>> wordMap;
    private Map<ArrayList<String>, Integer> invertedMap;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        int wordCount = 0;
        wordMap = new HashMap<Integer, ArrayList<String>>();
        while (synsetIn.hasNextLine()) {
            String s = synsetIn.readLine();
            String[] sList = s.split(",");
            int id = Integer.parseInt(sList[0]);
            String[] syms = sList[1].split(" ");
            ArrayList<String> synsetList = new ArrayList<String>();
            for (String word: syms) {
                synsetList.add(word);
            }
            wordMap.put(id, synsetList);
            wordCount++;
        }
        g = new Digraph(wordCount);
        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            String[] lineList = line.split(",");
            int parentNym = Integer.parseInt(lineList[0]);
            for (int i = 1; i < lineList.length; i++) {
                g.addEdge(parentNym, Integer.parseInt(lineList[i]));
            }
        }
        invertedMap = invert(wordMap);
    }
    
    private static <K, V> Map<V, K> invert(Map<K, V> map) {
        Map<V, K> resultMap = new HashMap<V, K>();
        int i = 0;
        for (K newVal: map.keySet()) {
            if (resultMap.containsKey(map.get(newVal))) { 
                ((ArrayList<String>) map.get(newVal)).add("ruijing" + i);
                i += 1;
            }
            resultMap.put(map.get(newVal), newVal);
        }
        return resultMap;
    }

    public boolean isNoun(String noun) {
        Collection<ArrayList<String>> wordList = wordMap.values();
        for (ArrayList<String> words: wordList) {
            if (words.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> hyponyms(String word) {
        Set<String> resultHyms = new TreeSet<String>();
        int sysId;
        Set<Integer> wantedKey = new TreeSet<Integer>();
        for (ArrayList<String> sList: invertedMap.keySet()) {
            if (sList.contains(word)) {
                sysId = invertedMap.get(sList);
                wantedKey.add(sysId);
            }
        }
        Set<Integer> allHyms = GraphHelper.descendants(g, wantedKey);
        for (Integer i: allHyms) {
            for (String s: wordMap.get(i)) {
                // This if statement expression was gotten from 
                // http://stackoverflow.com/questions/18590901/check-if-a-string-
                // contains-numbers-java
                if (!s.matches(".*\\d+.*")) {
                    resultHyms.add(s);
                } else if (!s.contains("ruijing")) {
                    resultHyms.add(s);
                }
            }
        }
        return resultHyms;
    }

    public Set<String> nouns() {
        Set<String> resultNouns = new TreeSet<String>();
        Set<Integer> keyList = wordMap.keySet();
        for (Integer key: keyList) {
            for (String s: wordMap.get(key)) {
                if (!s.matches(".*\\d+.*")) {
                    resultNouns.add(s);
                } else if (!s.contains("ruijing")) {
                    resultNouns.add(s);
                }
            }
        }
        return resultNouns;
    }
}
