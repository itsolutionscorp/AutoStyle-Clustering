package ngordnet;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph d;
    private Map<Integer, String> numToSys = new TreeMap<Integer, String>();
    private Map<Integer, String> def = new TreeMap<Integer, String>();
    private Map<String, Set<Integer>> wordToNum = new TreeMap<String, Set<Integer>>();

    public WordNet(String sysnetFile, String hyponymFile) {
        In openSys = new In(sysnetFile);
        In openHyp = new In(hyponymFile);
        In hypCount = new In(hyponymFile);
        int dSize = 0;
        String line;
        String[] tokens, wordsIn;
        Integer tempNum;
        Set<Integer> tempIntSet;

        while (openSys.hasNextLine()) {
            line = openSys.readLine();
            tokens = line.split(",");
            numToSys.put(Integer.parseInt(tokens[0]), tokens[1]);
            def.put(Integer.parseInt(tokens[0]), tokens[2]);
            wordsIn = tokens[1].split(" ");

            for (String word : wordsIn) {
                tempIntSet = wordToNum.get(word);

                if (tempIntSet == null) {
                    wordToNum.put(word, new TreeSet<Integer>());
                }
                wordToNum.get(word).add(Integer.parseInt(tokens[0]));
            }
        }

        openSys.close();

        while (hypCount.hasNextLine()) {
            line = hypCount.readLine();
            tokens = line.split(",");
            dSize += tokens.length;
        }

        hypCount.close();

        d = new Digraph(numToSys.size());
        while (openHyp.hasNextLine()) {
            line = openHyp.readLine();
            tokens = line.split(",");

            for (int i = 1; i < tokens.length; i++) {
                d.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }

        openHyp.close();
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> sysnet = wordToNum.get(word);
            Set<Integer> ret = GraphHelper.descendants(d, sysnet);
            Set<String> ret1 = new TreeSet<String>();
            String[] temp;

            for (Integer i : sysnet) {
                temp = numToSys.get(i).split(" ");
                for (String x : temp) {
                    ret1.add(x);
                }
            }

            for (Integer i : ret) {
                temp = numToSys.get(i).split(" ");
                for (String x : temp) {
                    ret1.add(x);
                }
            }
            return ret1;
        }
        return null;
    }

    public boolean isNoun(String noun) {
        return wordToNum.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordToNum.keySet();
    }
}
