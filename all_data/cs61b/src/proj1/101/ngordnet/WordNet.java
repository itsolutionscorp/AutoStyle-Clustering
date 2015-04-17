/**
 *  @author Mitchell Oleson
 *  Read input files and store them in ADTs allowing rapid queries.
 */

package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    // Synset Data Structures
    // Integer to String of words
    private LinkedHashMap<Integer, String> synDefNet = 
        new LinkedHashMap<Integer, String>();
    // Integer to def
    private LinkedHashMap<Integer, String> synDefDef = 
        new LinkedHashMap<Integer, String>();
    // Word to Integer
    private LinkedHashMap<String, LinkedHashSet<Integer>> synDefStr = 
        new LinkedHashMap<String, LinkedHashSet<Integer>>();    

    // Hyponyms Data Structures
    // Int to Int[]
    private LinkedHashMap<Integer, LinkedHashSet<Integer>> hypNet = 
        new LinkedHashMap<Integer, LinkedHashSet<Integer>>();

    // Data Structure
    private Digraph wordNetDigraph;
    private int numLines;

    /** Create a WordNet using files from synsetLoc and hyponymLoc */
    public WordNet(String synsetLoc, String hyponymLoc) {
        In synonymsFile = new In(synsetLoc);
        In hyponymsFile = new In(hyponymLoc);
        while (synonymsFile.hasNextLine() || hyponymsFile.hasNextLine()) {
            if (synonymsFile.hasNextLine()) {
                synsReadLine(synonymsFile);
            }
            if (hyponymsFile.hasNextLine()) {
                hpysReadLine(hyponymsFile);
            }
            numLines++;
        }
        createStructure();
        synonymsFile.close();
        hyponymsFile.close();
    }

    /** Read in files for wordnet */
    private void synsReadLine(In synonymsFile) {    
        String[] lineSyn = (synonymsFile.readLine()).split(",");
        Integer posSyn = Integer.valueOf(lineSyn[0]);
        synDefNet.put(posSyn, lineSyn[1]);
        synDefDef.put(posSyn, lineSyn[2]);
        String[] hypWords = lineSyn[1].split(" ");
        for (String hypWord : hypWords) {
            if (synDefStr.containsKey(hypWord)) {
                (synDefStr.get(hypWord)).add(posSyn);
            } else {
                LinkedHashSet<Integer> synStart = new LinkedHashSet<Integer>();
                synStart.add(posSyn);
                synDefStr.put(hypWord, synStart);
            }
        }
    }

    private void hpysReadLine(In hyponymsFile) {            
        String[] lineHyp = (hyponymsFile.readLine()).split(",");
        LinkedHashSet<Integer> hypoList = new LinkedHashSet<Integer>();
        Integer pos = 0;
        for (int x = 0; x < lineHyp.length; x++) {
            if (x == 0) {
                pos = Integer.valueOf(lineHyp[x]);
            } else {
                hypoList.add(Integer.valueOf(lineHyp[x]));
            }
        }
        if (!hypNet.containsKey(pos)) {
            hypNet.put(pos, hypoList);
        } else {
            LinkedHashSet<Integer> addHere = hypNet.get(pos);
            for (Integer k : hypoList) {
                addHere.add(k);
            }
        }
    }

    private void createStructure() {
        wordNetDigraph = new Digraph(numLines);
        for (Integer w : hypNet.keySet()) {
            for (Integer k1 : hypNet.get(w)) {
                wordNetDigraph.addEdge(w, k1);
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synDefStr.containsKey(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return synDefStr.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        LinkedHashSet<String> gottenStrings = new LinkedHashSet<String>();
        for (Integer x : GraphHelper.descendants(wordNetDigraph, synDefStr.get(word))) {
            for (String hyp : (synDefNet.get(x)).split(" ")) {
                gottenStrings.add(hyp);
            }
        }
        return gottenStrings;
    }
}
