package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.lang.String;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;

public class WordNet {
    private int vertex;
    private int actualvertex;
    private Digraph s;
    private HashMap<Integer, String[]> sHash;
    private HashSet<String> set;
    private HashSet<String> hyposet;
    private HashSet<Integer> iDs;
    private String[] x;

    
/* Got help from Stina Shen(A Lab TA) who helped me and Briana Nguyen with setting up the WordNet and guiding us 
** through implementing the other methods of this part.
** Litterally sat through with us for 3+ hours and helped us figure out what to do when we got lost and 
** helped us debug when we had trouble. (Litterally the nicest person in lab) */
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sin2 = new In(synsetFilename);
        In sin = new In(synsetFilename);
        In hin = new In(hyponymFilename);
        
    
        actualvertex = getVertices(synsetFilename);
        
        
        s = new Digraph(actualvertex);
            while (!hin.isEmpty()) {
                String hString = hin.readLine();
                String[] hLine = hString.split(",");
                for (int x = 1; x < hLine.length; x++) {
                        s.addEdge(Integer.parseInt(hLine[0]), Integer.parseInt(hLine[x]));
                    }
            }

        sHash = new HashMap<Integer, String[]>();
            while (!sin.isEmpty()) {   //might have to chagne sin.hasNextLine to sin.isempty or some shit like that */
                String sString = sin.readLine();
                String[] sLine = sString.split(",");
                String[] sLine2 = sLine[1].split(" ");
                sHash.put(Integer.parseInt(sLine[0]), sLine2);

            }
        set = new HashSet<String>();
            while (!sin2.isEmpty()) {
                String lineSet1 = sin2.readLine();
                String[] sLine = lineSet1.split(",");
                    String[] s2Line = sLine[1].split(" ");
                    for (int x =0; x < s2Line.length; x++) {
                        set.add(s2Line[x]);
                    }
            }   
    }   
    


    public boolean isNoun(String noun) {
        if (set.contains(noun)) {
            return true;
        }        else {
            return false;   
        }
    }

    public Set<String> nouns() {
        return set; 
    }
    /** Used http://stackoverflow.com/questions/10462819/get-keys-from-hashmap-in-java for ideas of how to get keys from a hashmap */

    public Set<String> hyponyms(String word) {
        Set<Integer> iDs = new HashSet<Integer>();
        Set<Integer> graphset = new HashSet<Integer>();
        hyposet = new HashSet<String>();
        hyposet.add(word);
        for (Map.Entry<Integer, String[]> entry : sHash.entrySet()) {
                Integer key = entry.getKey();
                String[] value = entry.getValue();
                for (int i =0; i < value.length; i++) {
                    if (value[i].equals(word)) {
                        hyposet.add(value[i]);
                        graphset.add(key);
                    }
                }
        }
        
        iDs = ((GraphHelper.descendants(s, graphset)));
            for (Iterator<Integer> it = iDs.iterator();it.hasNext();) {
                x= sHash.get(it.next());
                for (int i =0; i < x.length; i++) {
                    hyposet.add(x[i]);
                }
            }                    
        return hyposet;
        
        //ArrayListSet<String> hset = new Set<String>();
    }

    
    private int getVertices(String synset) {
        In in100 = new In(synset);
        while (in100.hasNextLine()!= false) {
            String lineVertices = in100.readLine();
            String[] newLine = lineVertices.split(",");
            vertex = Integer.parseInt(newLine[0]);
        }
        vertex = vertex + 1;
        return vertex;
    }



}