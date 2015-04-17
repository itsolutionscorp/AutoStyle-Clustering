package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private Digraph synsetMap;
    private HashMap<String, String[]> hyponymParts;
    private HashSet<String> hyponymVals;
    private HashMap<String, String[]> wordIndex;
    private HashSet<String> totalWords;


    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        totalWords = new HashSet<String>();
        hyponymVals = new HashSet<String>();
        hyponymParts = new HashMap<String, String[]>();
        wordIndex =  new HashMap<String, String[]>();
        In scanner = new In(synsetFilename);
        In hypScanner = new In(hyponymFilename);
        String line = hypScanner.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            int i = 1;
            String[] keyVals = new String[parts.length - 1];
            while (i < parts.length) {
                keyVals[i - 1] = parts[i];
                i += 1;
            }
            for (String key : hyponymParts.keySet()) {
                if (key.equals(parts[0])) {
                    String[] oldvals = hyponymParts.get(key);
                    String[] addedvals = keyVals;
                    int numoldvals = oldvals.length;
                    keyVals = new String[numoldvals + keyVals.length];
                    int index = 0;
                    for (String oldval : oldvals) {
                        keyVals[index] = oldval;
                        index += 1;
                    }
                    for (String addedval: addedvals) {
                        keyVals[index] = addedval;
                    }
                }
            }
            hyponymParts.put(parts[0], keyVals);
            for (String vertex : parts) {
                hyponymVals.add(vertex);
            }
            line = hypScanner.readLine();
        }
        int helper = hyponymVals.size();
        String wordline = scanner.readLine();
        while (wordline != null) {
            String[] splitWords = wordline.split(",");
            String[] allwords = splitWords[1].split(" ");
            if (allwords.length > 0) {
                for (String word : allwords) {
                    totalWords.add(word);
                }
            }
            wordIndex.put(splitWords[0], allwords);
            wordline = scanner.readLine();
        }
        synsetMap = new Digraph(hyponymVals.size());
        for (String key : hyponymParts.keySet()) {
            for (String val : hyponymParts.get(key)) {
                synsetMap.addEdge(Integer.parseInt(key), Integer.parseInt(val));
            }
        }
    }


    public boolean isNoun(java.lang.String noun) {
        return totalWords.contains(noun);
    }

    public java.util.Set<java.lang.String> nouns() {
        HashSet<String> returnSet =  new HashSet<String>();
        for (String word : totalWords) {
            returnSet.add(word);
        }
        return returnSet;
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        HashSet<Integer> descendants = new HashSet<Integer>();
        HashSet<String> synonyms = new HashSet<String>();
        HashSet<String> numInstances = new HashSet<String>();
        HashSet<String> returnSet = new HashSet<String>();
        for (String key : wordIndex.keySet()) {
            String[] vals = wordIndex.get(key);
            for (String val : vals) {
                if (word.equals(val)) {
                    int intVal = Integer.parseInt(key);
                    descendants.add(intVal);
                    numInstances.add(key);
                }   
            }
        }
        for (String keyVal : numInstances) {
            String[] vals = wordIndex.get(keyVal);
            for (String synonym : vals) {
                returnSet.add(synonym);
            }
        }
        for (int child : GraphHelper.descendants(synsetMap, descendants)) {
            descendants.add(child);
        }
        for (int child : descendants) {
            for (String key: wordIndex.keySet()) {
                if (Integer.parseInt(key) == child) {
                    String[] vals = wordIndex.get(key);
                    for (String val : vals) {
                        returnSet.add(val);
                    }
                }
            }   
        }
        return returnSet;
    }

}
