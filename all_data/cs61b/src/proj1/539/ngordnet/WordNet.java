package ngordnet;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<String, ArrayList<Integer>> wordToNums;
    private HashMap<Integer, String> numToWords;
    private Digraph numToHyponyms;

    //Constructor
    public WordNet(String synsetsFile, String hyponymsFile) {
        In s = new In(synsetsFile);
        String[] allSLines = s.readAllLines();
        numToHyponyms = new Digraph(allSLines.length);
        numToWords = new HashMap<Integer, String>(allSLines.length);
        wordToNums = new HashMap<String, ArrayList<Integer>>(allSLines.length);
        for (String line : allSLines) {
            String[] splitSLine = line.split(",");
            numToWords.put(Integer.parseInt(splitSLine[0]), splitSLine[1]);
            String[] splitSWords = splitSLine[1].split(" ");
            for (String word: splitSWords) {
                if (wordToNums.containsKey(word)) {
                    wordToNums.get(word).add(Integer.parseInt(splitSLine[0]));
                } else {
                    wordToNums.put(word, new ArrayList<Integer>());
                    wordToNums.get(word).add(Integer.parseInt(splitSLine[0]));
                }
            } 
        }
        In h = new In(hyponymsFile);
        String[] allHLines = h.readAllLines();
        for (String line: allHLines) {
            String[] splitHLine = line.split(",");
            for (int i = 1; i < splitHLine.length; i += 1) {
                Integer a = Integer.parseInt(splitHLine[0]);
                Integer b = Integer.parseInt(splitHLine[i]);
                numToHyponyms.addEdge(a, b);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> numsAList = wordToNums.get(word);
        Set<Integer> numsSet = new TreeSet<Integer>();
        for (Integer i : numsAList) {
            numsSet.add(i);
        }
        Set<Integer> hyponymsSet = GraphHelper.descendants(this.numToHyponyms, numsSet);
        String hyponymsString = "";
        for (Integer num : hyponymsSet) {
            hyponymsString += numToWords.get(num) + " ";
        }
        String[] hyponymsArray = hyponymsString.split(" ");
        Set<String> toReturn = new HashSet<String>();
        for (String s : hyponymsArray) {
            toReturn.add(s);
        }
        return toReturn;
    }

    public boolean isNoun(String word) {
        if (wordToNums.containsKey(word)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return wordToNums.keySet();
    }
}
