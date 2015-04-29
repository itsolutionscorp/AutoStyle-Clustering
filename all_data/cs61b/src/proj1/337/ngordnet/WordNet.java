package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class WordNet {
    private List<Set<String>> wordList;
    private Digraph wordNetDig; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordList = new ArrayList<Set<String>>();
        
        In synsetIn = new In(synsetFilename);
        while (synsetIn.hasNextLine()) {
            String[] splitSynsets = synsetIn.readLine().split(",");
            Set<String> synonyms = new TreeSet<String>();
            String[] splitWords = splitSynsets[1].split(" ");
            for (String word : splitWords) {
                synonyms.add(word);
            }
            wordList.add(synonyms);
        }

        wordNetDig = new Digraph(wordList.size());

        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String[] splitIDs = hyponymIn.readLine().split(",");
            int rootID = Integer.parseInt(splitIDs[0]);
        
            for (int i = 1; i < splitIDs.length; i += 1) {
                wordNetDig.addEdge(rootID, Integer.parseInt(splitIDs[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Set<String> synSet : wordList) {
            if (synSet.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (Set<String> synSet : wordList) {
            allNouns.addAll(synSet);
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allHyps = new TreeSet<String>();
        Set<Integer> roots = new TreeSet<Integer>();
        
        for (Set<String> synSet : wordList) {
            if (synSet.contains(word)) {
                roots.add(wordList.indexOf(synSet));
            }
        }

        // set of integers that correspond to ID's of hyponyms
        Set<Integer> allHypsInt = GraphHelper.descendants(wordNetDig, roots);
        
        for (int id : allHypsInt) {
            allHyps.addAll(wordList.get(id));
        }

        return allHyps;
    }
}
