package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private Digraph d;
    private List<Synset> synsets;

    public WordNet(String synsetFileName, String hyponymFileName) {
        Scanner synsetScanner;
        try {
            synsetScanner = new Scanner(new File(synsetFileName));
        } catch (FileNotFoundException f) {
            System.out.println("Error: file not found.");
            return;

        } 
        
        synsets = new ArrayList<Synset>();
        while (synsetScanner.hasNextLine()) {
            String[] line = synsetScanner.nextLine().split(",");
            Set<String> currSet = new TreeSet<String>();
            String[] currWords = line[1].split(" ");
            for (int i = 0; i < currWords.length; i += 1) {
                currSet.add(currWords[i]);
            }
            synsets.add(Integer.parseInt(line[0]), new Synset(currSet));
        }

        d = new Digraph(synsets.size());
        Scanner hyponymScanner;
        try {
            hyponymScanner = new Scanner(new File(hyponymFileName));
        } catch (FileNotFoundException f) {
            System.out.println("Error: file not found.");
            return;
        }
        

        while (hyponymScanner.hasNextLine()) {
            String[] line = hyponymScanner.nextLine().split(",");
            int start = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i += 1) {
                int end = Integer.parseInt(line[i]);
                d.addEdge(start, end);
            }
        }

    }

    public boolean isNoun(String noun) {
        for (Synset s: synsets) {
            if (s.getWords().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> listOfWords = new TreeSet<String>();
        TreeSet<Integer> indexSet = new TreeSet<Integer>();
        for (int i = 0; i < synsets.size(); i += 1) {
            Synset currSynset = synsets.get(i);
            if (currSynset.getWords().contains(word)) {
                indexSet.add(i);
            }
        }
        Set<Integer> descendantSet = GraphHelper.descendants(d, indexSet);
        for (int i: descendantSet) {
            listOfWords.addAll(synsets.get(i).getWords());
        }
        return listOfWords;

    }

    public Set<String> nouns() {
        Set<String> set = new TreeSet<String>();
        for (Synset s: synsets) {
            set.addAll(s.getWords());
        }
        return set;
    }


    private class Synset {
        private Set<String> words;

        public Synset(Set<String> setOfWords) {
            this.words = setOfWords;
        }

        public Set<String> getWords() {
            return words;
        }


    }


}
