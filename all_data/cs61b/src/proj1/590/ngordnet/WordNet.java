package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    private In inputSynset;
    private In inputHyponym;
    private List<String> synsetStringArray;
    private List<String> hyponymStringArray;
    private Digraph hyponymDigraph;
    private List<List<String>> synsetArrayOfArrays;
    private List<List<String>> hyponymArrayOfArrays;
    private HashSet<String> nounHashSet;

    public WordNet(String synsetFilename, String hyponymFilename) {

        // Have to take in the data from the file, using the Princeton In library
        this.inputSynset = new In(synsetFilename);
        this.inputHyponym = new In(hyponymFilename);

        // Actually read in the file's contents
        this.synsetStringArray = Arrays.asList(this.inputSynset.readAllLines());
        this.hyponymStringArray = Arrays.asList(this.inputHyponym.readAllLines());

        this.synsetArrayOfArrays = new ArrayList<List<String>>();
        this.hyponymArrayOfArrays = new ArrayList<List<String>>();
        this.nounHashSet = new HashSet<String>();

        for (int i = 0; i < this.synsetStringArray.size(); i++) {
            this.synsetArrayOfArrays.add(new ArrayList<String>());
            String[] tokenized = this.synsetStringArray.get(i).split(",");
            this.synsetArrayOfArrays.get(i).add(tokenized[0]);
            String[] tokenized2 = tokenized[1].split(" ");
            for (String s : tokenized2) {
                this.nounHashSet.add(s);
                this.synsetArrayOfArrays.get(i).add(s);
            }
        }

        for (int i = 0; i < this.hyponymStringArray.size(); i++) {
            this.hyponymArrayOfArrays.add(new ArrayList<String>());
            String[] tokenized = this.hyponymStringArray.get(i).split(",");
            // String[] tokenized2 = tokenized[1].split(" ");
            for (String s : tokenized) {
                this.hyponymArrayOfArrays.get(i).add(s);
            }
        }

        // Create a digraph which use the given keys to make itself
        this.hyponymDigraph = new Digraph(this.synsetArrayOfArrays.size());

        // Populate the digraph
        for (int i = 0; i < this.hyponymArrayOfArrays.size(); i++) {
            for (int j = 1; j < this.hyponymArrayOfArrays.get(i).size(); j++) {
                String s = this.hyponymArrayOfArrays.get(i).get(j);
                if (s != null) {
                    this.hyponymDigraph.addEdge(Integer.parseInt(
                        this.hyponymArrayOfArrays.get(i).get(0)), Integer.parseInt(
                        this.hyponymArrayOfArrays.get(i).get(j)));
                } else {
                    break;
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nounHashSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.nounHashSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new HashSet<String>();
        if (!this.isNoun(word)) {
            return hyponymSet;
        }
        hyponymSet.add(word);
        Set<Integer> hyponymIDSet = new HashSet<Integer>();
        for (int i = 0; i < this.synsetArrayOfArrays.size(); i++) {
            if (this.synsetArrayOfArrays.get(i).get(1).contains(word)) {
                // System.out.println("Well I got here!");
                // System.out.println(this.synsetArrayOfArrays.get(i));
                hyponymIDSet.add(
                    Integer.parseInt(this.synsetArrayOfArrays.get(i).get(0)));
                for (int j = 1; j < this.synsetArrayOfArrays.get(i).size(); j++) {
                    hyponymSet.add(this.synsetArrayOfArrays.get(i).get(j));
                }
                // String temp = this.synsetArrayOfArrays.get(i).get(j);
                // System.out.println("temp for " + word + " is currently: " + temp);
                // while (temp.contains(" ")) {
                //     hyponymSet.add(temp.substring(0, temp.indexOf(" ")));
                //     temp = temp.substring(temp.indexOf(" ") + 1, temp.length());
                // }
                // hyponymSet.add(temp.substring(0, temp.length()));
                // System.out.println("hyponymSet for " + word + " is currently: " + hyponymSet);
            }
        }
        // System.out.println("hyponymIDSet is now: " + hyponymIDSet);
        hyponymSet.addAll(this.findHyponymsByID(hyponymIDSet));
        return hyponymSet;
    }

    private Set<String> findHyponymsByID(Set<Integer> idSet) {
        Set<Integer> hyponymIDs = GraphHelper.descendants(this.hyponymDigraph, idSet);

        // System.out.println("the given set is: " + idSet);
        // System.out.println("hyponymIDs fomr the descendants method is: " + hyponymIDs);
        Set<String> findHyponymsByID = new HashSet<String>();
        for (List<String> l : this.synsetArrayOfArrays) {
            if (hyponymIDs.contains(Integer.parseInt(l.get(0)))) {
                // System.out.println(l.get(0));
                // System.out.println("Got here!");
                for (int i = 1; i < l.size(); i++) {
                    findHyponymsByID.add(l.get(i));
                }
                // String toAdd = l.get(1);
                // while (toAdd.contains(" ")) {
                //     findHyponymsByID.add(toAdd.substring(0, toAdd.indexOf(" ")));
                //     toAdd = toAdd.substring(toAdd.indexOf(" ") + 1, toAdd.length());
                // }
                // findHyponymsByID.add(toAdd.substring(0, toAdd.length()));
            }
        }
        return findHyponymsByID;
    }


    // public static void main(String[] args) {
    //     WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
    //     System.out.println("hyponymArrayOfArrays is: " + wn.hyponymArrayOfArrays);
    //     System.out.println("synsetArrayOfArrays is: " + wn.synsetArrayOfArrays);
    //     System.out.println("The DiGraph is: " + wn.hyponymDigraph);

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
    //     System.out.println("");
    //     System.out.println("All nouns:");
    //     for (String noun : wn.nouns()) {
    //         System.out.println(noun);
    //     }

    //      The code below should print the following (maybe not in this order): 
    //         Hypnoyms of increase:
    //         augmentation
    //         increase
    //         leap
    //         jump
        

    //     System.out.println("");
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

    //     System.out.println("");
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
    //     System.out.println("");
    //     System.out.println("Hypnoyms of change:");

    //     WordNet wn2 = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
    //     for (String noun : wn2.hyponyms("change")) {
    //         System.out.println(noun);
    //     }              
    // }    
}
