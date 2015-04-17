package ngordnet;   
import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In; 
import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet; 

public class WordNet {
/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, Set<String>> synMap; 
    private Digraph connections; 

    public WordNet(String synsetFilename, String hyponymFilename) {

        In synIn = new In(synsetFilename); 
        synMap = new HashMap<Integer, Set<String>>(); 

        while (synIn.hasNextLine()) { 

            String newLine = synIn.readLine();
            String[] idSynsetDef = newLine.split(","); 
            String[] wordsInSynset = idSynsetDef[1].split(" ");

            Set<String> words = new HashSet<String>(); 
            for (String word: wordsInSynset) { 
                words.add(word); 
            }
            synMap.put(Integer.parseInt(idSynsetDef[0]), words); 

        }

        int numID = synMap.size(); 
        connections = new Digraph(numID);

        In hypIn = new In(hyponymFilename); 

        while (hypIn.hasNextLine()) { 
            String relations = hypIn.readLine(); 
            String[] idConnections = relations.split(",");
            for (int i = 1; i < idConnections.length; i++) {  
                connections.addEdge(Integer.parseInt(idConnections[0]),
                                Integer.parseInt(idConnections[i])); 
            }
        }

    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer x: synMap.keySet()) { 
            if (synMap.get(x).contains(noun)) { 
                return true; 
            }
        }
        return false; 
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>(); 
        for (Integer x: synMap.keySet()) { 
            allNouns.addAll(synMap.get(x)); 
        }
        return allNouns; 
    }

    /**Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<Integer> synIDs = new HashSet<Integer>(); 
        Set<String> allHypSyn = new HashSet<String>(); 
        for (Integer x: synMap.keySet()) { 
            if (synMap.get(x).contains(word)) { 
                synIDs.add(x); 
                allHypSyn.addAll(synMap.get(x)); 
            }
        }
        Set<Integer> hyponymVertices = GraphHelper.descendants(connections, synIDs); 
        for (Integer x: synMap.keySet()) { 
            if (hyponymVertices.contains(x)) { 
                allHypSyn.addAll(synMap.get(x)); 
            }
        }
        return allHypSyn; 
    }

}
