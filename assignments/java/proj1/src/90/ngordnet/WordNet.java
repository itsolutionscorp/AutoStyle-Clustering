package ngordnet;

import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;
// import java.util.TreeSet;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.io.BufferedInputStream;
// import java.io.*;
import java.util.*;

public class WordNet{
    Digraph g;
    Map<Integer, Set<String>> map_id_to_word;
    Map<String, Set<Integer>> map_word_to_id;

    public WordNet(String synsets_file, String hyponyms_file){ // TODO: how does readline pick the file it reads?

        // counts number of lines in file to get total vertex number
        int count = 0;


        map_id_to_word = new HashMap<Integer, Set<String>>();
        map_word_to_id = new HashMap<String, Set<Integer>>();
        //  make map to store id synset pairs and another to store word id pairs

        In syn_read = new In(synsets_file); // read two files
        In hypo_graph_edges = new In(hyponyms_file);




        while (syn_read.hasNextLine() ){
            String syn_line = syn_read.readLine(); //read synsets
            String[] syn_rawTokens = syn_line.split(","); //makes string array
            int id2 = Integer.parseInt(syn_rawTokens[0]); // get id

            String[] words_list = syn_rawTokens[1].split(" "); // split synset into a list of words

            Set<String> word_to_id_storage_set = new HashSet<String>(Arrays.asList(words_list));

            // put id --> words
            map_id_to_word.put(id2,word_to_id_storage_set);

            // put word--> id
            for (int i = 0; i <= words_list.length; i++) {
                if (map_word_to_id.containsKey(words_list[i])) {//word
                    Set<Integer> temp = map_word_to_id.get(words_list[i]);
                    temp.add(id2);
                    map_word_to_id.put(words_list[i] , temp);
                }
                else{
                    Set<Integer> temp_int= new HashSet<Integer>(id2);
                    map_word_to_id.put(words_list[i], temp_int);
                }
            }
            count++;
        }
        this.g = new Digraph(count); // digraph of right size

        while ( hypo_graph_edges.hasNextLine() ){ // fills in graph edges
            String hypo_line = hypo_graph_edges.readLine();
            String[] hypo_rawTokens = hypo_line.split(",");
            int id = Integer.parseInt(hypo_rawTokens[0]);

            for (int i =1; i <= hypo_rawTokens.length; i++){
                g.addEdge(id, Integer.parseInt(hypo_rawTokens[i]));
            }
        }
    }//end of constructor method

    public boolean isNoun(String noun) {
        return map_word_to_id.containsKey(noun);
    }

    public Set<String> nouns(){
        // @author assylias on StackOverflow

        //populate set
        Set<String> tmp = map_word_to_id.keySet();

        return tmp;
        // for (String s : tmp) {
        //     System.out.println(s);
        // }
    }

    public Set<String> hyponyms(String word_input){
        Set<Integer> val = map_word_to_id.get(word_input); // TODO: does this work or should val be an int?
        Set<Integer> desc = GraphHelper.descendants(g, val);
        HashSet<String> words_store = new HashSet<String>();

        for (int i : desc){ // may have to explicitly get keys in set
            Set<String> tmp = map_id_to_word.get(i); // read in strings one at a time instead of set of strings
            for (String s : tmp) {
             words_store.add(s);
            }
            // for (String s: words_store){
            //     // System.out.println(s);
            // }
        }
        return words_store;
    }

}

