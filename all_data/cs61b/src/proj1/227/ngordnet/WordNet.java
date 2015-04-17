package ngordnet;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.Iterator;
import java.io.File; 
import edu.princeton.cs.introcs.In; 
import edu.princeton.cs.algs4.Digraph; 
import java.util.HashMap;
import java.util.HashSet;

//Worked with Lakshay Badlani, Christine Chen and ELmer Diaz 
//on wordnet constructor, especially for the & hyponyms part.
public class WordNet {
    private In syn; 
    private In hyp; 
    private HashMap<Integer, String> idsynset; 
    private HashMap<String, Object[]> hypernymhyponym; 
    private HashMap<String, Boolean> synsetboolean; 
    private int[] array;
    private Object p; 
    private Digraph diagraph; 
    private int size = 0; 

    //Recieved significant help from Lakshay Badlani and Elmer Diaz, 
    //especially for setting up the digraph. 
    public WordNet(String synsetsfile, String hypernymsfile) {
        syn = new In(new File(synsetsfile));
        hyp = new In(new File(hypernymsfile));
        idsynset = new HashMap<Integer, String>();
        hypernymhyponym = new HashMap<String, Object[]>(); 
        synsetboolean = new HashMap<String, Boolean>(); 
        while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] rawTokens = line.split(",");
            idsynset.put(Integer.parseInt(rawTokens[0]), rawTokens[1].toString()); 
            String[] splitwords = rawTokens[1].split(" ");
            for (int i = 0; i < splitwords.length; i += 1) {
                synsetboolean.put(splitwords[i], true);
            } size += 1; 
        }

        diagraph = new Digraph(size);
        while (!hyp.isEmpty()) {
            String hypline = hyp.readLine(); 
            String[] hrawTokens = hypline.split(",");
            int first = Integer.parseInt(hrawTokens[0]);
            array = new int[hrawTokens.length - 1];
            for (int i = 1, j = 0; i < (hrawTokens.length); i += 1, j += 1) {
                array[j] = Integer.parseInt(hrawTokens[i]); 
            } 
            for (int i = 0; i < array.length; i += 1) {
                diagraph.addEdge(first, array[i]);
            }
        } 
    }

    public boolean isNoun(String word) {
        return synsetboolean.containsKey(word);
    }
        

    public Set<String> nouns() {
        Set<String> a = synsetboolean.keySet(); 
        return a; 
    }


    //Recieved significant help from Lakshay Badlani for this part: discussion of approach to 
    //solve the problem and understanding it conceptually.  
    public Set<String> hyponyms(String hypernym) {
        HashSet<Integer> split = new HashSet<Integer>();
        HashSet<String> answer = new HashSet<String>(); 
        String[] words;
        int i = 0; 
        for (Integer k : idsynset.keySet()) {
            words = idsynset.get(k).split(" ");
            for (int index = 0; index < words.length; index += 1) {
                if ((words[index]).equals(hypernym)) {
                    split.add(k);
                }
            } i += 1;  
        } 
        Set<Integer> s = GraphHelper.descendants(diagraph, split); 
        Iterator<Integer> iter = s.iterator(); 
        while (iter.hasNext()) {
            String ai = (iter.next()).toString();
            int z = Integer.parseInt(ai);
            String[] si = (idsynset.get(z)).split(" "); 
            for (int a = 0; a < si.length; a += 1) {
                answer.add(si[a]);
            } 
        } return answer; 
    }
}
