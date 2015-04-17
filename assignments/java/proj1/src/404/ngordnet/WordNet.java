package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /**Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String> idToSynsetsMap = new HashMap<Integer, String>();
    private Set<Integer> ids = new HashSet<Integer>();
    private Set<String> nouns = new HashSet<String>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetsText = new In(synsetFilename);
        In hyponymsText = new In(hyponymFilename);

        // -------------------------Make a map of synID's mapped to synsets---------------------//
        while (synsetsText.hasNextLine()) {
            String txtSLine = synsetsText.readLine();

            String[] synL = txtSLine.split(",");
            int synID = Integer.parseInt(synL[0]);

            // --------Add ID to Set of ids-----------//
            ids.add(synID);
            // ---------------------------------------//

            idToSynsetsMap.put(synID, synL[1]);
        }

        // ----------------------------Construct Digraph---------------------------------------//
        g = new Digraph(idToSynsetsMap.size());

                        //-------- Matching hyperID to hypoIDs------------ // 

        while (hyponymsText.hasNextLine()) {
            String txtHLine = hyponymsText.readLine();
            String[] hypL = txtHLine.split(",");

            Integer[] hypoID = new Integer[hypL.length - 1];
            for (int index = 1; index < hypL.length; index++) {
                hypoID[index - 1] = Integer.parseInt(hypL[index]);
            }
            
            for (int item : hypoID) {
                g.addEdge(Integer.parseInt(hypL[0]), item);
            }
        }


        // ---------------------------Make a set of Nouns-------------------------------------//
        for (int key : idToSynsetsMap.keySet()) {
            String[] nounsTokens = idToSynsetsMap.get(key).split(" ");
            for (String n : nounsTokens) {
                nouns.add(n);
            } 
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }


    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

     // Returns the set of all hyponyms of WORD as well as all synonyms of
     // * WORD. If 'WORD' belongs to multiple synsets, return all hyponyms of
     // * all of these synsets. See http://goo.gl/EGLoys for an example.
     // * Do not include hyponyms of synonyms.
     
      
    public Set<String> hyponyms(String word) {

        Set<Integer> setID = new HashSet<Integer>();

        Set<Integer> idsToWords = new HashSet<Integer>();

        Set<String> hyposAndsynons = new HashSet<String>();
        
        for (int k : idToSynsetsMap.keySet()) {
            String[] hypoWords = idToSynsetsMap.get(k).split(" ");
            for (String w : hypoWords) {
                if (word.equals(w)) {
                    setID.add(k);
                }
            }
        }
        idsToWords = GraphHelper.descendants(g , setID);
        
        for (int i : idsToWords) {
            for (int q : idToSynsetsMap.keySet()) {
                if (i == q) {
                    String[] sHypos = idToSynsetsMap.get(i).split(" ");
                    for (String l : sHypos) {
                        hyposAndsynons.add(l);
                    }
                }
            }
        }
        return hyposAndsynons;
    }
}
