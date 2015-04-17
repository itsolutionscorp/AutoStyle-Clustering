package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private Digraph digraphMap;
    private ArrayList<String[]> synsetArray; //index is array, value is a list of words for id
    private HashMap<String, int[]> nounToId;

    public WordNet(String synset, String hyponym) {
        In synsetStream =  new In(synset);
        synsetArray = new ArrayList<String[]>();
        nounToId = new HashMap<String, int[]>();
        while (synsetStream.hasNextChar()) {
            String set = synsetStream.readLine();
            String[] synsetSet = set.split(",");
            int key = Integer.parseInt(synsetSet[0]);

            int[] keyInsert = new int[]{key};
            String noun = synsetSet[1];
            String[] nounSet = noun.split(" ");
            synsetArray.add(key, nounSet);
            for (String s: nounSet) {
                if (nounToId.containsKey(s)) {
                    int[] retTemp = nounToId.get(s);
                    int[] toRet = new int[retTemp.length + 1];
                    for (int x = 0; x < retTemp.length; x++) {
                        toRet[x] = retTemp[x];
                    }
                    toRet[retTemp.length] = key;
                    nounToId.put(s, toRet);
                } else {
                    nounToId.put(s, keyInsert);
                }   
            }
        }

        digraphMap = new Digraph(synsetArray.size());
        In hyponymStream = new In(hyponym);

        while (hyponymStream.hasNextChar()) {
            String set = hyponymStream.readLine();
            String[] hyponymSetAll = set.split(",");
            int[] hyponymSet = new int[hyponymSetAll.length];
            for (int k = 0; k < hyponymSetAll.length; k++) {
                hyponymSet[k] = Integer.parseInt(hyponymSetAll[k]);
            }

            int vertex = hyponymSet[0];
            int[] hypoSet = Arrays.copyOfRange(hyponymSet, 1, hyponymSetAll.length);
            for (int l = 0; l < hypoSet.length; l++) {
                digraphMap.addEdge(vertex, hypoSet[l]);
            }
        }
    }

    public boolean isNoun(String noun) {
        //returns true if the given noun exists
        return nounToId.containsKey(noun);
    }

    public Set<String> nouns() {
        //return list of nouns
        return nounToId.keySet();
    }

    public Set<String> hyponyms(String noun) {
        //return list of hypos
        Set<String> returnAnswer = new HashSet<String>();
        int[] ids = nounToId.get(noun);
        for (int id : ids) {
            Set<Integer> n = new TreeSet<Integer>();
            n.add(id);
            Set<Integer> res = GraphHelper.descendants(digraphMap, n);
            for (int a : res) {
                String[] tmp = synsetArray.get(a);
                for (String x : tmp) {
                    returnAnswer.add(x);
                }
            }
        }
        return returnAnswer;
    }
}

