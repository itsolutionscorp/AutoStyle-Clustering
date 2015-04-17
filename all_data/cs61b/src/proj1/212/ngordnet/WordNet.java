package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import java.util.TreeSet;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private TreeMap<String, Set<Integer>> nounToInt = new TreeMap<String, Set<Integer>>();
    private TreeMap<Integer, Set<String>> intToNoun = new TreeMap<Integer, Set<String>>();

    private String nouns;
    private TreeSet<String> hasNouns = new TreeSet<String>(); // used for synsets
    private TreeSet<Integer> hasIndices = new TreeSet<Integer>(); // used for hyponyms
    private Digraph connected;

    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            File synFile = new File(synsetFilename);
            File hypFile = new File(hyponymFilename);
            Scanner synScanner = new Scanner(synFile);

            /* This is for synsets */
            String line = "";
            String manyNouns = "";

            int counter1 = 0;
            while (synScanner.hasNextLine()) {
                Set<String> nounSet = new HashSet<String>();
                line = synScanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    Scanner characters = new Scanner(line).useDelimiter(", *");
                    counter1 = Integer.parseInt(characters.next());
                    nouns = characters.next();
                    Scanner spaceScanner = new Scanner(nouns);
                    while (spaceScanner.hasNext()) {
                        manyNouns = spaceScanner.next();
                        nounSet.add(manyNouns);
                        hasNouns.add(manyNouns);
                    }
                }
                intToNoun.put(counter1, nounSet); 
            }

            /* THIS IS FOR HYPONYMS */
            String hypLine = "";
            Scanner hypScanner = new Scanner(hypFile);
            int synCount = 0;
            int otherNums = 0;
            Set<Integer> synSetIndex = intToNoun.keySet();
            String synSetNum = "";

            connected = new Digraph(hasNouns.size());

            while (hypScanner.hasNextLine()) {
                Set<Integer> hypSet = new HashSet<Integer>();
                hypLine = hypScanner.nextLine();
                Scanner numbers = new Scanner(hypLine).useDelimiter(", *");
                synCount = Integer.parseInt(numbers.next());
                while (numbers.hasNext()) {
                    otherNums = Integer.parseInt(numbers.next());
                    hypSet.add(otherNums);
                    hasIndices.add(otherNums);
                    connected.addEdge(synCount, otherNums);
                }
                hasIndices.add(synCount);
                nounToInt.put(Integer.toString(synCount), hypSet); 
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e); //prints the error message. 
        } catch (NullPointerException e) {
            System.out.println("Null Pointer: ");
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> value = new TreeSet<Integer>();
        Integer wordIndex = 0;
        
        for (Integer number : intToNoun.keySet()) {
            if (intToNoun.get(number).contains(word)) {
                wordIndex = number;
                value.add(wordIndex);
            }
        }

        Set<Integer> digraphSet = new TreeSet<Integer>();
        Set<String> allNouns = new TreeSet<String>();
        digraphSet.addAll(GraphHelper.descendants(connected, value));
        for (Integer indices : intToNoun.keySet()) {
            for (Integer vertex : digraphSet) {
                if (indices.equals(vertex)) {
                    allNouns.addAll(intToNoun.get(vertex));
                }
            }
        }
        allNouns.addAll(intToNoun.get(wordIndex));
        return allNouns;
    }

    public boolean isNoun(String noun) {
        for (String words : hasNouns) {
            if (words.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return hasNouns;
    }
}
