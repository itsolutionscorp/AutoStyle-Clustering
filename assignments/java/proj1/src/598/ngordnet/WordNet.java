package ngordnet; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Set;
import java.util.Map; 
import edu.princeton.cs.introcs.In; 
import edu.princeton.cs.algs4.Digraph; 
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, ArrayList<String>> synset; 
    private HashSet<String> nouns; 
    private Digraph hyponymG;       
    private int vectorSize; 
    public WordNet(String synsetFilename, String hyponymFilename) {
        //synset parsing
        In input = new In(synsetFilename); 
        synset = new HashMap<Integer, ArrayList<String>>(); 
        nouns = new HashSet<String>(); 
        while (input.hasNextLine()) {
            String line = input.readLine();
            String[] commaSplit = line.split(","); 
            Integer id = Integer.parseInt(commaSplit[0]); 
            String[] spaceSplit = commaSplit[1].split(" "); 
            ArrayList<String> syns = new ArrayList<String>(); 
            for (int i = 0; i < spaceSplit.length; i++) {
                syns.add(spaceSplit[i]); 
                nouns.add(spaceSplit[i]); 
            }
            synset.put(id, syns);
            vectorSize++;    
        }
        //hyponym parsing
        In hInput = new In(hyponymFilename); 
        hyponymG = new Digraph(vectorSize);   
        while (hInput.hasNextLine()) {
            String line = hInput.readLine(); 
            String[] commaSplit = line.split(","); 
            Integer id = Integer.parseInt(commaSplit[0]); 
            ArrayList<Integer> hyp = new ArrayList<Integer>(); 
            for (int i = 0; i < commaSplit.length; i++) {
                Integer num = Integer.parseInt(commaSplit[i]); 
                hyponymG.addEdge(id, num); 
            }
        }

    }    

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (nouns.contains(noun)); 
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns; 
    }
    
    /* influenced a post on StackOverflow (questions/1383797) and given input on by Quincy Hyunh */
    // sorts the map by putting entries into collection and then back again
    private <Integer, String> Set<Integer> keyByValue(HashMap<Integer, ArrayList<String>> map, 
                                           String value) {
        HashSet<Integer> keys = new HashSet<Integer>(); 
        for (Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
            ArrayList<String> values = entry.getValue();
            if (values.contains(value)) {
                keys.add(entry.getKey()); 
            }
        }
        return keys; 
    }


    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> id = keyByValue(synset, word);  
        Set<Integer> hyponymids = GraphHelper.descendants(hyponymG, id); 
        HashSet<String> words = new HashSet<String>(); 
        for (Integer i : hyponymids) {
            ArrayList<String> syns = synset.get(i); 
            for (String j : syns) {
                words.add(j); 
            }
        }
        return words; 
    }
}

