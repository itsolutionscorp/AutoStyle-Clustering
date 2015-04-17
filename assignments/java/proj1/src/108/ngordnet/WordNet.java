package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> nounSet = new TreeSet<String>();
    private HashMap<String, ArrayList<Integer>> wordIdMap
        = new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, String> idWordMap = new HashMap<Integer, String>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        int lineCounter = 0;
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] rawTokens = line.split(",");
            int id = Integer.parseInt(rawTokens[0]);
            String synset = rawTokens[1];   //...............one or more word in the synset
            String definition = rawTokens[2];

            // synset special case:    e.g.  change alteration modification 
            if (synset.contains(" ")) {
                String[] synsetCom = synset.split(" ");
                for (int i = 0; i < synsetCom.length; i += 1) {
                    nounSet.add(synsetCom[i]);
                    if (wordIdMap.containsKey(synsetCom[i])) {
                        ArrayList<Integer> originalSynsetArray = wordIdMap.get(synsetCom[i]);
                        originalSynsetArray.add(id);
                        wordIdMap.put(synsetCom[i], originalSynsetArray);
                    } else {
                        ArrayList<Integer> synsetArray = new ArrayList<Integer>();
                        synsetArray.add(id);
                        wordIdMap.put(synsetCom[i], synsetArray);
                    }
                }
            } else {
                nounSet.add(synset);
                if (wordIdMap.containsKey(synset)) {
                    ArrayList<Integer> originalSynsetArray = wordIdMap.get(synset);
                    originalSynsetArray.add(id);
                    wordIdMap.put(synset, originalSynsetArray);
                } else {
                    ArrayList<Integer> synsetArray = new ArrayList<Integer>();
                    synsetArray.add(id);
                    wordIdMap.put(synset, synsetArray); 
                }
            }
            idWordMap.put(id, synset);
            lineCounter += 1;
        }

        g = new Digraph(lineCounter);
        while (hyponymFile.hasNextLine()) {
            String newline = hyponymFile.readLine();
            String[] numTokens = newline.split(",");
            int synsetID = Integer.parseInt(numTokens[0]);

            for (int i = 1; i < numTokens.length; i += 1) {
                g.addEdge(synsetID, Integer.parseInt(numTokens[i]));
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
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> idList = wordIdMap.get(word);

        Set<Integer>  idOfWord = new TreeSet<Integer>();
        for (int i = 0; i < idList.size(); i += 1) {
            idOfWord.add(idList.get(i));
        }
        Set<Integer> idSet = GraphHelper.descendants(g, idOfWord);
        Set<String> idStringSet = new TreeSet<String>();

        Iterator<Integer> idSetIterator = idSet.iterator();
        while (idSetIterator.hasNext()) {
            int tempID = idSetIterator.next();
            String synset = idWordMap.get(tempID);
            if (synset.contains(" ")) {
                String[] synsetCom = synset.split(" ");
                for (int i = 0; i < synsetCom.length; i += 1) {
                    idStringSet.add(synsetCom[i]);
                }
            } else {
                idStringSet.add(synset);
            }
        }
        return idStringSet;
    }
}























