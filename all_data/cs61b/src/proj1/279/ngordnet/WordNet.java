package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private ArrayList<HashSet<String>> synListSet = new ArrayList<HashSet<String>>();
    private HashSet<String> nounSet = new HashSet<String>();
    private Digraph hypGraph;
    private int N = 0;

    /* Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Populate a List using the synset file. The synset ID is the index of the node.
          * Each node points to a HashSet of hyponyms.
          * While the List is being populated, create a HashSet of nouns.
          */
        try {
            Scanner input = new Scanner(new File(synsetFilename));
            Scanner firstPass = new Scanner(new File(hyponymFilename));
            Scanner secondPass = new Scanner(new File(hyponymFilename));

            /** Parse synset file. */
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineParts = line.split(",");
                HashSet<String> lineSet = new HashSet<String>();
                for (String elem : lineParts) {
                    if (elem == lineParts[1]) {
                        String[] words = elem.split(" ");
                        for (String word: words) {
                            lineSet.add(word);
                        }
                    }
                }
                synListSet.add(Integer.parseInt(lineParts[0]), lineSet);
                nounSet.addAll(lineSet);
            }

            /** Find the number of nodes in a digraph */
            while (firstPass.hasNextLine()) {
                String line = firstPass.nextLine();
                String[] lineParts = line.split(",");
                for (String s: lineParts) {
                    if (Integer.parseInt(s) > N) {
                        N = Integer.parseInt(s);
                    }
                }
            }

            /** Add hyponym data to digraph. */
            hypGraph = new Digraph(N + 1);
            while (secondPass.hasNextLine()) {
                String line = secondPass.nextLine();
                String[] lineParts = line.split(",");
                for (int i = 1; i < lineParts.length; i++) {
                    hypGraph.addEdge(Integer.parseInt(lineParts[0]), 
                        Integer.parseInt(lineParts[i]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("One of the input files was not read.");
            return;
        }    
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hypSet = new HashSet<String>();
        HashSet<Integer> markedNodes = new HashSet<Integer>();
        if (!isNoun(word)) {
            return null;
        } else {
            for (int i = 0; i < N + 1; i += 1) {
                if (synListSet.get(i).contains(word)) {
                    markedNodes.add(i);
                }
            }
            for (int i : GraphHelper.descendants(hypGraph, markedNodes)) {
                hypSet.addAll(synListSet.get(i));
            }
        }
        return hypSet;
    }
}       

