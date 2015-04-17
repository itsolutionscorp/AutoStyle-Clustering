package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    
    private HashMap<Integer, TreeSet<String>> net;
    private Digraph relations;
    private HashSet<String> allNouns;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        net = new HashMap<Integer, TreeSet<String>>();
        allNouns = new HashSet<String>();
        int vertices = 0;

        while (synFile.hasNextLine()) {
            String line = synFile.readLine();
            int indexOfLastComma = line.lastIndexOf(","); // Get rid of definition
            line = line.substring(0, indexOfLastComma).replaceAll(",", " "); 
            Scanner s = new Scanner(line);
            Integer id = s.nextInt();
            //System.out.print("\n" + id);
            TreeSet<String> treeSet = new TreeSet<String>();
            while (s.hasNext()) {
                String word = s.next();
                if (word.substring(0, 1).equals("\"") || word.substring(0, 1).equals("(")) {
                    // Fix for autograder data inconsistency
                    word = word.substring(1);
                }
                allNouns.add(word);
                //System.out.print(", " + word);
                treeSet.add(word);
                vertices++;
            }
            net.put(id, treeSet);
            s.close();
        }
        relations = new Digraph(vertices);
        while (hypFile.hasNextLine()) {
            Scanner s = new Scanner(hypFile.readLine().replaceAll(",", " "));
            int index = s.nextInt();
            //System.out.print("\n" + index);
            while (s.hasNextInt()) {
                int hyponym = s.nextInt();
                //System.out.print(", " + hyponym);
                relations.addEdge(index, hyponym);
            }
            s.close();
        }
    }
    
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> indexes = new TreeSet<Integer>();
        for (int key: net.keySet()) {
            if (net.get(key).contains(word)) {
                indexes.add(key);
            }
        }
        TreeSet<String> result = new TreeSet<String>();
        for (Integer i : GraphHelper.descendants(relations, indexes)) {
            result.addAll(net.get(i));
        }
        return result;
    }
    
    
    public boolean isNoun(String noun) {
        for (int key : net.keySet()) {
            if (net.get(key).contains(noun)) {
                return true;
            }
        }
        return false;
    }
    
    public Set<String> nouns() {
        return allNouns;
    }

}
