package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet; 
import java.util.HashMap; 
import java.util.Set;
import java.util.TreeSet;


public class WordNet {
    private HashMap<Integer, HashSet<String>> data;
    private HashMap<String, HashSet<Integer>> wordKey;
    private Digraph dGraph; 

    
// Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    public WordNet(String synsetFilename, String hyponymFilename) {
        data = new HashMap<Integer, HashSet<String>>();
        wordKey = new HashMap<String, HashSet<Integer>>(); 
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);

        String[] parSyn;
        int key;
        String[] words;
        String strDef;
        HashSet<String> arrWords;
        HashSet<Integer> locations;

        while (!synFile.isEmpty()) {
            parSyn = synFile.readLine().split(",");
            key = Integer.parseInt(parSyn[0]);
            words = parSyn[1].split(" ");

            arrWords = new HashSet<String>();
            for (int jj = 0; jj < words.length; jj += 1) {
                arrWords.add(words[jj]);
            }
            data.put(key, arrWords);

            // put word as Key in wordKey with value of "key"
            for (int jj = 0; jj < words.length; jj += 1) {
                if (wordKey.containsKey(words[jj])) {
                    locations = wordKey.get(words[jj]);
                    locations.add(key);
                    wordKey.put(words[jj], locations);
                } else {
                    locations = new HashSet<Integer>();
                    locations.add(key);
                    wordKey.put(words[jj], locations);
                }
            }
        }

        dGraph = new Digraph(data.size());
        String[] parHyp;

        while (!hypFile.isEmpty()) {
            parHyp = hypFile.readLine().split(",");

            for (int jj = 1; jj < parHyp.length; jj += 1) {
                dGraph.addEdge(Integer.parseInt(parHyp[0]), Integer.parseInt(parHyp[jj]));
            }
        }
    }



// Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
    public Set<String> hyponyms(String word) {
        Set<Integer> desID = new TreeSet<Integer>();
        Set<String> desWord = new TreeSet<String>();
        HashSet<Integer> iUnit;
        if (this.isNoun(word)) {
            iUnit = wordKey.get(word);
            desID.addAll(wordKey.get(word));
            desID = GraphHelper.descendants(dGraph, desID);
            if (desID != null) { 
                for (Integer iD : desID) {
                    desWord.addAll(data.get(iD));
                }
            }
            return desWord;
        } else {
            return null;
        }


    }

// Basically is this entry in the Set
    public boolean isNoun(String noun) {
        return wordKey.containsKey(noun);
    }

//

//
    public Set<String> nouns() {
        Set<String> n = new TreeSet<String>();
        n = this.wordKey.keySet();
        return n;
    }
}
