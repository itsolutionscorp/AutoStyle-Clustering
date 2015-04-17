package ngordnet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;

public class WordNet  {
    private HashMap<Integer, ArrayList<String>> numWord = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<Integer>> bran = new HashMap<Integer, ArrayList<Integer>>();
    private Digraph g;

    public WordNet(String syn, String hyp) {
        In synset = new In(syn);
        In hyponyms = new In(hyp);
        int counter = 0;
 
        // Make numWord <Integer, ArrayList<String>>
        while (synset.hasNextLine()) {
            String temp = synset.readLine();
            String[] commaSplit = temp.split(",");
            ArrayList<String> stringList = new ArrayList<String>();
            String[] splitWords = commaSplit[1].split(" ");
            for (String s: splitWords) {
                stringList.add(s);
            }
            numWord.put(Integer.parseInt(commaSplit[0]), stringList);
        }
        
        // Make bran <Integer, ArrayList<Integer>>
        while (hyponyms.hasNextLine()) {
            String temp = hyponyms.readLine();
            String[] intSplit = temp.split(",");
            ArrayList<Integer> intList = new ArrayList<Integer>();
            for (String i: intSplit) {
                intList.add(Integer.parseInt(i));
            }
            if (bran.keySet().contains(Integer.parseInt(intSplit[0]))) {
                ArrayList<Integer> newIntList = bran.get(Integer.parseInt(intSplit[0]));
                bran.remove(Integer.parseInt(intSplit[0]));
                for (int j: intList) {
                    if (!newIntList.contains(j)) {
                        newIntList.add(j);
                    }
                }
                bran.put(Integer.parseInt(intSplit[0]), newIntList);
            } else {
                bran.put(Integer.parseInt(intSplit[0]), intList);
            }
        }

        // Count verticies
        for (int i: bran.keySet()) {
            for (int j: bran.get(i)) {
                counter += 1;
            }
        }

        // Make digraph
        g = new Digraph(counter);
        for (int i: bran.keySet()) {
            for (int j: bran.get(i)) {
                g.addEdge(i, j);
            }
        }
    }

    public boolean isNoun(String testString) {
        for (ArrayList a: numWord.values()) {
            if (a.contains(testString)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> temp = new TreeSet<String>();
        for (ArrayList<String> a: numWord.values()) {
            for (String s: a) {
                if (!temp.contains(s)) {
                    temp.add(s);
                }
            }
        }
        return temp;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> allNums = numWord.keySet();
        TreeSet<Integer> startingNums = new TreeSet<Integer>();

        for (int i: allNums) {
            if (numWord.get(i).contains(word)) {
                startingNums.add(i);
            }
        }
        Set<Integer> searchingNums = new TreeSet<Integer>();
        searchingNums.addAll(GraphHelper.descendants(g, startingNums));
        TreeSet<String> answer = new TreeSet<String>();

        for (int i: searchingNums) {
            answer.addAll(numWord.get(i));
        }
        return answer;
    }
}
