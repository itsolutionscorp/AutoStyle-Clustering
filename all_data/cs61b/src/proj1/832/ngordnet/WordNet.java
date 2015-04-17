package ngordnet;

//import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
//import java.util.HashSet;
import java.util.TreeSet;

//used for read a test file line by line
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordNet {

    private Map<Integer, List<String>> synsetMap;
    private Map<Integer, List<Integer>> hyponymsMap;
    private Digraph hyponymsDigraph = null;
    private int vertices = 0; // vertex count;

    public WordNet(String synsetFileName, String hynosymFileName) {

        synsetMap = new HashMap<Integer, List<String>>();
        hyponymsMap = new HashMap<Integer, List<Integer>>();

        readSynsetFile(synsetFileName); // wang, need to reconsider to split the
        // synset with spaces. commma only
        // separate
        // id, synset, and definition.

        readHyponymsFile(hynosymFileName);

        // after reading int synset file we know the vertex count
        // use the vertex to set the digraph size
        hyponymsDigraph = new Digraph(vertices);

        buildHyponymsDigraph();

    }

    private void buildHyponymsDigraph() {

        Iterator<Integer> keyIterator = hyponymsMap.keySet().iterator();

        while (keyIterator.hasNext()) {
            Integer aKey = keyIterator.next();
            List<Integer> aValue = hyponymsMap.get(aKey);
            for (int j = 0; j < aValue.size(); j++) {
                hyponymsDigraph.addEdge(aKey, aValue.get(j));
            }
            // System.out.printf("key= %d, value=%s\n", aKey, output);
        }
    }

    private void readHyponymsFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] textLine = line.split(",");
                List<Integer> synsetWordList = new ArrayList<Integer>();
                for (int i = 1; i < textLine.length; i++) {
                    synsetWordList.add(Integer.parseInt(textLine[i]));
                }
                // what happens if a key already exists
                // like this scenario:
                // 11, 12
                // 11, 13
                // after read line 11, 12, nextline has the same id (11), in
                // this case we need to append 13 to the end of 12
                int wordid = Integer.parseInt(textLine[0]);
                if (hyponymsMap.containsKey(wordid)) {
                    List<Integer> existingList = hyponymsMap.get(wordid);
                    existingList.addAll(synsetWordList);
                    hyponymsMap.put(wordid, existingList);
                } else {
                    hyponymsMap.put(Integer.parseInt(textLine[0]), synsetWordList);
                }
            }
            fileReader.close();         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSynsetFile(String fileName) {

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] textLine = line.split(",");
                List<String> synsetWordList = new ArrayList<String>();
                String[] synset = textLine[1].split(" ");
                for (int i = 0; i < synset.length; i++) {
                    synsetWordList.add(synset[i]);
                }
                synsetMap.put(Integer.parseInt(textLine[0]), synsetWordList);

                this.vertices++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> getHyponymsSetBySetOfKey(Set<Integer> keySet) {

        Set<String> hyponymsSet = new TreeSet<String>();

        for (int s : keySet) {
            // System.out.println(s);
            List<String> aValue = synsetMap.get(s);

            for (int j = 0; j < aValue.size(); j++) {
                hyponymsSet.add(aValue.get(j));
            }
        }
        return hyponymsSet;
    }

    private Set<Integer> getKeyByHyponymsWord(String word) {

        Iterator<Integer> keyIterator = synsetMap.keySet().iterator();
        Set<Integer> matchingKeySet = new TreeSet<Integer>();

        while (keyIterator.hasNext()) {
            Integer aKey = keyIterator.next();
            List<String> aValue = synsetMap.get(aKey);

            for (int j = 0; j < aValue.size(); j++) {

                if (word.equals(aValue.get(j))) {
                    matchingKeySet.add(aKey);
                }
            }
        }
        return matchingKeySet;
    }

    // pass a word, check it exists in synset Map
    public boolean isNoun(String word) {

        Iterator<Integer> keyIterator = synsetMap.keySet().iterator();

        while (keyIterator.hasNext()) {
            Integer aKey = keyIterator.next();
            List<String> aValue = synsetMap.get(aKey);

            for (int j = 0; j < aValue.size(); j++) {
                if (word.equals(aValue.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // return all nouns in a set from synsetMap
    public Set<String> nouns() {

        Iterator<Integer> keyIterator = synsetMap.keySet().iterator();
        Set<String> hyponymsSet = new TreeSet<String>();

        while (keyIterator.hasNext()) {
            Integer aKey = keyIterator.next();
            List<String> aValue = synsetMap.get(aKey);

            for (int j = 0; j < aValue.size(); j++) {
                hyponymsSet.add(aValue.get(j));
            }
        }
        return hyponymsSet;
    }

    //Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
    //If WORD belongs to multiple synsets, return all hyponyms of all of these 
    //synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms 
    //of synonyms.
    public Set<String> hyponyms(String word) {

        Collection<Integer> matchingKeys = this.getKeyByHyponymsWord(word);

        Set<String> hyponymsSet = new TreeSet<String>();
        for (Integer matchingkey : matchingKeys) {
            Set<Integer> keySet = new TreeSet<Integer>();
            keySet.add(matchingkey);
            //all children for this matchingKey
            Set<Integer> allDescendantSet = GraphHelper.descendants(hyponymsDigraph, keySet);
            for (Integer childkey : allDescendantSet) {
                hyponymsSet.addAll(synsetMap.get(childkey));
            }
        }
        return hyponymsSet;
    }
}
