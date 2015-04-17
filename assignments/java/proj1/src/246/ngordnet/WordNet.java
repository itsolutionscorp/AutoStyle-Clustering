package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by TK on 3/6/15.
 */
public class WordNet extends Object {
    private Digraph wordnet;
    private int synsetsSize;
    private HashMap<Integer, String[]> synsetsMap;
    private HashMap<String, ArrayList<Integer>> synsetsbyString;

    public WordNet(String synsetFile, String hypoFile) {
        File syns = new File(synsetFile);
        File hyp = new File(hypoFile);
        Scanner synsets = null;
        Scanner hypos = null;
        try {
            synsets = new Scanner(syns).useDelimiter(",\\s");
            hypos = new Scanner(hyp);
        } catch (FileNotFoundException e) {
            synsets = new Scanner("./p1data/" + synsetFile.substring(2));
            hypos = new Scanner("./p1data/" + hypoFile.substring(2));

            syns = new File("./p1data/" + synsetFile.substring(2));
            hyp = new File("./p1data/" + hypoFile.substring(2));
            try {
                synsets = new Scanner(syns);
                hypos = new Scanner(hyp);
            } catch (FileNotFoundException c) {
                System.out.println(syns + "Files not found.");
            }
        }

        while (synsets.hasNextLine()) {
            synsets.nextLine();
            synsetsSize++;
        }
        try {
            synsets = new Scanner(syns).useDelimiter(",\\s");
        } catch (FileNotFoundException e) {
            //System.out.println("blah");
        }

        synsetsMap = new HashMap<>(synsetsSize);
        synsetsbyString = new HashMap<>(synsetsSize);
        String[] words;
        int id;
        String line;
        String[] vals;
        while (synsets.hasNextLine()) {
            line = synsets.nextLine();
            vals = line.split(",");
            id = Integer.parseInt(vals[0]);
            words = vals[1].split("\\s");

            synsetsMap.put(id, words); // fill in synsetsMap

            for (String word : words) { // fill in synsetsbyString
                if (synsetsbyString.containsKey(word)) {
                    //if synsetsbyString already has an id for word, append id
                    synsetsbyString.get(word).add(id);
                } else {
                    //else make new node of synsetsbyString for word
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(id);
                    synsetsbyString.put(word, temp);
                }
            }
        }

        wordnet = new Digraph(synsetsSize);

        while (hypos.hasNextLine()) {
            line = hypos.nextLine();
            vals = line.split(",");
            int hyper = Integer.parseInt(vals[0]);
            int i = 1;
            while (i < vals.length) {
                wordnet.addEdge(hyper, Integer.parseInt(vals[i]));
                i++;
            }
        }
    }

    public Set<String> hyponyms(String word) {
        if (!synsetsbyString.containsKey(word)) {
            return null;
        }
        Set<String> hypoints = new HashSet<>();

        //get hyponyms
        ArrayList<Integer> ids = synsetsbyString.get(word); //int synset id
        for (Integer id: ids) {
            Iterable hypoIter = wordnet.adj(id);
//            System.out.println(id + " is connected to ");
//            for (Object i : hypoIter) {
//                System.out.println("   " + i);
//            }
//            hypoIter = wordnet.adj(id);

            for (Object i : hypoIter) {
                int hypoID = (Integer) i;
//                System.out.println("looking for hyponyms of " + hypoID);
                hypoints.addAll(Arrays.asList(synsetsMap.get(hypoID)));
                if (synsetsMap.get(hypoID).length > 0) {
                    Set<String> subhyp = hyponyms(synsetsMap.get(hypoID)[0]);
                    hypoints.addAll(subhyp);
                }
            }
            //get synonyms
            String[] synonyms = synsetsMap.get(id);
            hypoints.addAll(Arrays.asList(synonyms));
            // source: http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
            //System.out.println(wordnet);
        }
        return hypoints;
    }

    private Set<String> hyponyms(int iD) {
        if (!synsetsMap.containsKey(iD)) {
            return null;
        }


        //get hyponyms
        Iterable hypoIter = wordnet.adj(iD);
        hypoIter = wordnet.adj(iD);
        Set<String> hypoints = new HashSet<>(); //[id1, id2, id3...]

        for (Object i : hypoIter) {
            int hypoID = (Integer) i;
//            System.out.println("looking for hyponyms of " + hypoID);
            String[] synsets = synsetsMap.get(hypoID);
            hypoints.addAll(Arrays.asList(synsets));
            Set<String> subhyp = hyponyms(hypoID);
            hypoints.addAll(subhyp);
        }
        //get synonyms
        String[] synonyms = synsetsMap.get(iD);
        hypoints.addAll(Arrays.asList(synonyms));
        // source: http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
        //System.out.println(wordnet);
        return hypoints;
    }

    private Set<String> getSynsets(String word) {
        Set<String> hypoints = new HashSet<>();
        ArrayList<Integer> ids = synsetsbyString.get(word);
        for (Integer id: ids) {
            String[] synonyms = synsetsMap.get(id);
            hypoints.addAll(Arrays.asList(synonyms));
        }
        return hypoints;
    }


    public boolean isNoun(String word) {
        return synsetsbyString.containsKey(word);
    }

    public Set<String> nouns() {
        Set<String> setu = synsetsbyString.keySet();
        //System.out.println(synsetsbyString);
        return setu;
    }
}
