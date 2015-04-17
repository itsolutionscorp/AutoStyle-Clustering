package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class WordNet {

    private List<String> linesOfSynsets = new ArrayList<>();
    private List<String> linesOfHyponyms = new ArrayList<>();

    private Map<String, String> synsetIdMap = new HashMap<>();
    private Digraph synsetDigraph;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(synsetFilename);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                linesOfSynsets.add(line);
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }

        try {
            inputStream = new FileInputStream(hypernymFilename);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                linesOfHyponyms.add(line);
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }

        for (String temp : linesOfSynsets) {
            String[] temp2 = temp.split(",");
            for (String s : temp2[1].split(" ")) {
                synsetIdMap.put(s, temp2[0]);
            }
        }

        synsetDigraph = new Digraph(linesOfSynsets.size());

        for (String temp : linesOfHyponyms) {
            String[] temp2 = temp.split(",");
            for (int i = 1; i < temp2.length; i++) {
                synsetDigraph.addEdge(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[i]));
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String s : linesOfSynsets) {
            String[] synsets = s.split(",")[1].split(" ");

            for (String se : synsets) {
                if (se.equals(noun)) {
                    return true;
                }
            }
        }

        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<>();

        for (String s : linesOfSynsets) {
            String[] synsets = s.split(",")[1].split(" ");

            for (String se : synsets) {
                nounSet.add(se);
            }
        }

        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsOfWord = new HashSet<>();

        for (String s : linesOfSynsets) {
            String[] synsets = s.split(",")[1].split(" ");
            String synsetId = s.split(",")[0];

            for (String individualWord : synsets) {
                if (individualWord.equals(word)) {
                    Set<Integer> id = new TreeSet<>();
                    id.add(Integer.parseInt(synsetId));

                    Set<Integer> desc = GraphHelper.descendants(synsetDigraph, id);

                    for (int i = 0; i < linesOfSynsets.size(); i++) {
                        int theID = Integer.parseInt(linesOfSynsets.get(i).split(",")[0]);
                        if (desc.contains(theID)) {
                            for (String names : linesOfSynsets.get(i).split(",")[1].split(" ")) {
                                hyponymsOfWord.add(names);
                            }
                        }
                    }
                }
            }
        }

        return hyponymsOfWord;
    }
}
