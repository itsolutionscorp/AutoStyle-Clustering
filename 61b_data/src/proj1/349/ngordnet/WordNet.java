package ngordnet;
import java.util.Set; 
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList; 
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, ArrayList<String>> mapOfWords;
    private int count = 0;
    private String fileline;
    private String[] tokens;
    private Integer number;
    private Set<String> allNouns;
    private ArrayList<ArrayList<String>> synsArrayWords;
    private Digraph wordNetGraph;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        mapOfWords = new HashMap<Integer, ArrayList<String>>();
        while (synsetFile.hasNextLine()) {
            fileReader(synsetFile);
            String[] words = tokens[1].split(" ");
            ArrayList<String> listOfWords = new ArrayList<String>();
            for (int i = 0; i < words.length; i += 1) {
                listOfWords.add(words[i]);
            }
            mapOfWords.put(number, listOfWords); 
            count++;     
        }
        wordNetGraph = new Digraph(count);
        while (hyponymFile.hasNextLine()) {
            fileReader(hyponymFile);
            int unwrappedNumber = number;
            for (int i = 1; i < tokens.length; i++) {
                wordNetGraph.addEdge(number, Integer.parseInt(tokens[i]));
            }
        }
    }
    /**
    * Reads the next line of the file provided.
    * <p>
    * This method reads a line of a file and splits the line into words that are stored in
    * a tokens array. The first word from tokens is then assigned to number.
    * 
    * @param filename   a file from which the line is extracted
    */
    private void fileReader(In filename) {
        fileline = filename.readLine();
        tokens = fileline.split(",");
        number = Integer.parseInt(tokens[0]);
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        synsArrayWords = new ArrayList<ArrayList<String>>(mapOfWords.values());
        for (int i = 0; i < synsArrayWords.size(); i++) {
            for (int j = 0; j < synsArrayWords.get(i).size(); j++) {
                if (noun.equals(synsArrayWords.get(i).get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        allNouns  = new HashSet<String>();
        synsArrayWords = new ArrayList<ArrayList<String>>(mapOfWords.values());
        for (int i = 0; i < synsArrayWords.size(); i++) {
            for (int j = 0; j < synsArrayWords.get(i).size(); j++) {
                allNouns.add(synsArrayWords.get(i).get(j));
            }
        }
        return allNouns;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> setOfhyponyms = new HashSet<String>();
        Set<Integer> keyNumbers = new HashSet<Integer>();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < mapOfWords.get(i).size(); j++) {
                if (mapOfWords.get(i).get(j).equals(word)) {
                    keyNumbers.add(i);
                }
            }
        }
        Set<Integer> descendantNumbers = GraphHelper.descendants(wordNetGraph, keyNumbers);
        for (Integer k : descendantNumbers) {
            for (int x = 0; x < mapOfWords.get(k).size(); x++) {
                setOfhyponyms.add(mapOfWords.get(k).get(x));
            }        
        }
        return setOfhyponyms;
    }
}
