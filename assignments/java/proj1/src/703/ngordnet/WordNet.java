package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;

public class WordNet {
    private HashMap<Integer, TreeSet<String>> data;
    
    private Digraph access;
    private Set<String> allWords = new TreeSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        data = new HashMap<Integer, TreeSet<String>>();
        int vertexes = 0;
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] temp = line.split(",");
            Integer key = Integer.parseInt(temp[0]);
            String synonyms = temp[1];
            String[] words = synonyms.split(" ");
            TreeSet<String> m = new TreeSet<String>();
            for (int i = 0; i < words.length; i += 1) {
                allWords.add(words[i]);
                m.add(words[i]);
            } 
            data.put(key, m);
        }
        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            String[] temp = line.split(",");
            vertexes += temp.length;
        }
        hyponym = new In(hyponymFilename);
        access = new Digraph(vertexes);
        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            String[] temp = line.split(",");
            for (int i = 1; i < temp.length; i += 1) {
                access.addEdge(Integer.parseInt(temp[0]), Integer.parseInt(temp[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    public Set<String> nouns() {
        return allWords;
    }

    public Set<String> hyponyms(String word) {
        Set<String> returnSet = new TreeSet<String>();
        Set<Integer> keys = data.keySet();
        Set<Integer> keynums = new TreeSet<Integer>();

        for (Integer key : keys) {
            if (data.get(key).contains(word)) {
                for (String y : data.get(key)) {
                    returnSet.add(y);
                }
                keynums.add(key);
            }
        }
        Set<Integer> loopingSet = GraphHelper.descendants(access, keynums);
        for (Integer i : loopingSet) {
            Set<String> words = data.get(i);
            for (String n : words) {
                returnSet.add(n);
            }
        }
        return returnSet;

    }

}

