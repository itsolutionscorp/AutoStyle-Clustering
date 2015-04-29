package ngordnet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.introcs.In; //

/** Class that does this.
 *  @author Matt Anderson
 */
public class WordNet {

    // Maps a synset ID to an array that includes all synset IDs of its hyponyms.
    private Map<String, String[]> hyponyms = new HashMap<String, String[]>();
    
    // Maps a synset ID to the synonym(s) in the synset.
    private Map<String, String[]> synonyms = new HashMap<String, String[]>();
    
    // All of the nouns.
    private Set<String> nouns = new HashSet<String>();
    
    // Map from the noun to the IDs of the synsets that it is in.
    // The second values are a string of Integers.
    // (e.g. "123" or "11,12,100")
    private Map<String, String> nounIDs = new HashMap<String, String>();
    
    
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME.
     *  Description and signature from WordNet javadocs.
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        
        // Initialize hyponyms.
        String line;
        String[] lineContents;
        String[] lineSynonyms;
        while (hyponymIn.hasNextLine()) {
            line = hyponymIn.readLine();
            lineContents = line.split(","); // .split from piazza post
            hyponyms.put(lineContents[0], 
                Arrays.copyOfRange(lineContents, 1, lineContents.length)); 
            // copyOfRange from StackOverflow
        }

        // Initialize synonyms and nouns.
        while (synsetIn.hasNextLine()) {
            line = synsetIn.readLine();
            lineContents = line.split(",");
            lineSynonyms = lineContents[1].split(" ");
            
            synonyms.put(lineContents[0], lineSynonyms);

            for (int i = 0; i < lineSynonyms.length; i++) {
                nouns.add(lineSynonyms[i]);
                if (nounIDs.containsKey(lineSynonyms[i])) {
                    // Noun is already in NounIDs. Add the new ID.
                    nounIDs.put(lineSynonyms[i], nounIDs.get(lineSynonyms[i]) 
                        + "," + lineContents[0]);
                } else {
                    nounIDs.put(lineSynonyms[i], lineContents[0]);
                }               
            }           
        }
        
        nouns = nounIDs.keySet();
    }
    
    /**
     * 
     * @param noun
     * @return 
     */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }
    
    /**
     * 
     * @return
     */
    public Set<String> nouns() {
        return nouns;
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
     *  If WORD belongs to multiple synsets, return all hyponyms of all of these synsets.
     * 
     * @param word
     * @return
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypsAndSyns = new HashSet<String>();
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Invalid word: " + word);
        }
        
        String[] ids = nounIDs.get(word).split(",");
        
        // One ID at a time:
        // hypsAndSyns.add(word);
        for (int i = 0; i < ids.length; i++) {

            // Add all hyponyms and synonyms starting from synset #ID[i].
            hypsAndSyns = addHyponyms(hypsAndSyns, ids[i]);
        }
        return hypsAndSyns; 
    }
    
    /** Recursively adds all synonyms in synset #id and all of its hyponyms.
     * 
     * @param s
     * @param id
     * @return
     */
    private Set<String> addHyponyms(Set<String> s, String id) {
        
        String[] idHyponyms;
        Set<String> copy = addSynonyms(s, id);
        
        if (!hyponyms.containsKey(id)) {
            // There are no hyponyms under the current synset.
            // Just add the synonyms here.
            return copy;
        } else {
            // There are hyponyms under the current synset.
            // Add the synonyms and then add the rest of the hyponyms
            idHyponyms = hyponyms.get(id);
            for (int i = 0; i < idHyponyms.length; i++) {
                copy = addHyponyms(copy, idHyponyms[i]);
            }
            return copy;
        }
        
    }
    
    /** Adds all words in synset #ID to Set S.
     * 
     * @param id
     * @param s
     * @return Updated Set S.
     */
    private Set<String> addSynonyms(Set<String> s, String id) {
        String[] a = synonyms.get(id);
        if (a == null) {
            return s;
        }
        for (int i = 0; i < a.length; i++) {
            s.add(a[i]);
        }
        return s;
    }

}
