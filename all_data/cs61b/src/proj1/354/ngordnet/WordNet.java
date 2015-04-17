package ngordnet;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class WordNet    {
    private HashMap<String, ArrayList<Synset>> wordToSynset;
    private ArrayList<Synset> indexToSynset;
    private Digraph hyponymGraph;
    private TreeSet<Synset> synsets;
    public WordNet(String synsetFilename, String hyponymFilename)    {
        try {
            Scanner synsetScanner = new Scanner(new File(synsetFilename));
            Scanner hyponymScanner = new Scanner(new File(hyponymFilename));            
            
            wordToSynset = new HashMap<String, ArrayList<Synset>>();
            indexToSynset = new ArrayList<Synset>();
            synsets = new TreeSet<Synset>();
            
            String[] line;
            Synset tempSynset;
            while (synsetScanner.hasNext()) {
                line = synsetScanner.nextLine().split(",");
                tempSynset = new Synset(Integer.parseInt(line[0]));
                for (String word : line[1].split(" ")) {
                    tempSynset.add(word);
                    if (!(wordToSynset.containsKey(word)))  {
                        wordToSynset.put(word, new ArrayList<Synset>());
                    }
                }
                for (String word : line[1].split(" "))  {
                    wordToSynset.get(word).add(tempSynset);
                }
                indexToSynset.add(tempSynset);
                synsets.add(tempSynset);
            }
            
            hyponymGraph = new Digraph(indexToSynset.size());
            while (hyponymScanner.hasNext())    {
                line = hyponymScanner.nextLine().split(",");
                for (int i = 0; i < line.length; i++)   {
                    hyponymGraph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
                }
            }
            
            synsetScanner.close();
            hyponymScanner.close();
            
        } catch (FileNotFoundException e)    {
            System.out.println("Could not find file!");
        }
        
    }
    
    public boolean isNoun(String noun)  {
        return wordToSynset.containsKey(noun);
    }
    
    public Set<String> hyponyms(String word)    {        
        TreeSet<Integer> indexes = new TreeSet<Integer>();
        for (Synset wordSynset : wordToSynset.get(word))   {
            indexes.add(wordSynset.getID());
        }
        indexes.addAll(GraphHelper.descendants(hyponymGraph, indexes));
        
        TreeSet<String> output = new TreeSet<String>();
        for (Integer index : indexes)   {
            output.addAll(indexToSynset.get(index).getWords());
        }
        
        
        return output;
    }
    
    public Set<String> nouns()  {
        return wordToSynset.keySet();
    }
    private class Synset implements Comparable {
        private TreeSet<String> words = new TreeSet<String>();
        private int ID;
        public Synset(int id) {
            this.ID = id;
        }
        public void add(String word)    {
            words.add(word);
        }
        public TreeSet getWords()   {
            return words;
        }
        public int getID()  {
            return ID;
        }
        public int compareTo(Object S)  {
            return 1;
        }
    }
    
}
