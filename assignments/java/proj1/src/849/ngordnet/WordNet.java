package ngordnet;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, HashSet<String>> idSynset  =  new HashMap<Integer,  HashSet<String>>();
    private HashSet<String> nounSet  =  new HashSet<String>();
    private Digraph G;
    private String[] lineArr;
    private String line = "";
    public WordNet(String synsetFilename,  String hyponymFilename) {
        In synReader  =  new In(synsetFilename);
        In hypReader  =  new In(hyponymFilename);
        while (!synReader.isEmpty()) {
            line  =  synReader.readLine();
            lineArr  =  line.split(",");
            String[] nouns  =  lineArr[1].split(" ");
            HashSet<String> currSynset  =  new HashSet<String>(Arrays.asList(nouns));
            idSynset.put(new Integer(Integer.parseInt(lineArr[0])),  currSynset);
            nounSet.addAll(currSynset);
        }

        G = new Digraph(Integer.parseInt(lineArr[0]) + 1);
        
        while (!hypReader.isEmpty()) {
            line = hypReader.readLine();
            lineArr = line.split(",");
            int from = Integer.parseInt(lineArr[0]);
            for (int i = 1;  i < lineArr.length;  i++) {
                G.addEdge(from, Integer.parseInt(lineArr[i]));
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets,  return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        HashSet<String> hypsym  =  new HashSet<String>();
        HashSet<Integer> synsetIds  =  new HashSet<Integer>();
        HashSet<String> currSynset  =  new HashSet<String>();
        hypsym.add(word);
        for (Integer key: idSynset.keySet()) {
            currSynset =  idSynset.get(key);
            if (currSynset.contains(word)) {
                hypsym.addAll(currSynset);
                synsetIds.add(key);
            }
        }
        Set<Integer> hypids =  GraphHelper.descendants(G,  synsetIds);
        for (Integer k: hypids) {
            currSynset = idSynset.get(k);
            hypsym.addAll(currSynset);
        }
        
        return hypsym;
    }
    
}



