package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private Map<String, Set<Integer>> nounToId;
    private Map<Integer, Set<String>> idToNoun;
    private int setNumber;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nounToId = new TreeMap<String, Set<Integer>>();
        idToNoun = new TreeMap<Integer, Set<String>>();
        readSynset(synsetFilename);
        readHyponym(hyponymFilename);
    }

    /** read in the synset file into the Map structure, should always read synset before
     *  read in hyponym.
     */
    private void readSynset(String synsetFilename) {    
        In synsetFile = new In(synsetFilename);
        String[] synsetLines = synsetFile.readAllLines();
        
        setNumber = synsetLines.length;
        for (int i = 0; i < synsetLines.length; i++) {
            String[] eachline = synsetLines[i].split(",");
            int synsetId = Integer.parseInt(eachline[0]);
            String[] eachSet = eachline[1].split(" "); 
            for (int j = 0; j < eachSet.length; j++) {
                String noun = eachSet[j];
                if (nounToId.containsKey(noun)) {
                    nounToId.get(noun).add(synsetId);
                } else {
                    Set<Integer> newSet = new TreeSet<Integer>();
                    newSet.add(synsetId);
                    nounToId.put(noun, newSet);
                }

                if (idToNoun.containsKey(synsetId)) {
                    idToNoun.get(synsetId).add(noun);
                } else {
                    Set<String> newSet = new TreeSet<String>();
                    newSet.add(noun);
                    idToNoun.put(synsetId, newSet);
                }
            }   
        }   
    }
        /** read in the hyponym file into the digraph structure.
         */
    private void readHyponym(String hyponymFilename) {
        In hyponymFile = new In(hyponymFilename);
        String[] hyponymLines = hyponymFile.readAllLines();
        g = new Digraph(setNumber);
        for (int i = 0; i < hyponymLines.length; i++) {
            String[] eachline = hyponymLines[i].split(",");
            int synsetId = Integer.parseInt(eachline[0]);
            for (int j = 1; j < eachline.length; j++) {
                g.addEdge(synsetId, Integer.parseInt(eachline[j]));
            }
        }      
    }

    private void printData() {
        System.out.println(g);
        System.out.println(setNumber);
        System.out.println();
        System.out.println(nounToId);
        System.out.println();
        System.out.println(idToNoun);
        System.out.println();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounToId.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounToId.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> idSet = nounToId.get(word);
            Set<Integer> hyponymIdSet = GraphHelper.descendants(g, idSet); 
            Set<String> hyponymSet = new TreeSet<String>();
            for (Integer i : hyponymIdSet) {
                hyponymSet.addAll(idToNoun.get(i));
            }
            return hyponymSet;
        }
        return null;            
    }
}
