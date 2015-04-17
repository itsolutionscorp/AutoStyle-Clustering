/**
 * @(#)WordNet.java
 *
 *
 * @author 
 * @version 1.00 2015/3/11
 */
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.*;
import java.util.*;

public class WordNet {
	private int n;
	private String[] synsets;
	private Digraph hyponyms;
	private Hashtable<String,TreeSet<Integer>> nouns=new Hashtable<String,TreeSet<Integer>>();

	
    public WordNet(String syn, String hyp) {
    	try{
    	BufferedReader in = new BufferedReader(new FileReader(syn));
    	int id;
    	String[] line,words;
    	String input=in.readLine();
    	TreeSet<Integer> tmpindexes;
    	
    	ArrayList<String> tmpsynsets=new ArrayList<String>();
    	while (input!=null){
    		line=input.split(",");
    		id=Integer.parseInt(line[0]);
    		tmpsynsets.add(line[1]);
    		words=line[1].split(" ");
    		for (int i=0;i<words.length;i++){
    			if (nouns.containsKey(words[i])){
    				nouns.get(words[i]).add(id);
    			}
    			else{
    				tmpindexes=new TreeSet<Integer>();
    				tmpindexes.add(id);
    				nouns.put(words[i],tmpindexes);
    			}
    		}
    		input=in.readLine();
    	}
    	n=tmpsynsets.size();
    	hyponyms=new Digraph(n);
    	synsets=tmpsynsets.toArray(new String[n]);
    	
    	in=new BufferedReader(new FileReader(hyp));
    	
    	int hypernym;
    	input=in.readLine();
    	while(input!=null){
    		line=input.split(",");
    		if (line.length>=2){
    			hypernym=Integer.parseInt(line[0]);
    			for(int i=1;i<line.length;i++){
    				hyponyms.addEdge(hypernym,Integer.parseInt(line[i]));
    			}
    		}
    		input=in.readLine();
    	}
    	
    	}catch(IOException e){e.printStackTrace();
    	}
    	
    }
    public boolean isNoun(String word){
    	if (nouns.containsKey(word)){
    		return true;
    	}
    	return false;
    }
    
    private void add(TreeSet<String> hyp, String word, String synset){
    	String[] syn=synset.split(" ");
    	for(int i=0;i<syn.length;i++){
    		if(!(syn[i].equals(word))){
    			hyp.add(syn[i]);
    		}
    	}
    }
    private void allhyp(TreeSet<String> hyp, String word, int i){
    	for (int hypo: hyponyms.adj(i)){
    		if (!hyp.contains(synsets[hypo])){
    			add(hyp,word,synsets[hypo]);
    			allhyp(hyp,word,hypo);
    		}
    	}
    }
    public Set<String> hyponyms(String word){
    	TreeSet<Integer> ind;
		
    	TreeSet<String> hyp=new TreeSet<String>();
		hyp.add(word);
    	if (nouns.containsKey(word)){
    		ind=nouns.get(word);//indexes of all synsets which contain word
    		for (Integer key:GraphHelper.descendants(hyponyms,ind)){
    			for(String hypo:synsets[key].split(" ")){
    				hyp.add(hypo);
    			}
    		}
    	}
    	return hyp;
    }
    
    public Set<String> nouns(){
    	return nouns.keySet();
    }
}