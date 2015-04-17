package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In; 
import java.util.HashMap;
import java.util.Set; 
import java.util.Arrays;
import java.util.HashSet;
/** Class that demonstrates basic WordNet functionality.
 *  @author Raj Agrawal 
 */
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In readSyn; 
    private In readHyp; 
    private Digraph graph;
    private HashMap<Integer, String[]> dictWords;
    private int numVertices;
    private Set<String> nounSet;    

    public WordNet(String synsetFilename, String hyponymFilename) {
        nounSet = new HashSet<String>();
        dictWords = new HashMap<Integer, String[]>();
        //Read in files
        In readSynDic = new In(synsetFilename);
        readSyn = new In(synsetFilename);
        readHyp = new In(hyponymFilename);
        //Create directed graph
        numVertices = 0; 
        while (readSyn.hasNextLine()) {
            readSyn.readLine();
            numVertices += 1; 
        }
        graph = new Digraph(numVertices);
        while (readHyp.hasNextLine()) {
            String line = readHyp.readLine();
            String[] strList = line.split(",");
            int root = Integer.parseInt(strList[0]);
            for (int i = 1; i < strList.length; i += 1) {
                graph.addEdge(root, Integer.parseInt(strList[i]));
            }
        }
        //Create dictionary containing all synets with the corresponding number as 
        //the key and value a string list consisting of its synets 
        for (int i = 0; i < numVertices; i += 1) {
            String newline = readSynDic.readLine();
            String[] allInfo = newline.split(",");
            String synsetUnsplit = allInfo[1];
            String[] synsetSplitted = synsetUnsplit.split(" ");
            dictWords.put(i, synsetSplitted);  //Just put the synset names inside
            for (String noun: dictWords.get(i)) {
                nounSet.add(noun);
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
        Set<Integer> synIDs = new HashSet<Integer>(); 
        Set<String> hypDecendants = new HashSet<String>();
        //Check each synet in dictionary and see if that synet contains the word
        for (int key: dictWords.keySet()) {
            String[] value = dictWords.get(key); 
            if (Arrays.asList(value).contains(word)) { 
                synIDs.add(key);
            }
        } 
        Set<Integer> indices = GraphHelper.descendants(graph, synIDs);
        for (int i: indices) {
            //Add each word in syset to the hyponyms set
            for (String wordDecend: dictWords.get(i)) {
                hypDecendants.add(wordDecend);
            }
        }
        return hypDecendants; 
    }
}
