package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Set;


public class WordNet {

    private Map<Integer , String[]> synsets;
    private Digraph wordnet;

    public WordNet(String synsetFilenameR, String hyponymFilenameR) {
        In synsetFilename = new In(synsetFilenameR);
        In hyponymFilename = new In(hyponymFilenameR);
        synsets = new HashMap<Integer , String[]>();
        while (synsetFilename.hasNextLine()) {
            String tempLine = synsetFilename.readLine();
            String[] tempSplitted = tempLine.split(",");
            String[] temp2Splitted = tempSplitted[1].split(" ");
            synsets.put(Integer.parseInt(tempSplitted[0]) , temp2Splitted);
        }
        
        wordnet = new Digraph(synsets.size());
        while (hyponymFilename.hasNextLine()) {
            String temp2line = hyponymFilename.readLine();
            String[] temp3split = temp2line.split(",");
            for (int i = 1; i < temp3split.length; i++) {
                wordnet.addEdge(Integer.parseInt(temp3split[0]) , Integer.parseInt(temp3split[i]));
            }
        }
    }

    public boolean isNoun(String word) {
        for (String[] value : synsets.values()) {
            for (int i = 0; i < value.length; i++) {
                if (value[i].equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    //nouns returns every single noun as a set of strings.
    public Set<String> nouns() {
        Set<String> tempSet = new TreeSet<String>();
        for (String[] value : synsets.values()) {
            for (int i = 0; i < value.length; i++) {
                tempSet.add(value[i]);
            }
        }
        return tempSet;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> tempSet = new TreeSet<Integer>();
        for (Integer key : synsets.keySet()) {
            for (int i = 0; i < synsets.get(key).length; i++) {
                if (synsets.get(key)[i].equals(word)) {
                    tempSet.add(key);
                }
            }
        }
        Set<Integer> tempSet2 = GraphHelper.descendants(wordnet, tempSet);
        Set<String> tempSet3 = new TreeSet<String>();
        for (Integer id : tempSet2) {
            for (int j = 0; j < synsets.get(id).length; j++) {
                tempSet3.add(synsets.get(id)[j]);
            }
        }
        return tempSet3;
    }
}
