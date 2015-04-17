package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class WordNet {
    private Digraph graph;
    private int numVertices;
    private HashMap<Integer, List<String>> iTos;
    private HashMap<String, List<Integer>> sToi;


    private void readAndParseCSV(String synsetFile, String hyponymFile) {
        BufferedReader brS = null;
        BufferedReader brH = null;
        String line = "";
        String splitter = ",";

        try {
            brS = new BufferedReader(new FileReader(synsetFile));
            brH = new BufferedReader(new FileReader(hyponymFile));
            while ((line = brS.readLine()) != null) {
                numVertices++;
                String[] sLine = line.split(splitter);
                String[] words = sLine[1].split(" ");
                for (String w : words) {
                    List<String> wo = new ArrayList<String>();
                    wo.add(w);
                    if (sToi.containsKey(w)) {
                        sToi.get(w).add(Integer.parseInt(sLine[0])); 
                    } else {
                        List<Integer> in = new ArrayList<Integer>();
                        in.add(Integer.parseInt(sLine[0]));
                        sToi.put(w, in);
                    }
                    if (iTos.containsKey(Integer.parseInt(sLine[0]))) {
                        iTos.get(Integer.parseInt(sLine[0])).add(w);
                    } else {
                        iTos.put(Integer.parseInt(sLine[0]), wo);
                    }
                }
            }
            graph = new Digraph(numVertices);
            line = "";
            while ((line = brH.readLine()) != null) {
                String[] hLine = line.split(splitter);
                int index = Integer.parseInt(hLine[0]);
                for (int i = 1; i < hLine.length; i++) {
                    graph.addEdge(index, Integer.parseInt(hLine[i]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brS != null && brH != null) {
                try {
                    brS.close();
                    brH.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public WordNet(String synsetFile, String hyponymFile) {
        iTos = new HashMap<Integer, List<String>>();
        sToi = new HashMap<String, List<Integer>>();
        numVertices = 0;
        readAndParseCSV(synsetFile, hyponymFile);

    }

    public boolean isNoun(String noun) {
        return sToi.containsKey(noun);
    }

    public Set<String> nouns() {
        Set<String> noun = sToi.keySet();
        return noun;
    }

    private void printTable() {
        Set<Map.Entry<Integer, List<String>>> it = iTos.entrySet();
        Iterator its = it.iterator();
        while (its.hasNext()) {
            System.out.println(its.next());
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> indices = new HashSet<Integer>();
        Set<String> hyp = new HashSet<String>();
        indices.addAll(sToi.get(word));

        indices = GraphHelper.descendants(graph, indices);
        for (Integer i : indices) {
            hyp.addAll(iTos.get(i));
        }
        return hyp;
    }
}
