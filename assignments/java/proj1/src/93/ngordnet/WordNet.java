//Author: JinZhaoHong

package ngordnet;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.DirectedDFS;


public class WordNet {
    //optional: one of the possible data structures
    //Store all hyponyms
    private Set<String> synset;
    //the data structure to store all IDs of the synsets
    private Set<Integer> synsetIDs; 
    //the data structure of digrams
    private Digraph d;
    //map!!
    private TreeMap<Integer, String[]> tm;




    //Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    // eg: WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetIDs  = new HashSet<Integer>(); //initialize an empty set of synsetIDs

        In inSynset = new In(synsetFilename);
        In inHyponym = new In(hyponymFilename);
        In inHyponymCopy = new In(hyponymFilename);
        int digraphSize = 0;

        //find the number of commas in the file and count them
        //This will be the number of vertex, which is calculated through the number of commas.
        String[] s = inHyponymCopy.readAllStrings();

        for (String s1 : s) {
            String[] numbers = s1.split(",");
            for (String s2 : numbers) {
                digraphSize += 1;
            }
        }



        //construct the digraph
        d = new Digraph(digraphSize);
        System.out.println(d.V());

        //put data hyponym into the diagraph
        while (inHyponym.hasNextLine()) {
            String line = inHyponym.readLine(); //read one line of the input
            String[] numbers = line.split(","); 
            int length = numbers.length; 
            int head = Integer.parseInt(numbers[0]); //return the head of the vertex

            int index = 1;
            while (index < length) { //create arrows from the vertex
                int tail = Integer.parseInt(numbers[index]);
                d.addEdge(head, tail);
                index += 1;
            }
        }

        //initialize a new treemap;
        tm = new TreeMap();

        //put data from synsets into the treemap;
        while (inSynset.hasNextLine()) {
            String line = inSynset.readLine();
            //System.out.println("Printing the line for hyponym: " + line);
            String[] info = line.split(",");
            int key = Integer.parseInt(info[0]); // the first string represent the key 
            //System.out.println("this is the key: " + key);
            String synsets = info[1];
            String [] synsetsList = synsets.split(" "); //list of synsets
            synsetIDs.add(key); // put the IDs into the synsetID list.
            tm.put(key, synsetsList);
        }

    }



    //Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
    public Set<String> hyponyms(String word) {
        synset = new HashSet<String>(); //initialize an empty set of synset
        Set<Integer> sourceKey = findKey(word);

        //for(int i : sourceKey) {
        //System.out.println("this is the key of " + word + "ID: " + i);
    //  }

        /*if (index == -1){
            System.out.println("word not in the synsets");
            return;
        } */

        DirectedDFS dfdp = new DirectedDFS(d, sourceKey);
        TreeSet<Integer> reachable = new TreeSet<Integer>();

        for (int i = 0; i < d.V(); i += 1) {
            if (dfdp.marked(i)) {
                reachable.add(i);
            }
        }

        for (int key : reachable) {
            //System.out.println("This is the key of the hyponyms." + key);
            String [] nouns = tm.get(key);
            for (String s : nouns) {
                //System.out.println("ID : " + key + " Noun : "+ s );
                synset.add(s);
            }
        }

        return synset; 
    }




    private Set<Integer> findKey(String word) {  
        Set<Integer> sourceKey = new TreeSet<Integer>();
        Set<Integer> setOfKey = tm.keySet();
        for (int id : setOfKey) {
            String[] values = tm.get(id);
            for (String s : values) {
                if (s.equals(word)) {
                    sourceKey.add(id);
                }
            }
        }
        return sourceKey;

    }





    public boolean isNoun(String noun) {
        Set<Integer> setOfKey = tm.keySet(); //get the set of all keys 
        for (int id : setOfKey) {   //loop through each key
            String[] values = tm.get(id); 
            for (String s : values) {   //loop through each value associated with the key
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }




    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        Set<Integer> setOfKey = tm.keySet();
        for (int id : setOfKey) {
            String[] values = tm.get(id);
            for (String s : values) {
                nounSet.add(s);
            }
        }
        return nounSet;
    }



}
