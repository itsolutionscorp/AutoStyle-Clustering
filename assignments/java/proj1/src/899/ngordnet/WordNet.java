package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, String> backsynset;
    private HashMap<String, Set<Integer>> synset;
    private HashMap<Integer, Integer[]> hypset;
    private Set<String> localhypset;
    private Set<Integer> intset;
    private Digraph WORDsSSSS;

    public WordNet(String synsetFilename, String hyponymFilename) {
        int size = 0;
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);
        String[] lineTemp;
        String[] spacesplit;
        Set<Integer> synlist = new HashSet<Integer>();
        Set<Integer> templist;
        Set<Integer> emptyset = new HashSet<Integer>();
        Integer num;
        backsynset = new HashMap<Integer, String>();
        synset = new HashMap<String, Set<Integer>>();
        hypset = new HashMap<Integer, Integer[]>();
        while (synIn.hasNextLine()) {
            lineTemp = synIn.readLine().split(",");
            num = Integer.parseInt(lineTemp[0]);
            backsynset.put(num, lineTemp[1]);
            synlist.add(num);
            if (lineTemp[1].contains(" ")) {
                spacesplit = lineTemp[1].split(" ");
                for (int i = 0; i < spacesplit.length; i += 1) {
                    if (synset.containsKey(spacesplit[i])) {
                        templist = synset.get(spacesplit[i]);
                        synlist.add(num);
                        synlist.addAll(templist);
                        synset.put(spacesplit[i], synlist);
                        synlist = new HashSet<Integer>();
                    } else {
                        synlist.add(num);
                        synset.put(spacesplit[i], synlist);
                        synlist = new HashSet<Integer>();
                    }
                }
            } else if (synset.containsKey(lineTemp[1])) {
                templist = synset.get(lineTemp[1]);
                synlist.add(num);
                synlist.addAll(templist);
                synset.put(lineTemp[1], synlist);
                synlist = new HashSet<Integer>();
            } else {
                synset.put(lineTemp[1], synlist);
                synlist = new HashSet<Integer>();
            }
            size += 1;
        }
        String[] hyptemp;
        String[] hypstrings;
        String[] lengthcreation;
        hypstrings = hypIn.readAllLines();
        lengthcreation = hypstrings[hypstrings.length - 1].split(",");
        WORDsSSSS = new Digraph(size);
        for (int hs = 0; hs < hypstrings.length; hs += 1) {
            hyptemp = hypstrings[hs].split(",");
            for (int d = 1; d < hyptemp.length; d += 1) {
                WORDsSSSS.addEdge(Integer.parseInt(hyptemp[0]),
                        Integer.parseInt(hyptemp[d]));
            }
        }

    }

    public boolean isNoun(String noun) {
        return synset.containsKey(noun);
    }

    public Set<String> nouns() {
        return synset.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> searchnumbers = synset.get(word);
        intset = GraphHelper.descendants(WORDsSSSS, searchnumbers);
        localhypset = new HashSet<String>();
        Integer searchnumlist;
        String[] spacecontainer;
        String nextword;
        for (Integer point : intset) {
            nextword = backsynset.get(point);
            if (nextword.contains(" ")) {
                spacecontainer = nextword.split(" ");
                for (int j = 0; j < spacecontainer.length; j += 1) {
                    localhypset.add(spacecontainer[j]);
                }
            } else {
                localhypset.add(nextword);
            }
        }
        return localhypset;
    }

}
