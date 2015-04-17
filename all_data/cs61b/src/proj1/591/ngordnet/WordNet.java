package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class WordNet {
    private static final int B_SIZE = 1024;
    private TreeSet[] synsetArray;
    private Digraph hypGraph;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            int size = countLines(synsetFilename);
            synsetArray = new TreeSet[size];
            hypGraph = new Digraph(size);
        
            
            BufferedReader synsetReader = new BufferedReader(new FileReader(synsetFilename));
            
            BufferedReader hypReader = new BufferedReader(new FileReader(hyponymFilename));
        
        
            String synLine = "";
            String hypLine = "";
            while ((synLine = synsetReader.readLine()) != null) {
                String[] tokens = synLine.split(",");
                String[] syns = tokens[1].split(" ");
                int index = Integer.parseInt(tokens[0]);
                TreeSet temp = new TreeSet();
                for (String syn : syns) {
                    temp.add(syn); 
                }
                synsetArray[index] = temp;
            }

            while ((hypLine = hypReader.readLine()) != null) {
                String[] tokens = hypLine.split(",");
                int hyper = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i += 1) {
                    int hypo = Integer.parseInt(tokens[i]);
                    hypGraph.addEdge(hyper, hypo);
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error"); 
        }
    }
    
    public Set<String> nouns() {
        TreeSet temp = new TreeSet();
        for (TreeSet ts : synsetArray) {
            temp.addAll(ts);
        }
        return temp;
    }
    
    public Set<String> hyponyms(String word) {
        TreeSet temp = new TreeSet();
        TreeSet verticies = new TreeSet();
        for (int i = 0; i < synsetArray.length; i += 1) {
            if (synsetArray[i].contains(word)) {
                verticies.add(i);
            }
        }
        Set<Integer> des = GraphHelper.descendants(hypGraph, verticies);
        for (Integer d : des) {
            temp.addAll(synsetArray[d]);
        }
        return temp;
    }
    
    public boolean isNoun(String noun) {
        for (TreeSet ts : synsetArray) {
            if (ts.contains(noun)) {
                return true;
            }
        }
        return false;
    }
    
    /* Source: http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java*/
    private int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[B_SIZE];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }    
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            } 
            return count;
        } finally {
            is.close();
        }
    }   
}
