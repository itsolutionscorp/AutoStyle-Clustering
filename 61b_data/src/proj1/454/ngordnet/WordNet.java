package ngordnet;

import java.util.List;
import java.util.Arrays;  
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/** Creates WordNets by reading in files 
 *  @author Tatevik Stepanyan
 */

public class WordNet {

    private TreeMap<Integer, ArrayList<String>> synset;
    private TreeMap<Integer, ArrayList<Integer>> hyponyms;
    private Digraph g;

/** Creates a WordNet using files from synsetFile and hyponymFile. */
    public WordNet(String synsetFile, String hyponymFile) {
        synset = new TreeMap<Integer, ArrayList<String>>();
        hyponyms = new TreeMap<Integer, ArrayList<Integer>>();
        In synsetIn = new In(synsetFile);
        In hyponymIn = new In(hyponymFile);
        while (synsetIn.hasNextLine()) {
            String currSynLine = synsetIn.readLine();
            List<String> sContentsList = Arrays.asList(currSynLine.split(","));
            ArrayList<String> sContents = new ArrayList<String>(sContentsList);
            for (int i = 2; i < sContents.size(); i += 1) {
                sContents.remove(i);
            }
            Integer synsetID = Integer.parseInt(sContents.remove(0));
            sContents = new ArrayList<String>(Arrays.asList(sContents.get(0).split(" ")));
            synset.put(synsetID, sContents);
        }
        while (hyponymIn.hasNextLine()) {
            String currHypLine = hyponymIn.readLine();
            List<String> hContentsString = Arrays.asList(currHypLine.split(","));
            ArrayList<Integer> hContentsInts = new ArrayList<Integer>();
            for (String string : hContentsString) {
                hContentsInts.add(Integer.parseInt(string));
            }
            Integer hypKey = hContentsInts.remove(0);
            if (hyponyms != null && hyponyms.keySet().contains(hypKey)) {
                for (Integer content : hContentsInts) {
                    hyponyms.get(hypKey).add(content);
                }
            } else { 
                hyponyms.put(hypKey, hContentsInts); 
            }
        }
        Set<Integer> synKeys = synset.keySet();
        Set<Integer> hypKeys = hyponyms.keySet();
        int gLength = synKeys.size();
        g = new Digraph(gLength);
        for (Integer synKey : synKeys) {
            ArrayList<Integer> vertices = hyponyms.get(synKey);
            if (vertices != null) {
                for (Integer vertex : vertices) {
                    g.addEdge(synKey, vertex);
                }
            }
        }
    }

    /** Tests if noun is a word in a WordNet. */
    public boolean isNoun(String noun) {
        for (Integer synID : synset.keySet()) {
            ArrayList<String> wordList = synset.get(synID);
            for (String word : wordList) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns all nouns in a WordNet. */
    public Set<String> nouns() {
        Set nouns = new TreeSet<String>();
        Iterator keyIter = synset.keySet().iterator();
        while (keyIter.hasNext()) {
            ArrayList wordList = synset.get(keyIter.next());
            for (int i = 0; i < wordList.size(); i += 1) {
                nouns.add(wordList.get(i));
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms and synonyms of word. */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>(); //the hyponyms
        Set<Integer> occurrences = new TreeSet<Integer>(); //synsets ID's that have word

        //* Find all occurrences of word in synset *//
        for (Integer synID : synset.keySet()) {
            ArrayList<String> wordList = synset.get(synID);
            if (wordList.contains(word)) {
                occurrences.add(synID);
            }
        }

        //* Utilizes GraphHelper class to get hyponyms (descendants) *//

        Set<Integer> reachables = GraphHelper.descendants(g, occurrences);
        for (Integer reachID : reachables) {
            ArrayList<String> hymWords = synset.get(reachID);
            for (String hymWord : hymWords) {
                result.add(hymWord);
            }
        }
        result.add(word);
        return result;
    }
}

/** Sources:
* http://docs.oracle.com/javase/7/docs/api/java/util/List.html
* http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
* http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
* http://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html
* http://docs.oracle.com/javase/7/docs/api/java/util/Set.html
* http://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html
* http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
* introcs.cs.princeton.edu/stdlib
*/
