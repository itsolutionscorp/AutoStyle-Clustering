package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/** An object that stores the WordNet graph 
 *  in a manner useful for extracting all hyponyms of a word. 
 *  @author: Yue Zheng
 */
public class WordNet {
    private Digraph hyponyms;
    private HashMap<Integer, List<String>> synsets = new HashMap<Integer, List<String>>();
    private int synsetNum = 0;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        try {
            while (!synsetIn.isEmpty()) {
                String[] line = synsetIn.readLine().split(",");
                String[] synsetArray = line[1].split(" ");
                List<String> synsetList = new ArrayList<String>(synsetArray.length);
                
                for (String item : synsetArray) {
                    synsetList.add(item);
                }
                
                synsets.put(Integer.parseInt(line[0]), synsetList);
                synsetNum += 1; 
            }

            hyponyms = new Digraph(synsetNum);

            while (!hyponymIn.isEmpty()) {
                String[] line = hyponymIn.readLine().split(",");
                for (int i = 1; i < line.length; i++) {
                    hyponyms.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
                }
            }

        } catch (NullPointerException e) {
            return;
        }
    }

    public boolean isNoun(String noun) {
        for (int k : synsets.keySet()) {
            if (synsets.get(k).contains(noun)) {
                return true;
            }
        }
        return false;
    }   

    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        
        for (List<String> nounList : synsets.values()) {
            Collection<String> nounCollection = nounList;
            nounSet.addAll(nounCollection);
        }
        
        return nounSet;
    }

    public Set<String> hyponyms(String noun) {
        HashSet<String> hyponymSet = new HashSet<String>();
        for (int key : synsets.keySet()) {
            Collection<String> values = synsets.get(key);
            
            if (values.contains(noun)) {
                hyponymSet.addAll(values);
                    
                HashSet<Integer> hypernym = new HashSet<Integer>();
                hypernym.add(key);
                Set<Integer> descendantKeys = GraphHelper.descendants(hyponyms, hypernym);
                
                for (int hypoKey : descendantKeys) {
                    Collection<String> descendantValues = synsets.get(hypoKey);
                    hyponymSet.addAll(descendantValues);
                }
            }
        }
        return hyponymSet;
    }
}
