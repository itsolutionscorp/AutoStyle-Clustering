package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class WordNet {
    private In synset_reader;
    private In hypo_reader;
    private TreeMap<Integer,String> synsets;
    private Digraph hyponym_hold;
    public WordNet(String synset_url, String hypo_url){
        synset_reader = new In(synset_url);
        hypo_reader = new In(hypo_url);
        setUpStructures();
    }
   

   private void setUpStructures(){
       /*
       The magical method. This will do two things. One, it will set up a structure that will contains all the nouns (and synonym sets) with their respective definition.
       Then, there w
       ill be an integer Hyponym tree with the ID's of the words. 
       */
       synsets = new TreeMap<Integer,String>();
       //fill the synsets from the synsets file
       while(synset_reader.hasNextLine()){
           String s_set = synset_reader.readLine();
           StringTokenizer synset_tokens = new StringTokenizer(s_set,",");
           int syn_ID = Integer.parseInt(synset_tokens.nextToken());
           String syn_word = synset_tokens.nextToken();
           synsets.put(syn_ID,syn_word);
       }
       //fill up the hyponyms with digraph
       hyponym_hold = new Digraph(synsets.keySet().size());
       while(hypo_reader.hasNextLine()){
           String h_set = hypo_reader.readLine();
           StringTokenizer h_tokenizer = new StringTokenizer(h_set,",");
           int hyp_synset = Integer.parseInt(h_tokenizer.nextToken());
           ArrayList<Integer> hyponyms = new ArrayList<Integer>();
           while(h_tokenizer.hasMoreTokens()){
               hyponyms.add(Integer.parseInt(h_tokenizer.nextToken()));
           }
           for(int i = 0; i < hyponyms.size(); i++){
                hyponym_hold.addEdge(hyp_synset,hyponyms.get(i));
           }
       }
       
       
      
   }
   
   public Set<String> nouns(){
       TreeSet<String> alist = new TreeSet<String>();
       for(int ids : synsets.keySet()){
           String s = synsets.get(ids);
           StringTokenizer token_s = new StringTokenizer(s," ");
           while(token_s.hasMoreTokens()){
               alist.add(token_s.nextToken());
           }
           
       }
       return alist;
   }
   public boolean isNoun(String noun){
        for(String n : this.nouns()){
             if(n.equals(noun)){
                   return true;
             }
        }
        return false;
   }
   
   private int wordToID(String word){
       for(int ID : synsets.keySet()){
          if(synsets.get(ID).equals(word)){
              return ID;
          }
       }
       return 0;
   }
   
   
   
   public Set<String> hyponyms(String hypo){
        TreeSet<String> adj_nouns = new TreeSet<String>();
        adj_nouns.add(hypo);
        for(int synset_ID : hyponym_hold.adj(wordToID(hypo))){
            String syns = synsets.get(synset_ID);
            StringTokenizer t = new StringTokenizer(syns," ");
            while(t.hasMoreTokens()){
                adj_nouns.add(t.nextToken());
            }
            
        }
        return adj_nouns;
   }
   
       
       
       
       
}
    
    