// Author: Justin P. CHen
// Login: cs61b-aqg

package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private HashMap<Integer, String> synhashmap = new HashMap<Integer, String>();
    private HashSet<String> allnouns = new HashSet<String>();
    private Digraph dg;

    public WordNet(String synsetFilename, String hyponymFilename) {

        /* Read in file line-by-line, split based by commmas, 
        then map each synsetID to its synset. */
        In synsetfile = new In(synsetFilename);
        while (synsetfile.hasNextLine()) {
            String synline = synsetfile.readLine();
            String[] synlineparsed = synline.split(",");
            int synID = Integer.parseInt(synlineparsed[0]);
            String synIDsynset = synlineparsed[1];
            synhashmap.put(synID, synIDsynset);
        }

        dg = new Digraph(synhashmap.size());
        /* Read in file line-by-line, split based by commmas, 
        then turn the String array into an int array. */
        In hyponymfile = new In(hyponymFilename);
        while (hyponymfile.hasNextLine()) {
            String hypline = hyponymfile.readLine();
            String[] hyplineparsed = hypline.split(",");
            int hyplinelength = hyplineparsed.length;
            int[] hypIDarray = new int[hyplinelength];
            for (int i = 0; i < hyplinelength; i += 1) {
                hypIDarray[i] = Integer.parseInt(hyplineparsed[i]);
            }


            // Add edges for the Digraph.
            for (int i = 1; i < hyplinelength; i += 1) {
                dg.addEdge(hypIDarray[0], hypIDarray[i]);
            }

        }

        if (synhashmap.isEmpty()) {
            throw new NullPointerException("Empty file.");
        }
        nouns();

    }

    public boolean isNoun(String noun) {
        return allnouns.contains(noun);
    }

    public Set<String> nouns() {
        for (String eachsynset : synhashmap.values()) {
            String[] splitsynset = eachsynset.split("\\s+");
            for (int i = 0; i < splitsynset.length; i += 1) {
                allnouns.add(splitsynset[i]);
            }
        }
        return allnouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> allhyponyms = new HashSet<String>();
        int foundID = 0;
        if (isNoun(word)) {
            for (int wordID : synhashmap.keySet()) {
                String synsetassID = synhashmap.get(wordID);
                String[] synsetassIDarray = synsetassID.split("\\s+");
                for (int k = 0; k < synsetassIDarray.length; k += 1) {
                    if (synsetassIDarray[k].equals(word)) {
                        foundID = wordID;
                        Set<Integer> wordsetIDs  = new TreeSet<Integer>();
                        wordsetIDs.add(foundID);       
                        Set<Integer> hyponymintarray = GraphHelper.descendants(dg, wordsetIDs);
                        for (Integer i : hyponymintarray) {
                            String[] eachsynsetarray = synhashmap.get(i).split("\\s+");
                            for (int j = 0; j < eachsynsetarray.length; j += 1) {
                                allhyponyms.add(eachsynsetarray[j]);
                            }
                        }
                    }
                }
            }
        }

        return allhyponyms;
    }
}
