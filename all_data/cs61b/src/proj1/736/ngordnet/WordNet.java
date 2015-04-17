package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeMap;

import java.util.TreeSet;
import java.util.ArrayList;


public class WordNet {
    private TreeMap<String, Integer> synset;
    private Digraph hyponym;        
    private ArrayList<Entry> array;

    //Creates a WordNet object using the files from given 'synsetFileName' and 'hyponym Filename'
    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        synset = new TreeMap<String, Integer>();
        In ins = new In(synsetFilename);
        array = new ArrayList<Entry>();

        while (!(ins.isEmpty())) {
            String s = ins.readLine();
            String[] tokens = s.split(",");
            int id = Integer.parseInt(tokens[0]);

            String[] tokenArray = tokens[1].split(" ");
            for (String t: tokenArray) {
                synset.put(t, id);
                array.add(new Entry(id, t));
            }
        }

        In hyp = new In(hyponymFilename);
        In hyp2 = new In(hyponymFilename);
        int count = 0;
        while (hyp.hasNextLine()) {
            String p = hyp.readLine();
            String[] splitP = p.split(",");
            count += splitP.length;
        }

        hyponym = new Digraph(count);
        while (hyp2.hasNextLine()) {
            String specLine = hyp2.readLine();
            String[] line = specLine.split(",");
            int int1 = Integer.parseInt(line[0]);
            for (int x = 1; x < line.length; x += 1) {
                int int2 = Integer.parseInt(line[x]);
                hyponym.addEdge(int1, int2);
            }
        }
    }

    //Returns whether or not the given word is a noun
    public boolean isNoun(java.lang.String noun) {
        return synset.containsKey(noun);

    }

    //Returns a set of strings, namely the nouns in the WordNet files that were read
    public Set<String> nouns() {
        return synset.keySet();

    }

    //Returns the set of all hyponyms and synonyms of word. If a word belongs to multiple synsets,
    //return all hyponyms of those synsets.
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new TreeSet<String>();
        Set<Integer> tree = new TreeSet<Integer>();
        ArrayList<Integer> allIds = new ArrayList<Integer>();
        for (int n = 0; n < array.size(); n += 1) {
            if (array.get(n).second.equals(word)) {
                allIds.add(array.get(n).first);
            }
        }
        for (Integer t : allIds) {
            tree.add(t);
        }
        Set<Integer> graph = GraphHelper.descendants(hyponym, tree);
        for (Integer number : graph) { 
            for (int n = 0; n < array.size(); n += 1) {
                if (array.get(n).first == number) {
                    toReturn.add(array.get(n).second);
                }
            }
        }     
        return toReturn;
    }
}
