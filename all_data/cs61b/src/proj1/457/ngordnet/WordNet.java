package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;

public class WordNet {
    private int entries = 0; 
    private Digraph dg; 
    private Map<Integer, String> idTable; 
    private TreeMap<String, Set<Integer>> wordTable;
    private HashSet<Integer> ids; 
    private Set<String> ret; 
    private Set<Integer> input; 
    private String[] splitter; 
    private Set<Integer> temporary; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        In readSyn = new In(synsetFilename); //->treemap
        In readHyp = new In(hyponymFilename); //->digraph
    
        idTable = new TreeMap<Integer, String>(); 
        wordTable = new TreeMap<String, Set<Integer>>();

        while (readSyn.hasNextLine()) {
            String currenttext = readSyn.readLine(); 
            String[] splittext = currenttext.split(","); 
            Integer synid = Integer.parseInt(splittext[0]); 

            String temp = splittext[1]; 
            String[] synsets = temp.split(" "); 
            for (String word: synsets) {
                if (wordTable.containsKey(word)) {
                    wordTable.get(word).add(synid);
                } else {
                    ids = new HashSet<Integer>();
                    ids.add(synid);
                    wordTable.put(word, ids);
                } 
                entries += 1;
            }
            idTable.put(synid, temp);
        }

        dg = new Digraph(entries); 
        while (readHyp.hasNextLine()) {
            String hypLine = readHyp.readLine(); 
            String[] hypNo = hypLine.split(","); 
            int firstHyp = Integer.parseInt(hypNo[0]); 
            for (int i = 1; i < hypNo.length; i++) {
                dg.addEdge(firstHyp, Integer.parseInt(hypNo[i])); 
            }
        }
    }


    public Set<String> hyponyms(String word) {
        //Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
        ret = new HashSet<String>();
        input = wordTable.get(word);
        temporary = GraphHelper.descendants(dg, input);
        for (Integer i : temporary) {
            splitter = idTable.get(i).split(" ");
            for (String x: splitter) {
                ret.add(x);
            }       
        }
        return ret;
    }

    public boolean isNoun(String noun) {
        return wordTable.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordTable.keySet();
    }
}
