//ISSUE: Supposed to take ~5s to read synset.txt and hyponym.txt

package ngordnet;

//import java.lang.String;
import java.util.Set;

import java.util.HashSet;
import java.util.HashMap;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private HashMap<String, HashSet<Integer>> nounToSynsets;  //maps nouns to synset #s
    private HashMap<Integer, Integer[]> hyponyms; //maps synset # to hyponyms #s
    private HashMap<Integer, String[]> synsetToNouns; //maps synset # to nouns contained

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        File synsetFile = new File(synsetFilename);
        File hyponymFile = new File(hyponymFilename);
        readSynsetFile(synsetFile);
        readHyponymFile(hyponymFile);
    }

    /** Initializes and fills out nounToSynsets and synsetToNouns **/
    private void readSynsetFile(File synsetFile) {
        nounToSynsets = new HashMap<String, HashSet<Integer>>();
        synsetToNouns = new HashMap<Integer, String[]>();

        Scanner synsetRead;

        try {
            synsetRead = new Scanner(synsetFile);
        } catch (FileNotFoundException E) {
            throw new RuntimeException("Synset file not found! Oh noes.");
        }            

//        These are temp variables used in the loop to read synsetFilename.
        int synsetNum; //Each synset # corresponding to that line.
        HashSet<Integer> synsets;
        String[] numNounsDef = new String[2];
        String[] nouns;

        while (synsetRead.hasNextLine()) {

            numNounsDef = synsetRead.nextLine().split(",");
            nouns = numNounsDef[1].split(" ");
            synsetNum = Integer.parseInt(numNounsDef[0]);

            for (String noun : nouns) {
                if (nounToSynsets.containsKey(noun)) {
                    nounToSynsets.get(noun).add(synsetNum);
                } else {
                    synsets = new HashSet<Integer>();
                    synsets.add(synsetNum);
                    nounToSynsets.put(noun, synsets);
                }            
            }

            synsetToNouns.put(synsetNum, nouns);
        }
    }

    /** Initializes and fills out hyponyms**/
    private void readHyponymFile(File hyponymFile) {
        hyponyms = new HashMap<Integer, Integer[]>();

        Scanner hyponymRead;
        try {
            hyponymRead = new Scanner(hyponymFile);
        } catch (FileNotFoundException E) {
            throw new RuntimeException("Hyponym file not found! Oh noes.");
        }

//        These are temp variables for reading hyponymFilename.
        String[] sSelfHyponyms;
        int self;
        Integer[] iHyponyms;

        while (hyponymRead.hasNextLine()) {

//            next_Line = hyponymRead.nextLine();
//            lineReader = new Scanner(next_Line).useDelimiter(",");
            sSelfHyponyms = hyponymRead.nextLine().split(",");

            //What would happen if I considered the synset #s Strings?!
            //Then I wouldn't have to parse everything...
            self = Integer.parseInt(sSelfHyponyms[0]);
            iHyponyms = new Integer[sSelfHyponyms.length - 1];

            for (int i = 1; i < sSelfHyponyms.length; i++) {
                iHyponyms[i - 1] = Integer.parseInt(sSelfHyponyms[i]);
            }
///            synsetNum = lineReader.nextInt();
            if (hyponyms.containsKey(self)) {
                Integer[] oldHyponyms = hyponyms.get(self);
                Integer[] newHyponyms = iHyponyms;
                iHyponyms = new Integer[oldHyponyms.length + newHyponyms.length];
                for (int i = 0; i < oldHyponyms.length; i++) {
                    iHyponyms[i] = oldHyponyms[i];
                }
                for (int i = 0; i < newHyponyms.length; i++) {
                    iHyponyms[oldHyponyms.length + i] = newHyponyms[i];
                }
            }
            hyponyms.put(self, iHyponyms);
/*            currHyponyms = new HashSet<Integer>();
            currHyponyms.add(synsetNum);
            while (lineReader.hasNext()) {
                currHyponyms.add(lineReader.nextInt());
            }
            hyponyms.put(synsetNum, currHyponyms);*/
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounToSynsets.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounToSynsets.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        HashSet<String> toReturn = new HashSet<String>();

        //The synsets that that noun is part of.
        HashSet<Integer> synsetNums = nounToSynsets.get(word);
        if (synsetNums == null) {
            return toReturn;
        }
        //For every synset that that noun is part of...
        for (Integer synset : synsetNums) {
            toReturn.addAll(hyponymsFromNum(synset));
        }
        return toReturn;
    }

    /*public boolean hasDirectHyponyms(Integer synset) {
        return hyponyms.containsKey(synset);
    }*/

    private Set<String> hyponymsFromNum(Integer synset) {

        HashSet<String> toReturn = new HashSet<String>();

        //The hyponyms that this synset has.
        Integer[] hypoNums = hyponyms.get(synset);
        //For every hyponym that this synset has...

        //Add the nouns in this synset to the returned list of "hyponyms."
        String[] synonyms = synsetToNouns.get(synset);
        for (String synonym : synonyms) {
            toReturn.add(synonym);
        }

        if (hypoNums != null) {

            for (Integer h : hypoNums) {
                //The nouns in the synset of the hyponym.
                String[] hNouns = synsetToNouns.get(h);                
                //For every noun in the hyponym synset.
                for (String hNoun : hNouns) {
                    toReturn.add(hNoun);
                }                

                //Also need to add the hyponyms of h to "toReturn" recursively.
                if (hyponyms.containsKey(h)) {
                    Set<String> grandhyponyms = hyponymsFromNum(h);
    /*                for (String grandH in grandhyponyms) {
                        toReturn.add(grandH);
                    }*/
                    toReturn.addAll(grandhyponyms);
                }
            }
        }


        return toReturn;
    }
}
