package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

//Julie
public class WordNet {
    
    private Map<Integer, HashSet<String>> sysnsetmap;
    private Digraph diagraph;



    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetsfile = new In(synsetFilename);
        In hyponymsfile = new In(hyponymFilename);
        sysnsetmap = makesynsetsmap(synsetsfile);
        diagraph = new Digraph(nouns().size());
        while (!hyponymsfile.isEmpty()) {
            String[] line = hyponymsfile.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                diagraph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            } 
        }
    }

    private Map<Integer, HashSet<String>> makesynsetsmap(In m) {
        Map<Integer, HashSet<String>> returnmap = new HashMap<Integer, HashSet<String>>();
        while (!m.isEmpty()) {
            String[] line = m.readLine().split(",");
            Integer key = Integer.parseInt(line[0]);
            String valueline = line[1];
            HashSet<String> valueset = new HashSet<String>();
            if (valueline.contains(" ")) {
                String[] values = valueline.split(" ");                
                for (int i = 0; i < values.length; i++) {
                    valueset.add(values[i]);
                }
            } else {
                valueset.add(valueline);    
            }
            returnmap.put(key, valueset);
        }
        return returnmap;
    }
        
    public boolean isNoun(String noun) {
        for (HashSet<String> values : sysnsetmap.values()) {
            for (String value : values) {
                if (noun.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> m = new HashSet<String>();
        for (HashSet<String> values : sysnsetmap.values()) {
            Iterator<String> valueite = values.iterator();
            while (valueite.hasNext()) {
                m.add(valueite.next());
            }
        }
        return m;
    }

    
    public Set<String> hyponyms(String word) {
        Set<Integer> numset = new HashSet<Integer>();
        Set<String> returnset = new HashSet<String>();
        Set<Integer> keyset = sysnsetmap.keySet();
        for (Integer key : keyset) {
            if (sysnsetmap.get(key).contains(word)) {
                numset.add(key);
            }
        }
        Set<Integer> wordsid = GraphHelper.descendants(diagraph, numset);
        for (Integer key : wordsid) {
            HashSet<String> wordset = sysnsetmap.get(key);
            returnset.addAll(wordset);
        }
        return returnset;
    }

}


