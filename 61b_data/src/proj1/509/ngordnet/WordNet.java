package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private Digraph digrf;

    private ArrayList<String[]> slines = new ArrayList<String[]>();
    private ArrayList<String[]> hlines = new ArrayList<String[]>();
    private HashMap<Integer, String> sdicIS = new HashMap<Integer, String>();
    private HashMap<Integer, String[]> sdicISA = new HashMap<Integer, String[]>();
    private HashMap<String, Integer> sdicSI = new HashMap<String, Integer>();
    private HashMap<String, ArrayList<Integer>> sdicSIA = new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<Integer>> hdic = new HashMap<Integer, ArrayList<Integer>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        File sfile = new File(synsetFilename);
        File hfile = new File(hyponymFilename);
        int hNI = 0;
        try {
            Scanner sF = new Scanner(sfile);
            // fills slines
            while (sF.hasNextLine()) {
                slines.add(sF.nextLine().split(","));
            }
            sF.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // makes two dictionaries
        for (String[] i : slines) {
            sdicIS.put(Integer.parseInt(i[0]), i[1]);
            sdicISA.put(Integer.parseInt(i[0]), i[1].split("\\s+"));
            sdicSI.put(i[1], Integer.parseInt(i[0]));
            for (String s : i[1].split("\\s+")) {
                if (sdicSIA.containsKey(s)) {
                    ArrayList<Integer> temp1 = sdicSIA.get(s);
                    temp1.add(Integer.parseInt(i[0]));
                    sdicSIA.put(s, temp1);
                } else {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(Integer.parseInt(i[0]));
                    sdicSIA.put(s, temp);
                }
            }
        }
        try {
            Scanner hF = new Scanner(hfile);
            while (hF.hasNextLine()) {
                hlines.add(hF.nextLine().split(","));
            }

            hF.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        for (String[] i : hlines) {
            hNI += i.length;
        }
        // makes new Digraph
        digrf = new Digraph(hNI - 1);
        for (String[] i : hlines) {
            int key = Integer.parseInt(i[0]);
            if (hdic.containsKey(key)) {
                ArrayList<Integer> temp = hdic.get(key);
                for (int j = 1; j < i.length; j += 1) {
                    // fills Digraph
                    digrf.addEdge(key, Integer.parseInt(i[j]));
                    // fills ArrayLists within hdic
                    temp.add(Integer.parseInt(i[j]));
                }
                hdic.put(key, temp);

            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int j = 1; j < i.length; j += 1) {
                    // fills Digraph
                    digrf.addEdge(key, Integer.parseInt(i[j]));
                    // fills ArrayLists within hdic
                    temp.add(Integer.parseInt(i[j]));
                }
                hdic.put(key, temp);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        int i = 0;
        for (String s : noun.split("\\s+")) {
            i += 1;
            if (i > 1) {
                return false;
            }
        }

        for (Integer key : sdicIS.keySet()) {
            if (sdicIS.get(key).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> tree = new TreeSet<String>();
        for (Integer key : sdicIS.keySet()) {
            for (String word : sdicIS.get(key).split("\\s+")) {
                tree.add(word);
            }
        }
        return tree;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        TreeSet<String> tree = new TreeSet<String>();
        tree.add(word);
        if (sdicSIA.containsKey(word)) {
            ArrayList<Integer> numbs = sdicSIA.get(word);
            for (Integer i : numbs) {
                // gets synonyms
                for (String s : sdicISA.get(i)) {
                    if (s != word) {
                        tree.add(s);
                    }
                }
                // gets locations of hymonyns
                if (hdic.containsKey(i)) {
                    ArrayList<Integer> numbs2 = hdic.get(i);
                    for (Integer j : numbs2) {
                        String[] hyms = sdicISA.get(j);
                        for (String t : hyms) {
                            tree.addAll(hyponyms(t));
                        }
                    }
                }
            }
        }
        return tree;
    }

}
