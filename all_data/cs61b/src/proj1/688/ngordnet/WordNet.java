package ngordnet;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.io.FileNotFoundException;

public class WordNet {

    private HashMap<Integer, ArrayList> store = new HashMap();
    private HashSet<String> nounsLst = new HashSet();
    private Digraph hyp;
    private ArrayList<String> everyLine = new ArrayList();

    public WordNet(String synsets, String hyponyms) {
        File synsetsF = new File(synsets);
        In hyponymsIn = new In(hyponyms);

        try {
            Scanner sc1 = new Scanner(synsetsF);
            sc1.useDelimiter(",");
            while (sc1.hasNextInt()) {
                Integer i = sc1.nextInt();
                String word = sc1.next();
                String[] splited = word.split("\\s+");
                ArrayList<String> wordArray = new ArrayList();
                for (String s: splited) {
                    wordArray.add(s);
                    if (!nounsLst.contains(s)) {
                        nounsLst.add(s);
                    }
                }
                store.put(i, wordArray);
                sc1.nextLine();
            }
        }  catch (FileNotFoundException e) {
            System.out.println("exception1");
        }
        hyp = new Digraph(nounsLst.size());
        while (true) {
            String thisLine = hyponymsIn.readLine();
            if (thisLine == null) {
                break;
            } else {
                // System.out.println(thisLine);
                everyLine.add(thisLine);
            }

        }

        for (String line: everyLine) {
            ArrayList<Integer> numArray = new ArrayList();
            String[] commaSplit = line.split(",");
            for (String s: commaSplit) {
                String[] num = s.split(" ");
                for (String n: num) {
                    numArray.add(Integer.parseInt(n));
                }
            }
            // System.out.println("head: " + Integer.toString(numArray.get(0)));
            for (Integer i: numArray) {
                if (i != numArray.get(0)) {
                    // System.out.println("edge: " + Integer.toString(i));
                    hyp.addEdge(numArray.get(0), i);
                }
            }
        }
    }

    public boolean isNoun(String word) {
        if (nounsLst.contains(word)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return nounsLst;
    }

    public Set<String> hyponyms(String word) {
        HashSet<Integer> sysIDs = new HashSet();
        for (Map.Entry<Integer, ArrayList> entry : store.entrySet()) {
            for (String s: (ArrayList<String>) entry.getValue()) {
                if (word.equals(s)) {
                    sysIDs.add(entry.getKey());
                }
            }
        }

        Set<Integer> result = GraphHelper.descendants(hyp, sysIDs);
        HashSet returnHyponyms = new HashSet();
        for (Integer i : result) {
            for (String s1: (ArrayList<String>) store.get(i)) {
                if (!returnHyponyms.contains(s1)) {
                    returnHyponyms.add(s1);
                }
            }
        }
        return returnHyponyms;
    }
}
