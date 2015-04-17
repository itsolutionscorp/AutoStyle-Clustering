package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

public class WordNet extends Object {

    //want a digraph to keep track of synsets, but digraph uses ints
    //want a map to associate ints to their bucket of words
    //want set to contain my bucket of words
    private Digraph connections;
    private HashMap<Integer, Synset> conndict;
    private int synsetcount; 

    //Creates a WordNet using files from synsetfilename and hyponymfilename
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        conndict = new HashMap<Integer, Synset>();
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            synread(line);
        }
        connections = new Digraph(synsetcount);
        In hyponym = new In(hyponymFilename);
        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            hypread(line);
        }

    }

    private void hypread(String line) {
        Scanner sc = new Scanner(line).useDelimiter(",");
        int hypernym = sc.nextInt();
        while (sc.hasNext()) {
            int hyponym = sc.nextInt();

            connections.addEdge(hypernym, hyponym);
        }
    }

    private void synread(String line) {
        Scanner sc = new Scanner(line).useDelimiter(",");
        int id = sc.nextInt();
        String words = sc.next();
        Scanner wor = new Scanner(words);
        Set<String> bank = new HashSet<String>();
        String def = sc.next();
        while (wor.hasNext()) {
            bank.add(wor.next());
        }
        conndict.put(id, new Synset(id, bank, def));
        synsetcount += 1;
    }
    //returns the set of all hyponyms of word as well as all synonyms
    //needs to iterate through map, look into the set of each, and find word
    //returns its adj's and synonyms
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new TreeSet<Integer>();
        if (isNoun(word)) {
            Set<Integer> keys = conndict.keySet();
            for (Integer key : keys) {
                Synset curr = conndict.get(key);
                if (curr.wordbank.contains(word)) {
                    ids.add(curr.id);
                }
            }
        }
        return getadjandsyn(ids);
    }

    private Set<String> getadjandsyn(Set<Integer> ids) {
        Set<String> result = new HashSet<String>();
        Set<Integer> synsets = GraphHelper.descendants(connections, ids);
        for (Integer setid : synsets) {
            Set<String> syn = conndict.get(setid).wordbank;
            for (String each : syn) {
                result.add(each);
            } 
        } 

        return result;
    } 

    public boolean isNoun(String noun) {
        Set<Integer> keys = conndict.keySet();
        for (Integer key : keys) {
            Synset curr = conndict.get(key);
            if (curr.wordbank.contains(noun)) {
                return true;
            }
        } 
        return false;
    } 

    public Set<String> nouns() {
        Set<String> result = new HashSet<String>();
        Set<Integer> keyset = conndict.keySet();
        for (Integer each : keyset) {              
            Set<String> bank = conndict.get(each).wordbank;
            for (String word : bank) {
                result.add(word);
            }
        }
        return result;
    }

    private class Synset {
        private int id;
        private Set<String> wordbank;
        private String definition;

        private Synset(int identity, Set<String> wordbank2, String definition2) {
            this.id = identity;
            this.wordbank = wordbank2;
            this.definition = definition2;
        }
    }

}
