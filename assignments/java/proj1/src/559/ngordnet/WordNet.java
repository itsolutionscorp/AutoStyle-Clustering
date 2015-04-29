package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    
    private Map<Integer, Set<String>> wnetis = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> wnetsi = new HashMap<String, Set<Integer>>();
    private Digraph g;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        int count = 0;
        while (synsets.hasNextLine()) {
            String line = synsets.readLine();
            String[] linesplit = line.split(",");
            String[] wordsplit = linesplit[1].split(" ");
            Set<String> synset = new HashSet<String>();
            Set<Integer> wordIndexes = new HashSet<Integer>();
            for (int i = 0; i < wordsplit.length; i += 1) {
                synset.add(wordsplit[i]);
                if (wnetsi.containsKey(wordsplit[i])) {
                    wordIndexes = wnetsi.get(wordsplit[i]);
                    wordIndexes.add(Integer.parseInt(linesplit[0]));
                    wnetsi.put(wordsplit[i], wordIndexes);
                } else {
                    wordIndexes.add(Integer.parseInt(linesplit[0]));
                    wnetsi.put(wordsplit[i], wordIndexes);
                }
            }
            wnetis.put(Integer.parseInt(linesplit[0]), synset);
            count += 1;
        }
        
        
        
        String[] allVerts = hyponyms.readAllLines();
        g = new Digraph(count);
        for (int i = 0; i < allVerts.length; i += 1) {
            String[] linesplit = allVerts[i].split(",");
            for (int j = 0; j < linesplit.length; j += 1) {
                g.addEdge(Integer.parseInt(linesplit[0]), Integer.parseInt(linesplit[j]));
            }
        }
        
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wnetsi.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wnetsi.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        Set<Integer> wordIndexes = new HashSet<Integer>();
        wordIndexes = wnetsi.get(word);
        Set<Integer> toReturnInt = GraphHelper.descendants(g, wordIndexes);
        Object[] returnArray = toReturnInt.toArray();
        for (int i = 0; i < returnArray.length; i += 1) {
            Set<String> stringReturn = wnetis.get(returnArray[i]);
            Iterator<String> strIter = stringReturn.iterator();
            while (strIter.hasNext()) {
                toReturn.add(strIter.next());
            }
        }
        return toReturn;
    }
}
