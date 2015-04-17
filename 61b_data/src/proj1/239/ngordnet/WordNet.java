/** WordNet .java
 *  @author Colin Schoen
 */ 

package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;


public class WordNet {
    private BufferedReader synsets;
    private BufferedReader hyponyms;
    /* digraph 
     * hashmap index to words
     * hashmap words to indexes
     * hashset of nouns
     * */
    //Digraph of all the Hyponym relationships
    private Digraph getHyponyms;
    //Synset Index -> Set of Words
    private HashMap<Integer, HashSet<String>> getWords = new HashMap<Integer, HashSet<String>>();
    //Word -> Set of Synset Indexes
    private HashMap<String, HashSet<Integer>> getSynsets = new HashMap<String, HashSet<Integer>>();
    //Hashset of all nouns
    private HashSet<String> getNouns = new HashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /*
         * ================================
         *       *Read Synset File
        /* ================================
         * */
        BufferedReader synFile = readCSV(synsetFilename);
        String row;
        String[] columns = null;
        List<String> words;
        try {
            while ((row = synFile.readLine()) != null) {
                columns = row.split(",");
                words = Arrays.asList(columns[1].split(" "));
                this.getWords.put(Integer.parseInt(columns[0]), new HashSet<String>(words));
                //Add every word to the hashset of nouns
                this.getNouns.addAll(words);
                //Add each work to the getSynsets hashmap
                HashSet<Integer> indexes;
                for (String word: words) {
                    //See if an existing HashSet of indexes exists
                    indexes = this.getSynsets.get(word);
                    if (indexes != null) {
                        indexes.add(Integer.parseInt(columns[0]));
                    } else {
                        //We need to first create the hashset
                        indexes = new HashSet<Integer>();
                        indexes.add(Integer.parseInt(columns[0]));
                    }
                    this.getSynsets.put(word, indexes);
                }

            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        if (synFile != null) {
            try {
                synFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
         * ================================
         *       *Read Hypo File
        /* ================================
         * */
        BufferedReader hypoFile = readCSV(hyponymFilename);
        //Our last read synFile index number + 1 should give us enough space for the Hyponyms
        this.getHyponyms = new Digraph(Integer.parseInt(columns[0]) + 1);
        try {
            int v;
            boolean isFirstColumn;
            while ((row = hypoFile.readLine()) != null) {
                //Create an array of colums in this row
                columns = row.split(",");
                //Define our vertex
                v = Integer.parseInt(columns[0]);
                //Now we need to look through the rest of the row and create edges 
                isFirstColumn = true;
                for (String w: columns) {
                    if (!isFirstColumn) {
                        this.getHyponyms.addEdge(v, Integer.parseInt(w));
                    }
                    isFirstColumn = false;
                }
            }
        } catch (IOException e) {
            if (hypoFile != null) {
                try {
                    hypoFile.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /*
     * readCSV
     * String src -- source path of csv
     * BufferedReader -- returns a Buffered Reader of the file
     * */
    private BufferedReader readCSV(String src) {
        BufferedReader file = null;
        try { 
            file = new BufferedReader(new FileReader(src));
        } catch (FileNotFoundException e) { 
            e.printStackTrace();
        }
        return file;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.getNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.getNouns;
    }

    /* Searches through the Hashmap of words and indexes.
     * Returns the integer index of the synset where the word is found
     * @param String w -- Word to be search for
     * */
    private HashSet<Integer> getWordIds(String w) {
        if (w == null) { 
            throw new IllegalArgumentException("w cannot be null"); 
        }
        HashSet<Integer> indexes = this.getSynsets.get(w);      
        return indexes;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
     * If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     * */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> wordIds = this.getWordIds(word);
        Set<Integer> indexes = GraphHelper.descendants(this.getHyponyms, wordIds);
        //Add our not only the desendant synsets to the indexes set
        for (int index: wordIds) {
            indexes.add(index);
        }
        //Create a set of strings which are the words of all of the words in these synsets
        HashSet<String> words = new HashSet<String>();
        HashSet<String> subsetOfWords;
        for (int index: indexes) {
            subsetOfWords = this.getWords.get(index);   
            for (String w: subsetOfWords) {
                words.add(w);
            }
        }   
        //Finally return our set of words
        return words;
    }

}
//WordNet .java 

