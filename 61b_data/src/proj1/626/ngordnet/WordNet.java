package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private TreeMap<Integer, HashSet<String>> synsetMap;
    private Digraph wordnet;

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Read in files.
        In synset = new In(synsetFilename);
        In hypnonym = new In(hyponymFilename);
        
        /*  Create the TreeMap which has id values as keys and synset as 
            a hashset the value the keys point to. */
        synsetMap = new TreeMap<Integer, HashSet<String>>();
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] list = line.split(",");
            int key = Integer.parseInt(list[0]);
            String[] strings = list[1].split(" ");
            HashSet<String> set = new HashSet<String>();
            for (String s: strings) {
                set.add(s);
            } 
            synsetMap.put(key, set);
        }

        /*  Creates a digraph out of all the id numbers. Then add edges which
            connects related vertices. */
        wordnet = new Digraph(synsetMap.size());
        while (hypnonym.hasNextLine()) {
            String line = hypnonym.readLine();
            String[] list = line.split(",");
            int id = Integer.parseInt(list[0]);
            Integer[] hyponymNumbers = new Integer[list.length - 1];
            for (int i = 1; i < list.length; i++) {
                int dh = Integer.parseInt(list[i]);
                wordnet.addEdge(id, dh);
            }
        }
    }

    // Returns the set of all hyponyms of word as well as all synonyms of word.
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponyms = new HashSet<String>();
        for (int i: synsetMap.keySet()) {
            HashSet<String> list = synsetMap.get(i);
            for (String s : list) {
                if (s.equals(word)) {
                    hyponyms.addAll(synsetMap.get(i));
                    HashSet<Integer> toGet = new HashSet<Integer>();
                    toGet.add(i);
                    Set<Integer> descendantList = GraphHelper.descendants(wordnet, toGet);
                    Iterator descendantListItr = descendantList.iterator();
                    while (descendantListItr.hasNext()) {
                        int key = Integer.parseInt(descendantListItr.next().toString());
                        hyponyms.addAll(synsetMap.get(key));
                    }
                }
            }
        }
        return hyponyms;
    }

    // Checks if input string is in the WordNet, in which case it would return true.
    public boolean isNoun(String noun) {
        for (int i : synsetMap.keySet()) {
            for (String s : synsetMap.get(i)) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns the set of all nouns in the WordNet.
    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        for (int k : synsetMap.keySet()) {
            HashSet<String> list = synsetMap.get(k);
            for (String s : list) {
                nounSet.add(s);
            }
        }
        return nounSet;
    }
}
