package ngordnet;
//import ngordnet.WordNet;
//import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

/** project 1
 *  @author Gautam Machiraju
 */

public class WordNet {
    HashMap<Integer, ArrayList<String>> sMap = new HashMap<Integer, ArrayList<String>>();
    HashMap<String, ArrayList> sMapByWord = new HashMap<String, ArrayList>();
    Digraph dig;
    int size;
    //ArrayList prevNums;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        int size = 0;
        
        //Creating Synset structure — the basis of the digraph (roots/parents)
        In inSyn = new In(new File(synsetFilename));
        while(inSyn.hasNextLine()) {
            String line = inSyn.readLine();
            String[] fields = line.split(","); 
            Integer id = Integer.parseInt(fields[0]);
            String[] synset = fields[1].split(" ");
            
            //-----creating inverse map-----------
            ArrayList nums = new ArrayList();
            nums.add(id);

            for (String el : synset) {
                if (sMapByWord.containsKey(el) == false) {
                    sMapByWord.put(el, nums);
                } else {
                    ArrayList prevNums = sMapByWord.get(el);
                    prevNums.add(id);
                    sMapByWord.put(el, prevNums);
                }   
            }
            //------------------------------------

            ArrayList<String> synset2 = new ArrayList<String>(Arrays.asList(synset));
            sMap.put(id, synset2);
            size = size + 1;
        }
        inSyn.close();

        //Creating Digraph based on synset structure/size
        Digraph dig = new Digraph(size);

        //Creating Hyponym structure — getting more specific (children)
        In inHyp = new In(new File(hyponymFilename));
        while (inHyp.hasNextLine()) {
            String line = inHyp.readLine();
            String[] fields = line.split(",");
            Integer synID = Integer.parseInt(fields[0]); //synset ID
            for (int i = 1; i < fields.length; i++) {
                Integer hypID = Integer.parseInt(fields[i]);
                dig.addEdge(synID, hypID);    //connecting elements
            }
        }
        inHyp.close();

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return sMapByWord.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // HashSet<String> nounSet = new HashSet<String>();
        // for (int i = 0; i < size; i++) {
        //     ArrayList<String> synsetList = sMap.get(i);
        //     for (String noun : synsetList) {
        //         //System.out.println(noun);
        //         nounSet.add(noun);
        //     }
        // }
        // return nounSet;
        return sMapByWord.keySet();
    }


    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> toRet = new HashSet<String>();
        ArrayList<String> curr = new ArrayList<String>();

        int mapSize = sMap.size();
        for (int i = 0; i < mapSize ; i++){
            if (sMap.get(i) != null) { //checking if syset has elements
                
                int synSize = sMap.get(i).size();
                for (int j = 0; j < synSize; j++) { //iterating through synset
                    String syn1 = sMap.get(i).get(j);
                    
                    if (syn1.equals(word)) {   //found location of word
                        Set<Integer> digVals = new HashSet<Integer>();
                        digVals.add(i);

                        Set<Integer> descendantIDs = GraphHelper.descendants(dig, digVals); //list of all hyponym ids
                        for (Integer id : descendantIDs) {
                            for (int k = 0; k < synSize; k++) {
                                String syn2 = sMap.get(id).get(k);
                                if (curr.contains(syn2) == false) {
                                    curr.add(syn2);
                                    toRet.add(syn2);
                                }
                            }
                        }

                        for (int l = 0; l < synSize; l++) {
                            String syn3 = sMap.get(i).get(l);
                            if (curr.contains(syn3) == false) {
                                curr.add(syn3);
                                toRet.add(syn3);
                            }
                        }
                    }
                }
            }
        }
        return toRet;
    }
}

