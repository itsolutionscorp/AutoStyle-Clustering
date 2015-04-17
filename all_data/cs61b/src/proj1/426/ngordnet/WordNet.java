package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

//An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a word.
public class WordNet {

    private HashMap<Integer, String[]> newMap = new HashMap<Integer, String[]>();
    private Digraph digraph;
    private ArrayList<Integer> allHypKeys = new ArrayList<Integer>();


    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        while (synset.hasNextLine()) {
            String nextLine = synset.readLine();
            String[] syn = nextLine.split(",");
            String[] synonyms = syn[1].split(" ");
            newMap.put(Integer.parseInt(syn[0]), synonyms);
            

        }
        digraph = new Digraph(newMap.size());
        while (hyponym.hasNextLine()) {
            String intLine = hyponym.readLine();
            String[] hyp = intLine.split(",");
            for (int i = 1; i < hyp.length; i++) {
                digraph.addEdge(Integer.parseInt(hyp[0]), Integer.parseInt(hyp[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer i: newMap.keySet()) {
            if (newMap.get(i).length > 1) {
                if (Arrays.asList(newMap.get(i)).contains(noun)) {
                    return true;
                }
            }
            if (noun.equals(newMap.get(i)[0])) {
                return true;
            }
        }
        return false;
    }

    //returns the set of nouns
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (Integer i: newMap.keySet()) {
            if (newMap.get(i).length > 1) {
                for (String s : newMap.get(i)) {
                    nounSet.add(s);
                }
            }
            nounSet.add(newMap.get(i)[0]);
        }
        return nounSet;
    }
    private ArrayList<Integer> getHyponymKeys(Integer i) {
        Iterable<Integer> intTemp = digraph.adj(i);
        for (Integer k: intTemp) {
            if (!i.equals(k)) {
                allHypKeys.add(k);
                getHyponymKeys(k);
            }
        }
        return allHypKeys;
    }


    //Returns the set of all hyponyms of WORD. 
    //If WORD belongs to multiple synsets, return all hyponyms for all of these synsets. 
    //A word is considered a hyponym of itself. 
    //If a synset contains multiple words, they are all hyponyms of each other.
    public Set<String> hyponyms(String word) {
        Set<Integer> synonymIndex = new HashSet<Integer>();
        Set<String> hyponym = new HashSet<String>();
        Set<Integer> hypKey = new HashSet<Integer>();
        for (Integer i: newMap.keySet()) {
            if (newMap.get(i).length > 1) {
                for (String s : newMap.get(i)) {
                    if (s.equals(word)) {
                        hypKey.add(i);
                        synonymIndex.add(i);
                    }
                }
            } else {
                if (newMap.get(i)[0].equals(word)) {
                    hypKey.add(i);
                }
            }
        }
        for (Integer w: GraphHelper.descendants(digraph, hypKey)) {
            hypKey.add(w);
        }
        for (Integer j: hypKey) {
            Iterable<Integer> intHyponyms = digraph.adj(j);
            for (Integer k: intHyponyms) {
                if (newMap.get(k).length > 1) {
                    for (String s : newMap.get(k)) {
                        hyponym.add(s);
                    } 
                } else {
                    hyponym.add(newMap.get(k)[0]);
                }
            }
        }
        for (Integer q: synonymIndex) {
            for (String a: newMap.get(q)) {
                hyponym.add(a);
            }
        }
        hyponym.add(word);
        return hyponym;
    }
}
