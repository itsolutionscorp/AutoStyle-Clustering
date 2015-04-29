package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    private Hashtable<Integer, LinkedList<String>> dataTable;
    private Hashtable<String, LinkedList<Integer>> dictionaryTable;
    private Digraph wordDigraph;

    public WordNet(String synsetsPath, String hyponymsPath) {
        File hypo = new File(hyponymsPath);
        File syn = new File(synsetsPath);

        In hypoFile = new In(hypo);
        In synFile = new In(syn);
        String[] hypoStrings = hypoFile.readAllLines();
        String[] synStrings = synFile.readAllLines();
        int graphSize = synStrings.length;

        wordDigraph = new Digraph(graphSize);
        dataTable = new Hashtable<>();
        dictionaryTable = new Hashtable<>();
        for (int i = 0; i < hypoStrings.length; i += 1) {
            StringTokenizer tempStringTokenizer = new StringTokenizer(
                    hypoStrings[i], ",");
            if (!tempStringTokenizer.hasMoreTokens()) {
                continue;
            }

            String parent = tempStringTokenizer.nextToken();
            int parentToGraph = Integer.parseInt(parent);
            while (tempStringTokenizer.hasMoreTokens()) {
                String child = tempStringTokenizer.nextToken();
                int childToGraph = Integer.parseInt(child);
                wordDigraph.addEdge(parentToGraph, childToGraph);
            }
        }

        Set<Integer> five = new TreeSet<Integer>();
        five.add(5);

        for (int i = 0; i < synStrings.length; i += 1) {
            StringTokenizer tempStringTokenizer = new StringTokenizer(
                    synStrings[i], ",");
            if (!tempStringTokenizer.hasMoreTokens()) {
                continue;
            }

            int id = Integer.parseInt(tempStringTokenizer.nextToken());
            String midString = tempStringTokenizer.nextToken();
            StringTokenizer midStringTokenizer = new StringTokenizer(midString,
                    " ");
            LinkedList<String> midDataList = new LinkedList<>();
            while (midStringTokenizer.hasMoreElements()) {
                String wordInMid = midStringTokenizer.nextToken();
                midDataList.add(wordInMid);
                if (dictionaryTable.containsKey(wordInMid)) {
                    LinkedList<Integer> tempList = dictionaryTable
                            .get(wordInMid);
                    tempList.add(id);
                    dictionaryTable.put(wordInMid, tempList);
                } else {
                    LinkedList<Integer> idList = new LinkedList<Integer>();
                    idList.add(id);
                    dictionaryTable.put(wordInMid, idList);
                }
            }

            dataTable.put(id, midDataList);

        }
    }

    public boolean isNoun(String test) {
        if (test == null) {
            return false;
        } else if (dictionaryTable.get(test) != null) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> finalSet = new TreeSet<>();
        Enumeration<String> keys = dictionaryTable.keys();
        while (keys.hasMoreElements()) {
            finalSet.add(keys.nextElement());
        }

        return finalSet;
    }

    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return new TreeSet<>();
        }
        LinkedList<Integer> wordsIntegers = dictionaryTable.get(word);
        TreeSet<String> finalSet = new TreeSet<>();
        if (wordsIntegers == null) {
            return null;
        }
        Integer[] numbers = (Integer[]) wordsIntegers.toArray(new Integer[0]);
        Set<Integer> theSet = new TreeSet<Integer>();

        for (int i = 0; i < numbers.length; i += 1) {
            theSet.add(numbers[i]);
        }
        Set<Integer> resultSet = GraphHelper.descendants(wordDigraph, theSet);
        Iterator<Integer> setIterator = resultSet.iterator();
        while (setIterator.hasNext()) {
            Integer tempInteger = setIterator.next();
            LinkedList<String> intableLinkedList = dataTable.get(tempInteger);
            String[] tempArray = (String[]) intableLinkedList
                    .toArray(new String[0]);
            for (int i = 0; i < tempArray.length; i += 1) {
                if (!finalSet.contains(tempArray[i])) {
                    finalSet.add(tempArray[i]);
                }
            }
        }

        return finalSet;
    }
}
