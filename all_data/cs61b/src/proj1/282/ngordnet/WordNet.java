package ngordnet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private ArrayList<HashSet<String>> synset;
    private Digraph hypnonym;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        In in1 = new In(synsetFilename);
        synset = new ArrayList<HashSet<String>>();
        while (in1.hasNextLine()) {
            HashSet<String> currentSet = new HashSet<String>();
            String line = in1.readLine();
            String[] betweenCommas = line.split(",");
            String synsetGroup = betweenCommas[1];
            String[] betweenSpaces = synsetGroup.split(" ");
            for (String str : betweenSpaces) {
                currentSet.add(str);
            }
            synset.add(currentSet);
        }
        
        In in2 = new In(hyponymFilename);
        hypnonym = new Digraph(synset.size());
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] betweenCommas = line.split(",");
            int vertex = Integer.parseInt(betweenCommas[0]);
            for (int i = 1; i < betweenCommas.length; i++) {
                hypnonym.addEdge(vertex, Integer.parseInt(betweenCommas[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun)  {
        Set<String> wholeSet = nouns();
        if (wholeSet.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns()  {
        Set<String> wholeSet = new HashSet<String>();
        for (HashSet<String> hashSet: synset) {
            for (String str : hashSet) {
                wholeSet.add(str);
            }
        }
        return wholeSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word)  {
        Set<Integer> indeces = new HashSet<Integer>();
        for (int i = 0; i < synset.size(); i++) {
            Set<String> hypnonyms = synset.get(i);
            if (hypnonyms.contains(word)) {
                indeces.add(i);
            }
        }
        
        Set<String> allHypnonyms = new HashSet<String>();
        Set<Integer> descendants = GraphHelper.descendants(hypnonym, indeces);
        for (int descendant : descendants) {
            Set<String> currentSynset = synset.get(descendant);
            for (String str : currentSynset) {
                allHypnonyms.add(str);
            }
        }

        return allHypnonyms;
    }
}
