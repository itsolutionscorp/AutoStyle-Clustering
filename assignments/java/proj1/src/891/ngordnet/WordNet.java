package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;


/** Fixed multiple occurance of words . 
 *  @author Bo Zeng
 */

public class WordNet {
    /** Returns the set of all vertex numbers reachable from the start vertices. */
    /* using set and directed graph */

    private HashMap<String, String> synsetMap;
    private HashMap<Integer, String> reversesynsetMap;
    private Digraph hypernymGraph;
    private int size;

    public WordNet(String synsetfile, String hypernymfile) {

        synsetMap = new HashMap<String, String>();
        reversesynsetMap = new HashMap<Integer, String>();
        size = 0;
        String eachline;
        int synid;
        String synidString;
        String synset;
        String[] components;
        String[] synsetelements;
        BufferedReader synsetin = null, hypernymin = null;
        try {
            synsetin = new BufferedReader(new FileReader(synsetfile));
            eachline = synsetin.readLine();
            while (eachline != null) {
                components = eachline.split(",");
                if (components.length < 2) {
                    throw new IllegalArgumentException("Input file format Error");
                }
                synidString = components[0];
                synid = Integer.parseInt(components[0]);
                synset = components[1];

                synsetelements = synset.split(" ");

                for (String each:synsetelements) {
                    if (synsetMap.containsKey(each)) {
                        synsetMap.put(each, synsetMap.get(each) + "," + synidString);
                    } else {
                        synsetMap.put(each, synidString);
                    }
                }
                reversesynsetMap.put((Integer) synid, synset);
            /* add to hashmap */
                size += 1;
                eachline = synsetin.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                synsetin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hypernymGraph = new Digraph(size);
        int parent;
        try {
            hypernymin = new BufferedReader(new FileReader(hypernymfile));
            eachline = hypernymin.readLine();
            while (eachline != null) {
                components = eachline.split(",");
                if (components.length < 2) {
                    throw new IllegalArgumentException("Input file format Error");
                }
                parent = Integer.parseInt(components[0]);
                for (int j = 1; j < components.length; j++) {
                    hypernymGraph.addEdge(parent, Integer.parseInt(components[j]));
                }
                eachline = hypernymin.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                hypernymin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isNoun(String key) {
        return synsetMap.containsKey(key);
    }

    public Set<String> nouns() {
        Set<String> allNouns = synsetMap.keySet();

        return allNouns;
    }

    public Set<String> hyponyms(String parent) {

        Set<Integer> node = new TreeSet<Integer>();

        TreeSet<Integer> children = new TreeSet<Integer>();

        String[] parentsID;

        if (!synsetMap.containsKey(parent)) {
            return new TreeSet<String>();
        }

        parentsID = synsetMap.get(parent).split(",");

        for (String eachID:parentsID) {
            node.add(Integer.parseInt(eachID));
        }

        children = (TreeSet<Integer>) GraphHelper.descendants(hypernymGraph, node);

        Set<String> result = new TreeSet<String>();

        String temp;
        String[] temps;

        for (Integer each: children) {
            temp = reversesynsetMap.get(each);
            temps = temp.split(" ");

            for (String eachsyn: temps) {
                result.add(eachsyn);
            }

        }

        return result;

    }
} 
