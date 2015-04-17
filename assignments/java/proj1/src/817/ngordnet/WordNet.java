package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class WordNet {
    private Map<Integer, HashSet<String>> synsets;
    private Map<Integer, HashSet<Integer>> hyponyms;
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = readSynsets(synsetFilename);
        hyponyms = readHyponyms(hyponymFilename);
        d = constructGraph();
    }

    private HashMap<Integer, HashSet<String>> readSynsets(String file) {
        HashMap<Integer, HashSet<String>> temp = new HashMap<Integer, HashSet<String>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line = new String();
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    String[] synonyms = fields[1].split(" ");
                    HashSet<String> names = new HashSet<String>(Arrays.asList(synonyms));
                    temp.put(Integer.parseInt(fields[0]), names);
                }
                br.close();
            } catch (IOException ex) {
                return null;
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return temp;
    }

    private HashMap<Integer, HashSet<Integer>> readHyponyms(String file) {
        HashMap<Integer, HashSet<Integer>> temp = new HashMap<Integer, HashSet<Integer>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line = new String();
                while ((line = br.readLine()) != null) {
                    String[] fieldsString = line.split(",");
                    Integer[] fields = new Integer[fieldsString.length];
                    for (int i = 0; i < fieldsString.length; i++) {
                        fields[i] = Integer.parseInt(fieldsString[i]);
                    }
                    Integer[] rest = Arrays.copyOfRange(fields, 1, fields.length);
                    if (!temp.containsKey(fields[0])) {
                        temp.put(fields[0], new HashSet<Integer>(Arrays.asList(rest)));
                    } else {
                        temp.get(fields[0]).addAll(new HashSet<Integer>(Arrays.asList(rest)));
                    }
                }
                br.close();
            } catch (IOException ex) {
                return null;
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return temp;
    }

    private Digraph constructGraph() {
        Digraph temp = new Digraph(synsets.size());
        for (Integer i : hyponyms.keySet()) {
            for (Integer j : hyponyms.get(i)) {
                temp.addEdge(i, j);
            }
        }
        return temp;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
        // check if noun exists in nouns()
    }

    public Set<String> nouns() {
        Set<String> temp = new HashSet<String>();
        for (Integer i : synsets.keySet()) {
            temp.addAll(synsets.get(i));
        }
        return temp;
        // return the Set of all elemnts in values of the synsets
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> temp = new HashSet<String>();
        Integer id = 0;
        if (isNoun(word)) {
            for (Integer i : synsets.keySet()) {
                if (synsets.get(i).contains(word)) {
                    id = i;
                    HashSet<Integer> idSet = new HashSet<Integer>(Arrays.asList(id));
                    Set<Integer> ids = GraphHelper.descendants(d, idSet);
                    for (Integer j : ids) {
                        for (String noun : synsets.get(j)) {
                            temp.add(noun);
                        }
                    }
                }
            }
        }
        return temp;
        // use that key for descendents in digraph
        // use those ids to get the Set of Strings from each and print them
    }

}
