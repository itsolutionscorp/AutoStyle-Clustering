package ngordnet; 
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Description:
 *  An object that stores the WordNet graph in a manner useful 
 *  for extracting all hyponyms of a word.
 *
 *  @author Karen Chiao
 */

public class WordNet {

    private HashMap<Integer, ArrayList<String>> synset;
    private HashMap<ArrayList<String>, Integer> synsetRev;

    // private HashMap<Integer,ArrayList<Integer>> hyponym;
    private ArrayList<ArrayList<Integer>> hyponym;



    /* Constructor: Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {

        synset = new HashMap<Integer, ArrayList<String>>();
        synsetRev = new HashMap<ArrayList<String>, Integer>();
        hyponym = new ArrayList<ArrayList<Integer>>();
        // hyponym = new HashMap<Integer,ArrayList<Integer>>();

        In syn = new In(synsetFilename);
        In hypo = new In(hyponymFilename);

        while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] split1 = line.split(",");
            int stempkey = Integer.parseInt(split1[0]);
            String syns = split1[1];
            String[] temps = syns.split(" ");
            ArrayList<String> stempval = new ArrayList<String>();
            for (String s : temps) {
                stempval.add(s);
            }
            synset.put(stempkey, stempval);
            synsetRev.put(stempval, stempkey);
        }

        while (!hypo.isEmpty()) {
            String line = hypo.readLine();
            String[] arrayOfStrings = line.split(",");
            ArrayList<Integer> integersAL = new ArrayList<Integer>();
            for (int i = 0; i < arrayOfStrings.length; i++) {
                integersAL.add(Integer.parseInt(arrayOfStrings[i]));
            }
            hyponym.add(integersAL);

            // ArrayList<Integer> valuesAL = new ArrayList<Integer>();
            // for (Integer i : integersAL) {
            //     valuesAL.add(i);
            // }
            // int comma1 = line.indexOf(",");
            // int htempkey = Integer.parseInt(line.substring(0,comma1));
            // String sub = line.substring(comma1 + 1);
            // String[] arrayOfStrings = sub.split(",");
            // hyponym.put(htempkey, integersAL);
        }
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        HashSet<String> hs = new HashSet<String>();
        Set<ArrayList<String>> array = synsetRev.keySet();
        for (ArrayList<String> o : array) {
            hs.addAll(o);        
        }
        return hs;
    }

    /* Returns the set of all hyponym of WORD as well as all synonyms of WORD. */
    public Set<String> hyponyms(String word) {
        HashSet<String> allhyponyms = new HashSet<String>();
        int maxV = synset.size();
        // int maxV = 0;
        // for (Integer i : synset.keySet()) {
        //     if (maxV < i) {
        //         maxV = i;
        //     }
        // }
        // maxV += 1;
        Digraph digraph = new Digraph(maxV);

        // ArrayList<Integer> hypoKeys = new ArrayList<Integer>();
        // for (int i = 0; i < hyponym.size(); i++) {
        //     hypoKeys.add(hyponym.get(0).get(i));
        // }
        
        for (ArrayList<Integer> a : hyponym) {
            int key = a.get(0);
            for (int j = 1; j < a.size(); j++) {
                digraph.addEdge(key, a.get(j));
            }
        }

        // get synonyms (in same synset), ex. given "jump", will get "parachuting" as well as "leap"
        ArrayList<String> strArray = new ArrayList<String>();
        for (Integer k : getSynsetKeys(word)) {
            strArray.addAll(synset.get(k));
        }
        for (String str : strArray) {
            allhyponyms.add(str);
        }

        // for each int from set<int> with all synset IDS
        for (Integer s : GraphHelper.descendants(digraph, getSynsetKeys(word))) {
            for (String t : synset.get(s)) {
                allhyponyms.add(t);
            }
        }
        return allhyponyms;
    }

    private Set<Integer> getSynsetKeys(String word) { 
        // Set<ArrayList<String>> kset = synsetRev.keySet();
        HashSet<Integer> kset = new HashSet<Integer>();

        for (ArrayList<String> o : synsetRev.keySet()) {
            for (String p : o) {
                if (p.equals(word)) {
                    kset.add(synsetRev.get(o));
                }
            }
        }
        return kset;
    }

    // /* Returns the set of all hyponym of WORD as well as all synonyms of WORD. */
    // public Set<String> hyponyms(String word) {
    //     HashSet<String> hashset = new HashSet<String>();
    //     ArrayList<Integer> keys = getSynsetKeys(word);

    //     // get synonyms (in same synset), 
    //     // ex. given "jump", will get "parachuting" as well as "leap"
    //     ArrayList<String> strArray = new ArrayList<String>();
    //     for (Integer i : keys) {
    //         strArray.addAll(synset.get(i));
    //     }
    //     for (String str : strArray) {
    //         hashset.add(str);
    //     }

    //     // get hyponym of original WORD
    //     HashSet<Integer> intArray = new HashSet<Integer>();
    //     for (ArrayList<Integer> arr : hyponym) {
    //         if (keys.contains(arr.get(0))) { 
    //             for (int i = 1; i < arr.size(); i++) {
    //                 intArray.add(arr.get(i));
    //             }
    //             // intArray.add(arr.sublist(1, arr.length));
    //         }
    //     }    
    //     // for (Integer i : keys) {
    //     //     if (hyponym.containsKey(i)) {
    //     //         intArray.addAll(hyponym.get(i));
    //     //     }
    //     // }
    //     ArrayList<String> strArray2 = new ArrayList<String>();
    //     for (Integer j : intArray) {
    //         strArray2.addAll(synset.get(j));
    //     }
    //     for (String str : strArray2) {
    //         hashset.add(str);
    //         hashset.addAll(hyponyms(str));
    //     }
    //     return hashset;
    // }

    // private ArrayList<Integer> getSynsetKeys(String word) { 
    //     // Set<ArrayList<String>> kset = synsetRev.keySet();
    //     // HashSet<String> ks = new HashSet<String>();
    //     ArrayList<Integer> al = new ArrayList<Integer>(); 

    //     for (ArrayList<String> o : synsetRev.keySet()) {
    //         for (String p : o) {
    //             if (p.equals(word)) {
    //                 al.add(synsetRev.get(o));
    //             }
    //         }
    //     }
    //     return al;
    // }

}
