
package ngordnet;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    // instance variables
    private HashMap<String, HashSet<Integer>> wdToId;
    private HashMap<Integer, HashSet<String>> idToSs;
    private Digraph d;
    private final int GUESSSYNSETS = 80000;
    private final float BUFFACTOR = 0.75f;
    private final int ARCLISTSIZE = 20;
    private int wordCount;
    private int synsetCount;

    // constructor
    public WordNet(String sysnsetFileName, String hyponymFileName) {
        wdToId = new HashMap<String, HashSet<Integer>>(GUESSSYNSETS * 5, BUFFACTOR);
        idToSs = new HashMap<Integer, HashSet<String>>(GUESSSYNSETS, BUFFACTOR);
        // prepare files for reading
        try {
            File f1 = new File(sysnsetFileName);
            Scanner synScan = new Scanner(f1);
            synScan.useDelimiter(",");
            int currInt;
            HashSet<String> currNouns;
            // insert all words into RadixTree and TreeSet
            while (synScan.hasNextInt() && synScan.hasNextLine()) {
                currInt = synScan.nextInt(); // gets the int before the comma
                currNouns = nextSynSet(synScan); // Look at the method!
                for (String s : currNouns) {
                    if (wdToId.get(s) == null) {
                        HashSet<Integer> a  = new HashSet<Integer>(5);
                        a.add(currInt);
                        wdToId.put(s, a);
                    } else {
                        wdToId.get(s).add(currInt);
                    }
                    wordCount = wordCount + 1;
                }
                synsetCount = synsetCount + 1;
                idToSs.put(currInt, currNouns);
            }
            synScan.close();
        } catch (IOException ex) {
            System.out.println("Sysnset file not found");
        }
        try {
            File f2 = new File(hyponymFileName);
            Scanner hypScan = new Scanner(f2);
            ArrayList<Integer> arcs = new ArrayList<Integer>(ARCLISTSIZE);
            d = new Digraph(synsetCount);
            while (hypScan.hasNextLine()) {
                arcs = nextArcs(hypScan.nextLine());
                for (int i = 1; i < arcs.size(); i++) {
                    d.addEdge(arcs.get(0), arcs.get(i));
                }
                arcs.clear();
            }
            hypScan.close();
        } catch (IOException ex) {
            System.out.println("Hyponym file not found");
        }
    }

    private HashSet<String> nextSynSet(Scanner synScan) {
        HashSet<String> a = new HashSet<String>(); // toReturn
        String containsNouns = synScan.next(); // comma delimited, so only get nouns
        Scanner s1 = new Scanner(containsNouns); // white-space delimited
        while (s1.hasNext()) {
            a.add(s1.next());
        }
        synScan.nextLine(); // skips the rest of this line (definition) ignore return value
        return a; // should now be ready to read next int
    }

    private ArrayList<Integer> nextArcs(String line) {
        // stores first int in position zero (source)
        ArrayList<Integer> a = new ArrayList<Integer>();
        Scanner s1 = new Scanner(line);
        s1.useDelimiter(",");
        while (s1.hasNextInt()) {
            a.add(s1.nextInt());
        }
        return a;
    }

    // other methods
    public boolean isNoun(String s) {
        return (wdToId.get(s) != null);
    }

    public Set<String> nouns() {
        Set<String> toReturn = new HashSet<String>();
        if (wdToId.keySet() != null) {
            toReturn.addAll(wdToId.keySet());
        }
        return toReturn;
    }

    public Set<String> hyponyms(String s) {
        HashSet<String> toReturn = new HashSet<String>();
        HashSet<Integer> a = wdToId.get(s);
        if (a != null) {
            for (Integer i : GraphHelper.descendants(d, a)) {
                toReturn.addAll(idToSs.get(i));
            }
        }
        return toReturn;
    }
}
