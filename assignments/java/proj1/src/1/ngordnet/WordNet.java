package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.Set;

import java.util.Scanner;

public class WordNet {

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private Hashtable<String, TreeSet<Integer>> wordstonum;
    private Hashtable<Integer, TreeSet<String>> numtowords;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            File sy = new File(synsetFilename);
            File hy = new File(hyponymFilename);
            Scanner temp = new Scanner(sy).useDelimiter(",|\\s*");
            // for counting the length
            Scanner synset = new Scanner(sy).useDelimiter(",|\\s*");
            Scanner hypony = new Scanner(hy).useDelimiter(",|\\s*");
            Scanner temp2 = new Scanner(hy).useDelimiter(",|\\s*");
            // counting for Digraph

            // ******************* SYNSET ******************************
            wordstonum = new Hashtable<String, TreeSet<Integer>>();
            numtowords = new Hashtable<Integer, TreeSet<String>>();
            while (synset.hasNextLine()) {
                Scanner synsetline = new Scanner(synset.nextLine())
                        .useDelimiter(",");
                int addnum = synsetline.nextInt();

                TreeSet<String> addwordset = new TreeSet<String>();

                Scanner canbemanywords = new Scanner(synsetline.next());
                // IMPORTANT if multiple words in a block , break them
                // seperately
                // loop over words
                while (canbemanywords.hasNext()) {
                    String addword = canbemanywords.next();
                    if (wordstonum.containsKey(addword)) {
                        wordstonum.get(addword).add(addnum);
                    } else {
                        TreeSet<Integer> addnums = new TreeSet<Integer>();
                        addnums.add(addnum);
                        wordstonum.put(addword, addnums);

                    }

                    if (numtowords.containsKey(addnum)) {
                        numtowords.get(addnum).add(addword);
                    } else {
                        TreeSet<String> addwords = new TreeSet<String>();
                        addwords.add(addword);
                        numtowords.put(addnum, addwords);
                    }
                }

                // throw each thing to their corresponding position

            }
            // ********************* HYPONY ***************************
            // first count the space needed

            int i3 = 0;
            while (temp2.hasNextInt()) {
                i3++;
                temp2.nextInt();
            }

            g = new Digraph(i3);
            // add linke to the number by calling g.addedge

            while (hypony.hasNextLine()) {
                Scanner hyponyline = new Scanner(hypony.nextLine())
                        .useDelimiter(",|\\s");
                int head = hyponyline.nextInt();
                while (hyponyline.hasNextInt()) {
                    int next = hyponyline.nextInt();
                    g.addEdge(head, next);

                }
            }
        } catch (FileNotFoundException n) {
            return;
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        if (wordstonum.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        return wordstonum.keySet();

    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> inputnumbers = wordstonum.get(word);

        Set<Integer> outputnumbers = GraphHelper.descendants(g, inputnumbers);

        // ???
        Set<String> outputwords = new TreeSet<String>();
        for (Integer n : outputnumbers) {
            for (String s : numtowords.get(n)) {

                outputwords.add(s);

            }

        }

        return outputwords;
    }
}
