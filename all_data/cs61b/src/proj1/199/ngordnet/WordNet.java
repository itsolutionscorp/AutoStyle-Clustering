package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;


public class WordNet {
    //idtosynset maps each unique id to a string[] in its corresponding synset
    private HashMap<Integer, String[]> idtosynset = new HashMap<Integer, String[]>();
    //nountoid maps each noun to an arraylist of its ids
    private HashMap<String, ArrayList<Integer>> nountoid 
        = new HashMap<String, ArrayList<Integer>>();
    //nouns is simply a Set of all the nouns in whichever synset file is used
    private Set<String> nouns = new TreeSet<String>();
    private static final int MAX_SIZE = 2000000;
    private Digraph d = new Digraph(MAX_SIZE);

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        
        File hyponymFile = new File(hyponymFilename);
        File synsetFile = new File(synsetFilename); 

        In inSynset = new In(synsetFile);
        In inHyponym = new In(hyponymFile);

        while (inSynset.hasNextLine()) {
            // Split into a String[] using , delimiter
            String[] line = inSynset.readLine().split(","); 
            // the first element of this list is the sysnet id
            int synsetId = Integer.parseInt(line[0]); 
            String synonymSet = line[1]; //the second element is the synset itself

            /* IF A SYNSET CONTAINS MORE THAN ONE WORD, SEPARATE BY WHITE SPACE */
            if (synonymSet.contains(" ")) {
                String[] splitsynset = synonymSet.split(" ");
                for (String word: splitsynset) {
                    nouns.add(word);

                    /* add each word in synset as key v pair to nountoid iff it 
                    does not already exist else add the key to the corresponding 
                    value arraylist */
                    if (!nountoid.containsKey(word)) {
                        ArrayList<Integer> value = new ArrayList<Integer>();
                        value.add(synsetId);
                        nountoid.put(word, value);
                    } else {
                        nountoid.get(word).add(synsetId);
                    }
                }

            } else {
                //this means the synonymSet is only one word and can be directly added to nouns
                nouns.add(synonymSet);
                /*if synonymSet is only one word, add it as a key pair value iff 
                it does not already exist in nountoid; else add the key to the corresponding 
                value arraylist */
                if (!nountoid.containsKey(synonymSet)) {
                    ArrayList<Integer> value = new ArrayList<Integer>();
                    value.add(synsetId);
                    nountoid.put(synonymSet, value);
                } else {
                    nountoid.get(synonymSet).add(synsetId);
                }
            }
            //HASHMAP id: add each unique id corresponding to a list
            idtosynset.put(synsetId, synonymSet.split(" ")); 
            //containing words in its synset
        } // end of parsing through synset file
            /* hyponyms text file handling*/
        while (inHyponym.hasNextLine()) {
            String[] line = inHyponym.readLine().split(",");
            int parent = Integer.parseInt(line[0]);
            for (int i = 1; i <= (line.length - 1); i++) {
                int descendent = Integer.parseInt(line[i]);
                d.addEdge(parent, descendent);
            }
        } 
            /* hyponyms text file handling*/
        
    } // end of constructor


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
         //id is an arraylist of ids corresponding to word
        ArrayList<Integer> ids = nountoid.get(word);
        Set<Integer> idHyponyms = new TreeSet<Integer>();

        for (int id: ids) { // add the ids of a word to idhyponyms 
            idHyponyms.add(id);
        }

        idHyponyms = GraphHelper.descendants(d, idHyponyms);
        Set<String> wordHyponyms = new TreeSet<String>();

        for (int x: idHyponyms) { // lookup words for each id 
            String[] synset = idtosynset.get(x); //synset is a string of list of hyponyms
            for (String each: synset) {
                wordHyponyms.add(each);
            }
        }
        return wordHyponyms; 
    }
} // end of wordnetclass

  


