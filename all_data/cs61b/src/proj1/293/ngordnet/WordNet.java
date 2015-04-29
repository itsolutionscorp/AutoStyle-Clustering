//An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a word.
package ngordnet;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
// import java.io.FileNotFoundException;
// import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
	private TreeMap<Integer, TreeSet<String>> synTree = new TreeMap<Integer, TreeSet<String>>();
    private TreeMap<String, TreeSet<Integer>> synTreeVK = new TreeMap<String, TreeSet<Integer>>();
	private Digraph hypDigraph;
    private In synIn;
    private In hypIn;
    private GraphHelper g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        //make syn treemap
    	synReader(synsetFilename);
       	//make hyp digraph and treemap
       	hypReader(hyponymFilename);
    }

    private void synReader(String synsetFilename) {
        // try {
            synIn = new In(synsetFilename);
            while (synIn.hasNextLine()) {
                String[] tempArray = synIn.readLine().split(",");
                int id = Integer.parseInt(tempArray[0]);
                String[] words = tempArray[1].split(" ");
                TreeSet<String> tempTreeSet = new TreeSet<String>();
                //make set of words
                for (int i = 0; i < words.length; i++) {
                    tempTreeSet.add(words[i]);
                    if (!synTreeVK.isEmpty() && synTreeVK.containsKey(words[i])) {
                        synTreeVK.get(words[i]).add(id);
                    } else {
                        TreeSet<Integer> tempID = new TreeSet<Integer>();
                        tempID.add(id);
                        synTreeVK.put(words[i], tempID);
                    }
                }
                synTree.put(id, tempTreeSet);
            }
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException ioe) {
        //     ioe.printStackTrace();
        // }
    }
    
    private void hypReader(String hyponymFilename) {
        // try {
            hypIn = new In(hyponymFilename);
            hypDigraph = new Digraph(synTree.size());
            while (hypIn.hasNextLine()) {
                String[] tempArray = hypIn.readLine().split(",");
                int id = Integer.parseInt(tempArray[0]);
                // TreeSet<Integer> tempTreeSet;
                //make add edges
                for (int i = 1; i < tempArray.length; i++) {
                    hypDigraph.addEdge(id, Integer.parseInt(tempArray[i]));
                    // tempTreeSet.add(Integer.parseInt(tempArray[i]));
                }
                // hypTree.put(id, tempTreeSet);
            }
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException ioe) {
        //     ioe.printStackTrace();
        // }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synTreeVK.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // Set<String> noun;
        // Collection<TreeSet<String>> tempCol = synTree.values();
        // Iterator<TreeSet<String>> tempIter = tempCol.iterator();
        // while (tempIter.hasNext()) {
        //     // noun.add(tempIter.next());
        //     TreeSet<String> tempTreeSet = tempIter.next();
        //     Iterator<String> tempTreeIter = tempTreeSet.iterator();
        //     while (tempTreeIter.hasNext()) {
        //         noun.add(tempTreeIter.next());
        //     }
        // }
        // return noun;
        return synTreeVK.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // return null add all method
        Set<Integer> ids = synTreeVK.get(word);
        ids = g.descendants(hypDigraph, ids);
        Iterator<Integer> iter = ids.iterator();
        Set<String> words = new TreeSet<String>();
        while (iter.hasNext()) {
            words.addAll(synTree.get(iter.next()));
        }
        return words;
    }

    // public static void main(String[] args) {
    //     WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt", "./p1data/wordnet/hyponyms11.txt");

    //     /* These should all print true. */
    //     System.out.println(wn.isNoun("jump"));
    //     System.out.println(wn.isNoun("leap"));
    //     System.out.println(wn.isNoun("nasal_decongestant"));

    //     /* The code below should print the following (maybe not in this order): 
    //         All nouns:
    //         augmentation
    //         nasal_decongestant
    //         change
    //         action
    //         actifed
    //         antihistamine
    //         increase
    //         descent
    //         parachuting
    //         leap
    //         demotion
    //         jump
    //     */

    //     System.out.println("All nouns:");
    //     for (String noun : wn.nouns()) {
    //         System.out.println(noun);
    //     }

    //     /* The code below should print the following (maybe not in this order): 
    //         Hypnoyms of increase:
    //         augmentation
    //         increase
    //         leap
    //         jump
    //     */

    //     System.out.println("Hypnoyms of increase:");
    //     for (String noun : wn.hyponyms("increase")) {
    //         System.out.println(noun);
    //     }

    //     /* The code below should print the following (maybe not in this order): 
    //         Hypnoyms of jump:
    //         parachuting
    //         leap
    //         jump
    //     */

    //     System.out.println("Hypnoyms of jump:");
    //     for (String noun : wn.hyponyms("jump")) {
    //         System.out.println(noun);
    //     }  

    //     /* The code below should print the following (maybe not in this order):
    //         Hypnoyms of change:
    //         alteration
    //         saltation
    //         modification
    //         change
    //         variation
    //         increase
    //         transition
    //         demotion
    //         leap
    //         jump        
    //     */

    //     /** From: http://goo.gl/EGLoys */
    //     System.out.println("Hypnoyms of change:");

    //     WordNet wn2 = new WordNet("./p1data/wordnet/synsets14.txt", "./p1data/wordnet/hyponyms14.txt");
    //     for (String noun : wn2.hyponyms("change")) {
    //         System.out.println(noun);
    //     }              
    // }    
}