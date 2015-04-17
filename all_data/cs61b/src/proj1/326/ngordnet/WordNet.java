package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

/* @author Neil Shankar */

public class WordNet {

    private String[][] words; //each row is a synset with ID = index
    private String[] dictionary; //each entry is a definition with ID = index
    private Digraph dg; 
    private int numSynsets;

    /* words is an array of synsets, where each synset is an array. For example:
       [word0] [word1] [word2]     (Synset ID = 0)
       [word0]                     (Synset ID = 1)
       [word0] [word1]             (Synset ID = 2)
       ...
     */

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        numSynsets = findNumSynsets(synsetFilename);
        populateArrays(synsetFilename);
        makeDigraph(hyponymFilename);
    }

    /* Finds number of synsets in the wordnet (number of vertices).
     * It will be the index of the last synset + 1. */
    private int findNumSynsets(String sFilename) {
        In reader = new In(sFilename);
        String lastLine = reader.readLine();
        while (reader.hasNextLine()) {
            lastLine = reader.readLine();
        }
        return getID(lastLine, 0) + 1;
    }

    /* Given a string, returns the first number found:
     *    - starting at index start
     *    - ending at a comma or the end of the string */
    private int getID(String str, int start) {
        String strNum = "";
        for (int i = start; i < str.length(); i += 1) {
            String curr = str.substring(i, i + 1);
            if (curr.equals(",")) {
                i = str.length(); //break the for loop
            } else {
                strNum += curr;
            }
        }
        return Integer.parseInt(strNum);
    }

    /* Populates words and dictionary. */
    private void populateArrays(String sFilename) {
        words = new String[numSynsets][];
        dictionary = new String[numSynsets];
        In sReader = new In(sFilename);
        for (int i = 0; i < numSynsets; i += 1) {
            String wholeLine = sReader.readLine();
            parseSynsetString(wholeLine, i);
        }
    }

    /* Parses each input line to find the words and definition of 
     * each synset. */
    private void parseSynsetString(String input, int id) {
        List<String> synset = new ArrayList<String>();
        int synsetStart = input.indexOf(",") + 1;
        int definitionStart = -1;
        String synWord = "";
        int numWords = 0;

        //add each word of the synset to the words array
        for (int i = synsetStart; i < input.length(); i += 1) {
            String curr = input.substring(i, i + 1);
            if (curr.equals(" ")) {
                synset.add(synWord);
                synWord = "";
                numWords += 1;
            } else if (curr.equals(",")) {
                synset.add(synWord);
                numWords += 1;
                definitionStart = i + 1;
                i = input.length(); //break the for loop
            } else {
                synWord += curr;
            }
        }

        //copy from ArrayList to array
        words[id] = new String[numWords];
        for (int n = 0; n < numWords; n += 1) {
            words[id][n] = synset.get(n);
        }

        dictionary[id] = input.substring(definitionStart, input.length());
    }

    /* Creates full digraph of indeces */
    private void makeDigraph(String hFilename) {
        dg = new Digraph(numSynsets);
        In hReader = new In(hFilename);
        while (hReader.hasNextLine()) {
            String wholeLine = hReader.readLine();
            parseHyponymString(wholeLine);
        }
    }

    /* Parses each input line to construct the digraph */
    private void parseHyponymString(String input) {
        int synsetID = getID(input, 0);
        int start = input.indexOf(",") + 1;
        while (start != 0) { //-1 + 1
            int hyponymID = getID(input, start);
            dg.addEdge(synsetID, hyponymID);
            start = input.indexOf(",", start) + 1;
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (idOf(noun, 0) != -1) {
            return true;
        }
        return false;
    }

    /* returns the ID of a particular word, starting from ID start.
     * Returns -1 if not found */
    private int idOf(String word, int start) {
        for (int i = start; i < numSynsets; i += 1) {
            for (int j = 0; j < words[i].length; j += 1) {
                if (words[i][j].equals(word)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (int i = 0; i < numSynsets; i += 1) {
            for (int j = 0; j < words[i].length; j += 1) {
                allNouns.add(words[i][j]);
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
        /* General Approach:
         * The IDs of all synsets in which the word is found are added
         * to synsetIDs. The word itself and all synonyms of the word are
         * added to the results set. 
         * Then, the synset IDs of all hyponyms are foud using GraphHelper, 
         * and all words in these synsets are added to the results set. */ 

        Set<Integer> synsetIDs = new TreeSet<Integer>();
        Set<String> result = new TreeSet<String>();
        int start = 0;
        int currID = idOf(word, start);

        while (currID != -1) {
            synsetIDs.add(currID);
            //add synonyms and the word itself to result
            for (int k = 0; k < words[currID].length; k += 1) {
                result.add(words[currID][k]);
            }
            start = currID + 1;
            currID = idOf(word, start);
        }

        Set<Integer> hyponymIDs = GraphHelper.descendants(dg, synsetIDs);
        Iterator<Integer> iter = hyponymIDs.iterator();
        while (iter.hasNext()) {
            currID = iter.next();
            for (int j = 0; j < words[currID].length; j += 1) {
                result.add(words[currID][j]);
            }
        }
        return result;
    }
}
