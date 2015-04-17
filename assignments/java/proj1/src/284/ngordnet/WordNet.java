package ngordnet; 
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private In syn;
    private In hyp;
    private Digraph d; 
    private HashMap<String, HashSet<Integer>> wordmap; 
    private HashMap<Integer, String[]> synmap; 
    private HashMap<Integer, String> defmap; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        syn = new In(synsetFilename);
        hyp = new In(hyponymFilename);
        synmap = new HashMap<Integer, String[]>();
        defmap = new HashMap<Integer, String>();
        wordmap = new HashMap<String, HashSet<Integer>>();
     
        for (String i = syn.readLine(); i != null; i = syn.readLine()) {
            String[] store = i.split(",");
            String[] words = store[1].split(" ");
            Integer num = Integer.valueOf(store[0]); 
            
            synmap.put(num, words);
            
            defmap.put(num, store[2]);
                
            for (int j = 0; j < words.length; j += 1) {
                if (wordmap.containsKey(words[j])) {
                    HashSet<Integer> intset = wordmap.get(words[j]);
                    intset.add(num);
                } else {
                    HashSet<Integer> newintset = new HashSet<Integer>();
                    newintset.add(num);
                    wordmap.put(words[j], newintset);
                }
            }
        }

        d = new Digraph(wordmap.size());
        for (String n = hyp.readLine(); n != null; n = hyp.readLine()) {
            String[] save = n.split(",");
            Integer number = Integer.valueOf(save[0]);
            for (int k = 1; k < save.length; k += 1) {
                Integer z = Integer.valueOf(save[k]);
                d.addEdge(number, z);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        Set<Integer> holder = new TreeSet<Integer>();
        for (Integer y: wordmap.get(word)) {
            holder.add(y);
        }
        for (Integer x: GraphHelper.descendants(d, holder)) {
            for (String a: synmap.get(x)) {
                result.add(a);
            }
        }
        return result;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        return wordmap.keySet();
    }   
}
