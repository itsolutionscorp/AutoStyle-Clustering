package ngordnet;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;   //Impoting In
import edu.princeton.cs.algs4.Digraph; //Importing digraph
import java.util.Iterator;



public class WordNet {
    private String synSetFile; // the name of the synSetFile this WordNet uses
    private String hypeSetFile; // the name of the hypeSetFile this WordNet uses
    private TreeSet<String> nouns; // a TreeSet that contains all the nouns in the synSetFile
    private int numVertices;
    private Digraph hypsDigraph;
    private TreeMap<Integer, String[]> iDKeyMap;
    private TreeMap<String, TreeSet<Integer>> wordKeyMap; 


 




 
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        synSetFile = synsetFilename;
        hypeSetFile = hyponymFilename;

        //STEP1: read in synset. 
            //a) add words to TreeSet nouns;
            //b) create (ID, synset) map
            //c) create (word, ID) map
        nouns = new TreeSet<String>(); // Set for isNoun() and nouns()
        getNouns();
        iDKeyMap = new TreeMap<Integer, String[]>();
        populateIDKeyMap();

        wordKeyMap = new TreeMap<String, TreeSet<Integer>>();
        populateWordKeyMap();


        numVertices = countVertices();
        hypsDigraph = new Digraph(numVertices);
        addEdges();
    }









        /*Counting the number of vertices we'll need in our Digraph*/
    private int countVertices() {
        int count = 0;
        In hypes = new In(hypeSetFile);
        while (!hypes.isEmpty()) {
            String s = hypes.readLine();   //read the whole file as a string
            String[] sa = s.split(","); 

            for (int i = 0; i < sa.length; i++) {

            
                int temp = Integer.parseInt(sa[i]);
            
                if (count < temp) {
                    count = temp;
                }  

            }
        }
        return count + 1; 


    }

    private void addEdges() {
        In hypes = new In(hypeSetFile);
        while (hypes.hasNextLine()) {
            String s = hypes.readLine();   //read next line as a string
            String[] sa = s.split(","); 

            for (int i = 1; i < sa.length; i = i + 1) {




                hypsDigraph.addEdge(Integer.parseInt(sa[0]), Integer.parseInt(sa[i]));
            }

        }



    }

    private void getNouns() {
        In syns = new In(synSetFile);  
        while (syns.hasNextLine()) {
            String s = syns.readLine();   //read first line as a string
            String[] sa = s.split(",");   // split into String[] by entry
            String[] n = sa[1].split(" "); // split words into individual words
            for (int i = 0; i < n.length; i++) {

                
                nouns.add(n[i]);
            }
        

        }
    }  

    private void populateIDKeyMap() {
        In syns = new In(synSetFile);  

        while (syns.hasNextLine()) {
            String s = syns.readLine();   
            String[] sa = s.split(",");   // split into String[] by entry
            int key = Integer.parseInt(sa[0]);  
            


            String[] n = sa[1].split(" ");
            String[] value = new String[n.length];
            for (int i = 0; i < n.length; i++) {
                value[i] = n[i];
                
            }

    
            iDKeyMap.put(key, value);
        

        }

    }
 
    private void populateWordKeyMap() {
        In syns = new In(synSetFile);  

        while (syns.hasNextLine()) {
            String s = syns.readLine();   //read first line as a string
            String[] sa = s.split(",");   // split into String[] by entry
            int val = Integer.parseInt(sa[0]);
            String[] n = sa[1].split(" ");

            
            for (int i = 0; i < n.length; i++) {
                String key = n[i]; 
                if (wordKeyMap.containsKey(key)) {
                    TreeSet<Integer> value = wordKeyMap.get(key);

                    value.add(val);
                    wordKeyMap.put(key, value);

                } else {
                    TreeSet<Integer> value = new TreeSet<Integer>();


                    value.add(val);
                    wordKeyMap.put(key, value);
                }

                
                 
                
            }
            
        

        }

    }


    public Set<String> hyponyms(String word) {
        TreeSet<String> hyps = new TreeSet<String>();
        if (!wordKeyMap.containsKey(word)) {
            hyps.add(word);
            return hyps;


        }
        
        hyps.add(word);
        TreeSet<Integer> iDs = wordKeyMap.get(word);
        GraphHelper h = new GraphHelper();
        
        

        Set<Integer> d = h.descendants(hypsDigraph, iDs);


        Iterator<Integer> iter = d.iterator();
        while (iter.hasNext()) {
            int key = iter.next();
            String[] w = iDKeyMap.get(key);


            for (int i = 0; i < w.length; i++) {

                hyps.add(w[i]);
            }
        }



        return hyps;
    } 




    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }


    public Set<String> nouns() {
        return nouns;

    }
}


