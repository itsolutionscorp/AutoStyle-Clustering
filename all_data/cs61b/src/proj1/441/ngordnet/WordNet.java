package ngordnet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private int N;

    // Keeps track of arrows.
    private Digraph dg;
    // Keeps track of which synset IDs each word is in (hashes a WORD to an ID).
    // Necessary because Digraph does not organize by words.
    private HashMap<String, LinkedList<Integer>> h;
    // Keeps track of which synset each ID points to (hashes an ID to a SYNSET).
    private HashMap<Integer, String> hId;

    public WordNet(String synsetFilenameIn, String hyponymFilenameIn) {
        this.N = getN(synsetFilenameIn);
        this.dg = new Digraph(this.N);
        this.h = new HashMap<String, LinkedList<Integer>>(this.N);
        this.hId = new HashMap<Integer, String>(this.N);

        // Digraph
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(hyponymFilenameIn));

            while ((line = fileReader.readLine()) != null) {
                String[] edges = line.split(",");
                for (int i = edges.length - 1; i > 0; i--) {
                    dg.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // hId, h
        fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(synsetFilenameIn));

            while ((line = fileReader.readLine()) != null) {
                String[] sepcomma = line.split(",");
                String[] sepspace = sepcomma[1].split(" ");
                int id = Integer.parseInt(sepcomma[0]);

                hId.put(id, sepcomma[1]);

                for (String element : sepspace) {
                    if (!h.containsKey(element)) {
                        LinkedList<Integer> newlist = new LinkedList<Integer>();
                        newlist.add(id);
                        h.put(element, newlist);
                    } else {
                        LinkedList<Integer> oldlist = h.get(element);
                        oldlist.add(id);
                        h.put(element, oldlist);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private int getN(String filename) {
        Scanner s;
        try {
            s = new Scanner(new File(filename));
            int count = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                count = count + 1;
            }
            return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isNoun(String noun) {
        return h.get(noun) != null;
    }

    public Set<String> nouns() {
        return h.keySet();
    }

    public Set<String> hyponyms(String word) {

        LinkedList<Integer> locList = (h.get(word));
        int listSize = locList.size();

        Set<Integer> locSet = new TreeSet<Integer>();
        for (int i = 0; i < listSize; i++) {
            locSet.add(locList.get(i));
        }

        TreeSet<Integer> hypIdSet = (TreeSet) GraphHelper.descendants(dg, locSet);
        Set<String> returnset = new TreeSet<String>();
        Iterator<Integer> iterator = hypIdSet.iterator();

        while (iterator.hasNext()) {
            String synset = hId.get(iterator.next());

            if (synset.split(" ").length == 1) {
                returnset.add(synset);
            } else {
                String[] sepspace = synset.split(" ");
                for (int j = 0; j < synset.split(" ").length; j++) {
                    returnset.add(sepspace[j]);
                }
            }
        }

        // Make sure returnset has word itself.
        if (returnset.contains(word)) {
            return returnset;
        } else {
            returnset.add(word);
            return returnset;
        }

    }

}
