package ngordnet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Tushar on 3/4/2015.
 */

public class WordNet {
    private BufferedReader synin;
    private BufferedReader hypin;

    /**
     * An <b>array list</b> containing <i>string</i> arrays of the
     * words in subset synset.
     */
    private ArrayList<String[]> synwords;

    /**
     * An array list of definitions of syns.
     */
    private ArrayList<String> syndefs;

    /**
     * An array list specifying the hyponym maps.
     */
    private HashMap<Integer, ArrayList<Integer>> hypmap;

    public WordNet(String syn, String hyp) {
        synwords = new ArrayList<String[]>();
        syndefs = new ArrayList<String>();
        hypmap = new HashMap<Integer, ArrayList<Integer>>();

        try {
            // Read syns
            synin = new BufferedReader(new FileReader(syn));
            String synline = synin.readLine();
            while (synline != null) {
                parsesyn(synline);
                synline = synin.readLine();
            }
            synin.close();

            // Read hyps
            hypin = new BufferedReader(new FileReader(hyp));
            String hypline = hypin.readLine();
            while (hypline != null) {
                parsehyp(hypline);
                hypline = hypin.readLine();
            }
            hypin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <strong>Will parse syns.</strong> <ul> <li>Split each line into
     * an array with values in between commas.</li> <li>If there are
     * not 3 commas, prints a notification of a badly formatted
     * line.</li> <li>Split the second element of the array by spaces,
     * creating an array of syn words.</li> <ul><li>Add second element
     * array to synwords.</li></ul> <li>The last element in the array
     * is the definition, added to syndefs.</li> </ul>
     *
     * @param currentLine The input line from BufferedReader.
     */
    private void parsesyn(String currentLine) {
        String[] words = currentLine.split(",", 3);

        if (words.length != 3) {
            System.out.println("Bad syn line: " + currentLine);
        } else {
            synwords.add(words[1].split(" "));
            syndefs.add(words[2]);
        }
    }

    /**
     * <strong>Will parse hyponyms.</strong> <ul> <li>Split input line
     * by commas, into an array.</li> <li>If there are less than 2
     * values, notify of a badly formatted line. Stop at this point.
     * </li> <li>Create an array that is one <i>less</i> than the
     * array of id's.</li> <li>For every int in the new array, parse
     * an int from the id's array. </li> <li>Add these to the hypmap
     * array. Creates a structure of int(word ID) int[hyponyms of
     * word] for every hyponym.</li> </ul>
     *
     * @param currentLine The input line from BufferedReader.
     */
    private void parsehyp(String currentLine) {
        String[] ids = currentLine.split(",");

        if (ids.length < 2) {
            System.out.println("Bad hyp line " + currentLine);
        } else {

            int main = Integer.parseInt(ids[0]);

            ArrayList<Integer> values = new ArrayList<Integer>();

            for (int i = 1; i < ids.length; i++) {
                values.add(Integer.parseInt(ids[i]));
            }

            if (!hypmap.containsKey(main)) {
                hypmap.put(main, values);
            } else {
                values.addAll(hypmap.get(main));
                hypmap.put(main, values);
            }


        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (findSyndex(noun).size() != 0);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() { //broken?
        Set<String> stringSet = new HashSet<String>();

        for (String[] i : synwords) {
            stringSet.addAll(Arrays.asList(i));
        }

        return stringSet;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms
     * of WORD. If WORD belongs to multiple synsets, return all
     * hyponyms of all of these synsets. See http://goo.gl/EGLoys for
     * an example. Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // Find index of word in synsets.txt using findSyndex().
        ArrayList<Integer> indeces = findSyndex(word);

        // Store all relevant word indeces in a list.
        ArrayList<Integer> hypnums = new ArrayList<Integer>();

        // A list of all relevant words. Last thing.
        Set<String> hypset = new HashSet<String>();

        // Find all relevant words.
        for (int i : indeces) {
            // Add indeces of word in synlist to hypnums
            hypnums.add(i);

            // Go through hypmap to get indeces of hyponyms of word
            // in synlist. Add to hypnum.
            hypnums.addAll(hypnumGetter(i));
        }

        // Using list of all relevant words, find the list of syns
        // of each word using synwords.get(int i). Cycle through
        // this list of words, adding each one to hypset, the
        // return list of all found words.
        for (int i : hypnums) {
            for (String w : synwords.get(i)) {
                hypset.add(w);
            }
        }

        return hypset;
    }

    /**
     * Will get all hypnums by recursively going through everything in
     * hyponyms.txt.
     *
     * @param i The index to initially look for.
     * @return
     */
    private ArrayList<Integer> hypnumGetter(int i) {
        ArrayList<Integer> hypnums = new ArrayList<Integer>();
        hypnums.add(i);

        if (hypmap.get(i) != null) {
            for (int w : hypmap.get(i)) {
                hypnums.addAll(hypnumGetter(w));
            }

        }

        return (hypnums);
    }

    /**
     * Return the index of the word, if it exists. Return null
     * otherwise.
     *
     * @param s The word being searched for.
     * @return The index of the searched-for word.
     */
    private ArrayList<Integer> findSyndex(String s) {
        ArrayList<Integer> indexList = new ArrayList<Integer>();

        int index = 0;
        for (String[] i : synwords) {
            for (String w : i) {
                if (w.equals(s)) {
                    indexList.add(index);
                }
            }

            index++;
        }

        return indexList;
    }
}
