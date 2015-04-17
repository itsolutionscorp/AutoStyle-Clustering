package ngordnet;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    //Got help with the concept for two maps from discussion with Wenhao Liao
    private HashMap<Integer, String[]> synsets;
    private HashMap<String, Set<Integer>> wordIDs;
    private Digraph hyponyms;

    public WordNet(String synsetFileName, String hyponymFilename) {
        In synsetInput = new In(synsetFileName);
        synsets = new HashMap<Integer, String[]>();
        wordIDs = new HashMap<String, Set<Integer>>();
        while (synsetInput.hasNextLine()) {
            String[] temp = synsetInput.readLine().split(",");
            String[] tempWords = (temp[1]).split(" ");
            for (String i : tempWords) {
                if (wordIDs.containsKey(i)) {
                    Set<Integer> tempwordID = wordIDs.get(i);
                    tempwordID.add(Integer.parseInt(temp[0]));
                } else {
                    Set<Integer> wordID = new TreeSet<Integer>();
                    wordID.add(Integer.parseInt(temp[0]));
                    wordIDs.put(i, wordID);
                }
            }
            synsets.put(Integer.parseInt(temp[0]), Arrays.copyOfRange(temp, 1, temp.length));
        }
        In hyponymInput = new In(hyponymFilename);
        hyponyms = new Digraph(synsets.size());
        while (hyponymInput.hasNextLine()) {
            String[] temp2 = hyponymInput.readLine().split(","); 
            for (int i = 1; i < temp2.length; i++) {
                hyponyms.addEdge(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[i]));
            }
        }
    }
    public Set<String> hyponyms(String word) {
        Set<Integer> temp = new TreeSet<Integer>();
        temp = wordIDs.get(word);
        Set<Integer> ids = GraphHelper.descendants(hyponyms, temp);
        Set<String> words = new TreeSet<String>();
        for (int i : ids) {
            String[] synsetWords = synsets.get(i)[0].split(" ");
            for (String s : synsetWords) {
                words.add(s);
            }
        }
        return words;
    }
    public boolean isNoun(String noun) {
        return (wordIDs.get(noun) != null);
    }

    public Set<String> nouns() {
        return wordIDs.keySet();
    }
}
