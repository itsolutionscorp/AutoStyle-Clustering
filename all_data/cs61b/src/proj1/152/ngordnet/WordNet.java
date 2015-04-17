/** WordNet class
 *  @author Nandita Sampath
 */
package ngordnet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;


public class WordNet {

    private TreeMap<Integer, TreeSet<String>> id2WordMap = 
        new TreeMap<Integer, TreeSet<String>>();
    private TreeMap<String, TreeSet<Integer>> word2IDMap = 
        new TreeMap<String, TreeSet<Integer>>();
    private Digraph digraph;
    private int numVertices;

    public WordNet(String synsetFilename, String hyponymFilename) {
        readSynsetFile(synsetFilename);
        readHyponymFile(hyponymFilename);
    }

    //file reader/comma split from myong.com 
    private void readSynsetFile(String csvFile) {
        BufferedReader br = null;
        String line = "";
        String commaSplitBy = ",";
        String spaceSplitBy = " ";
        Integer synsetID;

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                TreeSet<String> wordSet = new TreeSet<String>();
                numVertices++; 
                // use comma as separator
                String[] synsetLine = line.split(commaSplitBy);

                synsetID = Integer.parseInt(synsetLine[0]);
                //use space as separator
                String[] words = synsetLine[1].split(spaceSplitBy);
                for (String word: words) {
                    wordSet.add(word);
                }
                //create id to word map
                id2WordMap.put(synsetID, wordSet);

                for (String word: words) {
                    TreeSet<Integer> ts = word2IDMap.get(word);
                    if (ts == null) {
                        ts = new TreeSet<Integer>();
                        ts.add(synsetID);
                    } else {
                        ts.add(synsetID);
                    }
                    word2IDMap.put(word, ts);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readHyponymFile(String hyponymFile) {
        BufferedReader br = null;
        String line = "";
        String commaSplitBy = ",";
        Integer synsetID = 0;
        digraph = new Digraph(numVertices);
        int index;

        try {
            br = new BufferedReader(new FileReader(hyponymFile));
            while ((line = br.readLine()) != null) {
                index = 0;
                // use comma as separator
                String[] hyponymLine = line.split(commaSplitBy);

                for (String hypID: hyponymLine) {
                    if (index == 0) {
                        synsetID = Integer.parseInt(hypID);
                    } else {
                        Integer hypint = Integer.parseInt(hypID);
                        digraph.addEdge(synsetID, hypint);
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isNoun(String noun) {
        return word2IDMap.containsKey(noun);
    }

    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (String key: word2IDMap.keySet()) {
            allNouns.add(key);
        }
        return allNouns;
    }

    public Set<String> hyponyms(String word) {

        Set<String> hyps = new TreeSet<String>();
        Set<Integer> idsOfWord = new TreeSet<Integer>();
        if (word2IDMap.get(word) != null) {
            idsOfWord.addAll(word2IDMap.get(word));
            Set<Integer> desKey = new TreeSet<Integer>();
            desKey.addAll(GraphHelper.descendants(digraph, idsOfWord));
            for (Integer k: desKey) {
                if (id2WordMap.containsKey(k)) {
                    hyps.addAll(id2WordMap.get(k));
                }
            }
        }
        return hyps;
    } 
} 
