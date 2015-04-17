package ngordnet;

import java.util.Set;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;

/** Object that stores all word counts for a given year
 *  WordNet allows the user to describe the relationship 
 *  noun-input.

    @author Karen Lu
*/

public class WordNet {
    private Digraph thenet;
    private Set<String> nounSet;
    private Map<String, Set<Integer>> stringmap;
    private Map<Integer, Set<String>> wordset;

    /** Creates an wordnet from synset and hyponym files */
    public WordNet(String synsetFilename, String hyponymFilename) {
        int id = 0;
        String eachwordset;

        // reading in the files
        In sfile = new In(synsetFilename);
        In hfile = new In(hyponymFilename);

        //initiating the empty word storage
        stringmap = new HashMap<String, Set<Integer>>();
        wordset = new HashMap<Integer, Set<String>>();
        nounSet = new HashSet<String>();

        String currentline = sfile.readLine();
        while (currentline != null) {
            String[] parsed = currentline.split(",");
            id = Integer.parseInt(parsed[0]);
            eachwordset = parsed[1];
            wordsetcons(id, eachwordset);
            currentline = sfile.readLine();
        }

        int maxid = id + 1;

        // construct the digraph of wordnet
        thenet = new Digraph(maxid);
        currentline = hfile.readLine();
        while (currentline != null) {
            String[] parsed = currentline.split(",");
            digraphbuilder(parsed);
            currentline = hfile.readLine();
        }
    }

    /** Construct each entry of wordnet map 
      * @param  id
                each word
    */
    private void wordsetcons(int id, String eachwordset) {
        String[] words = eachwordset.split(" ");
        Set<String> currentset = new HashSet<String>();

        for (String word : words) {
            if (!stringmap.containsKey(word)) {
                Set<Integer> ids = new HashSet<Integer>();
                ids.add(id);
                stringmap.put(word, ids);
            } else {
                stringmap.get(word).add(id);            
            }
            nounSet.add(word);
            currentset.add(word);

        }
        wordset.put(id, currentset);

    }

    /** draw the connections between each id in diagraph 
      * @param  array of nouns in the wordnet
    */

    private void digraphbuilder(String[] links) {
        int syn = Integer.parseInt(links[0]);
        int hyn;
        for (int i = 1; i < links.length; i++) {

            hyn = Integer.parseInt(links[i]);

            thenet.addEdge(syn, hyn);
        }
    }


    /** @param: string
      * @return whether the string is a noun
      */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /** @param: None
      * @return Set of nouns
      */
    public Set<String> nouns() {
        return nounSet;
    }

    /** @param: String word
      * @return set of hyponyms of WORD as well as all synonyms of WORD 
      */
    public Set<String> hyponyms(String word) {
        Set<String> resultset = new HashSet<String>();

        if (isNoun(word)) {
            Set<Integer> ids = GraphHelper.descendants(thenet, stringmap.get(word));  
            for (int id:  ids) {
                resultset.addAll(wordset.get(id));
            }
        } 
        return resultset;
    }
}
