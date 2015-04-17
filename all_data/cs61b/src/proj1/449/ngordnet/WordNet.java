package ngordnet;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {

    private Map<String, List<Integer>> allNouns;
    private Map<Integer, List<String>> synsets;
    private Map<Integer, List<Integer>> hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        allNouns = new HashMap<String, List<Integer>>();
        synsets = new HashMap<Integer, List<String>>();
        hyponyms = new HashMap<Integer, List<Integer>>();
        try {
            read(synsetFilename, hyponymFilename);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
    }

    private void read(String synsetFilename, String hyponymFilename) throws FileNotFoundException {
        File synset = new File(synsetFilename);
        File hyponym = new File(hyponymFilename);

        /* reads from synset files and adds to data structures */
        Scanner synScan = new Scanner(synset);
        while (synScan.hasNextLine()) {
            String in = synScan.nextLine();
            String[] line = in.split(",");
            Integer wordCode = Integer.parseInt(line[0]);
            String[] lineSplitter = line[1].split(" ");
            List<String> syns = Arrays.asList(lineSplitter); //converts array to ArrayList 

            /** adds to the synsets map (only one "wordCode" Integer per synset) */
            synsets.put(wordCode, syns);

            /** adds keys to allNouns map; takes account of the fact that there
                * may be more than one "wordCode" Integer associated with each key */
            for (String synonym: syns) {
                List<Integer> addCode = new ArrayList<Integer>();
                if (allNouns.containsKey(synonym)) {
                    addCode = allNouns.get(synonym);
                }
                addCode.add(wordCode);
                allNouns.put(synonym, addCode);
            }
        }
        synScan.close();

        /* reads from hyponym files and adds to data structures */
        Scanner hypScan = new Scanner(hyponym);
        while (hypScan.hasNextLine()) {
            String in = hypScan.nextLine();
            String[] line = in.split(",");
            Integer wordCode = Integer.parseInt(line[0]);
            Integer[] lineSorter = new Integer[line.length - 1];
            for (int i = 1; i < line.length; i++) { //converts String array to Integer array
                lineSorter[i - 1] = Integer.parseInt(line[i]);
            }
            List<Integer> hyps = Arrays.asList(lineSorter); //converts array to ArrayList

            /** adds to the hyponyms map */
            if (hyponyms.containsKey(wordCode)) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.addAll(hyponyms.get(wordCode));
                temp.addAll(hyps);
                hyps = temp;
            }
            hyponyms.put(wordCode, hyps);
        }
        hypScan.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        if (allNouns != null) {
            return allNouns.keySet();
        }
        return null;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        //allCodes: saves all used word codes to prevent infinite loops
        Set<Integer> allCodes = new HashSet<Integer>(); 
        //hypSet: set of hyponyms to be returned
        Set<String> hypSet = new HashSet<String>(); 
        if (isNoun(word)) {
            List<Integer> synCodes = allNouns.get(word);
            /* while there are codes to be search for in the synset file, search... */
            while (synCodes != null && synCodes.size() > 0) {
                allCodes.addAll(synCodes); //saves codes currently being used 
                List<Integer> tempSynCodes = new ArrayList<Integer>();
                /* iterate through codes from synset file */
                for (Integer synCode: synCodes) {
                    hypSet.addAll(synsets.get(synCode)); //adds synonyms found 
                    List<Integer> hypCodes = hyponyms.get(synCode);
                    if (hypCodes != null) {
                        /* iterate through codes from hyponym file */
                        for (Integer hypCode: hypCodes) {
                            if (!allCodes.contains(hypCode)) {
                                tempSynCodes.add(hypCode);
                            }
                            hypSet.addAll(synsets.get(hypCode)); //adds hyponym synonyms found
                        }
                    }
                }
                synCodes = tempSynCodes;
            }   
        }           
        return hypSet;
    }
}
