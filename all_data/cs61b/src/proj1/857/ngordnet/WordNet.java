package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

	private ArrayList<String> vertices;
	private HashMap<Integer, ArrayList<Integer>> hyponyms;
    private HashMap<String, ArrayList<Integer>> synIDs;
    private HashMap<Integer, ArrayList<String>> synSets;
    private int q;
    private ArrayList<String> netNouns;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In fileSynset = new In(synsetFilename);
        In fileHyponym = new In(hyponymFilename);
        hyponyms = new HashMap<Integer, ArrayList<Integer>>();
        vertices = new ArrayList<String>();
        synIDs = new HashMap<String, ArrayList<Integer>>();
        synSets = new HashMap<Integer, ArrayList<String>>();
        netNouns = new ArrayList<String>();
        if (fileSynset.isEmpty() || fileHyponym.isEmpty()) {
            throw new IllegalArgumentException("empty file input");
        }
    	while (!fileHyponym.isEmpty()) {
    		String[] hyponymStrings = fileHyponym.readLine().split(",");
            ArrayList<Integer> hyponymInts = new ArrayList<Integer>();
            ArrayList<Integer> synonyms = new ArrayList<Integer>();
            for (int i = 0; i < hyponymStrings.length; i++) {
                int hypo = Integer.parseInt(hyponymStrings[i]);
                hyponymInts.add(hypo);
            }    
            for (int x = 0; x < hyponymInts.size(); x++) {
                synonyms.add(hyponymInts.get(x));
            }
            hyponyms.put(Integer.parseInt(hyponymStrings[0]), synonyms);
    	}
    	while (!fileSynset.isEmpty()) {
    		String[] synsetFileString = fileSynset.readLine().split(",");
    		vertices.add(synsetFileString[1]); 
            String[] synWord = synsetFileString[1].split(" ");
            ArrayList<String> currentVals = new ArrayList<String>();
            for (int i = 0; i < synWord.length; i++) {
                currentVals.add(synWord[i]);
            }
            Integer id = Integer.parseInt(synsetFileString[0]);
            synSets.put(id, currentVals);
            ArrayList<Integer> idList = new ArrayList<Integer>();
            for (int i = 0; i < synWord.length; i++) {
                if (synIDs.containsKey(synWord[i])) {
                    synIDs.get(synWord[i]).add(id);
                } else {
                    idList.add(id);
                    synIDs.put(synWord[i], idList);
                }
            }
            for (int i = 0; i < currentVals.size(); i++) {
                netNouns.add(currentVals.get(i));
            }
    	}
    	q = vertices.size();
        Digraph digraphD = new Digraph(q);
        if (!hyponyms.isEmpty()) {
            for (int i = 0; i < hyponyms.size(); i++) {
                if (hyponyms.get(i) != null) {
                    ArrayList<Integer> values = hyponyms.get(i);
                    for (int j = 0; j < values.size(); j++) {
                        int w = values.get(j);
                        digraphD.addEdge(i, w);
                    }
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < this.vertices.size(); i++) {
            String currentVert = this.vertices.get(i);
            String[] vertList = currentVert.split(" ");
            for (int j = 0; j < vertList.length; j++) {
                if (vertList[j].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> n = new TreeSet<String>(netNouns);
        return n;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        /* set to return */
        ArrayList<String> hypList = new ArrayList<String>();
        
        /* if word doesnt exist in synFile, throw error */
        if (!this.nouns().contains(word)) {
            throw new IllegalArgumentException("No such word in synset");
        } else {
            /* takes care of synonyms */
            /* list of ids associated with the word */
            ArrayList<Integer> idNums = this.synIDs.get(word);
            /* set of synonyms (to be added to total set later) */
            ArrayList<String> synWords = new ArrayList<String>();
            /* iterate through synIDs of word */
            for (int i = 0; i < idNums.size(); i++) {
                /* get all the synset words of ID i */
                ArrayList<String> s = synSets.get(idNums.get(i));
                for (int j = 0; j < s.size(); j++) {
                    synWords.add(s.get(j));
                }
            }

            hypList.addAll(synWords); 

            /* takes care of hyponyms of word*/
            for (int i = 0; i < idNums.size(); i++) {
                if (hyponyms.get(idNums.get(i)) != null) {
                    ArrayList<Integer> hypIDList = hyponyms.get(idNums.get(i));
                    for (int j = 0; j < hypIDList.size(); j++) {
                        ArrayList<String> addMe = synSets.get(hypIDList.get(j));
                        for (int k = 0; k < addMe.size(); k++) {
                            hypList.add(addMe.get(k));
                        }
                    }
                }  
            }    
    	} 

        Set<String> hypSet = new HashSet(hypList);
        return hypSet;
	}
}	
