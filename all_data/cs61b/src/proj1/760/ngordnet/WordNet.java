package ngordnet;
import edu.princeton.cs.introcs.In;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import com.google.common.collect.Multimap; //http://docs.guava-libraries.googlecode.com/
//git/javadoc/com/google/common/collect/ArrayListMultimap.html
import com.google.common.collect.ArrayListMultimap; //http://docs.guava-libraries.googlecode.com/
//git/javadoc/com/google/common/collect/Multimap.html
// collab on hyponyms with Leon Liang
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    private Multimap<Integer, String> multi = ArrayListMultimap.create();
    private Multimap<String, Integer> multiId = ArrayListMultimap.create();
    private int vertexSize;
    private int edgeSize;
    private Digraph g;
    public WordNet(String synsets, String hyponyms) {
        Scanner s = null;
        In h = null;
        In h2 = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(synsets)));
            h2 = new In(hyponyms);
            h = new In(hyponyms);
            s.useDelimiter(",");
            while (s.hasNextLine()) {
                int key = s.nextInt();
                String value = s.next();
                String[] parts = value.split(" ");
                Set<String> setValue = new TreeSet<String>(Arrays.asList(parts));
                int size = parts.length;
                for (int i = 0; i < size; i++) {
                    multi.put(key, parts[i]);
                    multiId.put(parts[i], key);
                }
                s.nextLine();
                vertexSize += 1; 
            }
            g = new Digraph(vertexSize);
            while (h.readLine() != null) {
                edgeSize += 1;
            }
            for (int i = 0; i < edgeSize; i++) {
                String hLine = h2.readLine();
                Scanner hInt = new Scanner(hLine).useDelimiter(",");
                int key = hInt.nextInt();
                while (hInt.hasNextInt()) {
                    int value = hInt.nextInt();
                    g.addEdge(key, value);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found :O");
        } finally {
            if (s != null) {
                s.close();
            }
            if (h2 != null) {
                h2.close();
            }  
        }
    }   
    public Set<String> nouns() {
        return multiId.keySet(); 
    }
    public boolean isNoun(String word) {
        return multi.containsValue(word);  
    }
    public Set<String> hyponyms(String word) {
        List<Integer> listHypo = new ArrayList<Integer>(multiId.get(word));
        Set<Integer> listKey = new TreeSet<Integer>();    
        for (Integer key : listHypo) {    
            listKey.add(key);
        }
        Set<Integer> descendInt = GraphHelper.descendants(g, listKey);  
        Set<String> hypoSet = new TreeSet<String>();
        for (Integer hypoVal:descendInt) {
            List<String> hypoList = new ArrayList<String>(multi.get(hypoVal));
            for (String hypoWord:hypoList) {
                hypoSet.add(hypoWord);
            }
        }
        return hypoSet;
    }         
}


