package ngordnet;

import java.util.Set;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.io.File;
import java.util.HashSet;
import java.io.FileNotFoundException;

public class WordNet {
    
    private ArrayList<String[]> words;
    private Digraph graph;

    public WordNet(String synsetFilename, String hypernymFilename) {
        readSynsetFile(synsetFilename);
        makeDiagraph(hypernymFilename);
    }

    private void readSynsetFile(String filepath) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            words = new ArrayList<String[]>();
            String line;
            String[] items;
            while (s.hasNextLine()) {
                line = s.nextLine();
                items = line.split(",");
                words.add(items[1].split(" "));
            }
        } catch (FileNotFoundException f) {
            System.out.println("synset file not found");
        }
    }

    private void makeDiagraph(String filepath) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            String line;
            int hyper;
            String[] items;
            graph = new Digraph(words.size());
            while (s.hasNextLine()) {
                line = s.nextLine();
                items = line.split(",");
                hyper = Integer.parseInt(items[0]);
                for (int i = 1; i < items.length; i += 1) {
                    graph.addEdge(hyper, Integer.parseInt(items[i]));
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("hypernym file not found");
        }
    }

    public boolean isNoun(String noun) {
        for (String[] arr: words) {
            for (int i = 0; i < arr.length; i += 1) {
                if (arr[i].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (String[] arr : words) {
            for (String w : arr) {
                nounSet.add(w);
            }
        }       
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyp = new HashSet<String>();
        
        Set<Integer> synsetIDs = new HashSet<Integer>();
        
        //find the sysnset index
        for (int aListIndex = 0; aListIndex < words.size(); aListIndex += 1) {
            String[] synset = words.get(aListIndex);
            for (String syn : synset) {
                if (syn.equals(word)) {
                    synsetIDs.add(aListIndex);
                }
            }
        }

        //get hyponyms
        Set<Integer> descendants = GraphHelper.descendants(graph, synsetIDs);
        descendants.addAll(synsetIDs);

        for (int i : descendants) {
            String[] arr = words.get(i);
            for (String w : arr) {
                hyp.add(w);
            }
        }
        return hyp;
    }
}
