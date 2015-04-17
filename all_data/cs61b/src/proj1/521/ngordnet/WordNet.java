package ngordnet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;
//Kevin Guru Ji inspired me to make use of the BufferedReader class

public class WordNet {

    private HashSet<String> nouns;
    private HashMap<Integer, Set<String>> setOfSynonyms;
    private HashMap<String, Set<Integer>> nounSet;
    private Digraph g;

    public WordNet(String synsetsFilename, String hyponymsFilename) {

        setOfSynonyms = new HashMap<Integer, Set<String>>();
        nounSet = new HashMap<String, Set<Integer>>();

        try {

            BufferedReader sysnetsText = new BufferedReader(new FileReader(synsetsFilename));
            String line = sysnetsText.readLine(); 

            while (line != null) {

                String[] lineData = line.split(",");

                // Parse data
                int index = Integer.parseInt(lineData[0]);
                String words = lineData[1];
                String[] synonymArray = words.split(" ");
                Set<String> synSet = new HashSet<String>(Arrays.asList(synonymArray));

                // Put a synSet for the index
                setOfSynonyms.put(index, synSet);


                for (String syn : synSet) {
                    if (!nounSet.containsKey(syn)) {
                        nounSet.put(syn, new HashSet<Integer>());
                    } 
                    nounSet.get(syn).add(index);
                }

                line = sysnetsText.readLine();

            }

            sysnetsText.close(); 

        } catch (IOException e) { 
            System.out.println("Error: IOException in sysnetsText WordNet");
        }

        g = new Digraph(nounSet.size());

        try {

            BufferedReader hyponymText = new BufferedReader(new FileReader(hyponymsFilename));
            String line = hyponymText.readLine();

            while (line != null) {

                String[] indexData = line.split(",");
                int mainDex = Integer.parseInt(indexData[0]);

                for (int i = 1; i < indexData.length; i++) {
                    int edgedDex = Integer.parseInt(indexData[i]);
                    g.addEdge(mainDex, edgedDex);
                }
                line = hyponymText.readLine();
            }
            hyponymText.close();
        } catch (IOException e) {
            System.out.println("Error: IOException in hyponymText WordNet");
        }
    }
    public boolean isNoun(String noun) {
        return nounSet.containsKey(noun);
    }

    public Set<String> nouns() {
        return nounSet.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (!isNoun(word)) {
            return hyponyms;
        }
        Set<Integer> descendants = GraphHelper.descendants(g, nounSet.get(word));
        for (Integer index : descendants) {
            Set<String> synSet = setOfSynonyms.get(index);
            hyponyms.addAll(synSet);
        }
        return hyponyms;
    }
}
