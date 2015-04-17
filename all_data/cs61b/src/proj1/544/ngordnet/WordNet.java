package ngordnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Digraph wordDigraph;
    private Map<Integer, ArrayList<Integer>> hyponyms = new HashMap<Integer, ArrayList<Integer>>();
    private Map<Integer, ArrayList<String>> synsets = new HashMap<Integer, ArrayList<String>>();
    private Set<Integer> vertices = new HashSet<Integer>();
    private Map<String, TreeSet<Integer>> nouns = new HashMap<String, TreeSet<Integer>>();

    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        File hyponym_file = new File(hyponymFilename);
        Scanner hyponym_file_Scanner = null;
        try {
            hyponym_file_Scanner = new Scanner(hyponym_file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        while (hyponym_file_Scanner.hasNextLine()) {
            String[] split_line = hyponym_file_Scanner.nextLine().split(",");
            ArrayList<Integer> int_line = new ArrayList<Integer>();
            for (Integer i = 0; i < split_line.length; i += 1) {
                int vertex = Integer.parseInt(split_line[i]);
                int_line.add(vertex);
                this.vertices.add(vertex);
            }
            int key = int_line.remove(0); // changes int_line to fit values in
            // Digraph.
            if (this.hyponyms.containsKey(key)){
                // ArrayList<Integer> int_line2 = int_line;
                // int_line = this.hyponyms.get(key).addAll(int_line2);
                int_line.addAll(this.hyponyms.get(key));
            }
            this.hyponyms.put(key, int_line);
        }

        // this.wordDigraph = new Digraph(numbers_hyponyms[numbers_hyponyms -
        // 1]);

        File synset_file = new File(synsetFilename);
        Scanner synset_file_Scanner = null;
        try {
            synset_file_Scanner = new Scanner(synset_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (synset_file_Scanner.hasNextLine()) {
            String[] split_line = synset_file_Scanner.nextLine().split(",");
            ArrayList<String> string_line = new ArrayList<String>();
            for (Integer i = 0; i < split_line.length; i += 1) {
                string_line.add(split_line[i]);
            }
            Integer key = Integer.parseInt(string_line.remove(0)); // changes
            // string_line to fit
            // values in Digraph

            this.synsets.put(key, string_line);
        }
        makeDigraph();
        nounsMaker();

    }

    private void makeDigraph() {
        this.wordDigraph = new Digraph(this.vertices.size());
        Set<Integer> keys = this.hyponyms.keySet();

        Iterator<Integer> key_iterator = keys.iterator();
        while (key_iterator.hasNext()) {
            Integer key_used = key_iterator.next();
            Iterator<Integer> value_iterator = this.hyponyms.get(key_used).iterator();
            while (value_iterator.hasNext()) {
                this.wordDigraph.addEdge(key_used, value_iterator.next());
            }
        }

    }

    private void nounsMaker() {

        Set<Integer> keys = this.synsets.keySet();

        Iterator<Integer> key_iterator = keys.iterator();
        while (key_iterator.hasNext()) {
            Integer key_used = key_iterator.next();
            String words = (String) this.synsets.get(key_used).toArray()[0];
            String[] split_words = words.split(" ");
            for (int i = 0; i < split_words.length; i += 1) {
                String used_word = split_words[i];
                if (this.nouns.containsKey(used_word) == false) {
                    this.nouns.put(used_word, new TreeSet<Integer>());
                }
                this.nouns.get(used_word).add(key_used);
            }

        }
    }

    public boolean isNoun(String werd) {
        return this.nouns.containsKey(werd);
    }

    public java.util.Set<java.lang.String> nouns() {
        return this.nouns.keySet();
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        if (isNoun(word)){
        Set<Integer> descendent_nums = GraphHelper.descendants(this.wordDigraph,
                this.nouns.get(word));
        Set<java.lang.String> returned = new HashSet<String>();
        Iterator<Integer> word_iterator = descendent_nums.iterator();
        while (word_iterator.hasNext()) {
            String some_words = (String) this.synsets.get(word_iterator.next()).toArray()[0];
            String[] returned_words = some_words.split(" ");
            for (int i = 0; i < returned_words.length; i += 1) {
                returned.add(returned_words[i]);
            }
        }
        return returned;

        }
        return new HashSet<java.lang.String>();
    }

}
