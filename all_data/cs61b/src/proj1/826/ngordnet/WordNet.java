package ngordnet;

import java.util.*;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
	private Map <Integer,Set<String>>wordmap;
	private Map <Integer,Set<Integer>>hypomap;
	private Map <String, Set<Integer>>searchmap;
	private Digraph digraph;
	public WordNet(String syn, String hyp) {
		In synin = new In (syn);
		wordmap = new HashMap <Integer,Set<String>> ();
		hypomap = new HashMap <Integer,Set<Integer>>();
		searchmap = new HashMap <String, Set <Integer>>();
		int count =0;
		while (synin.hasNextLine()){
			String line = synin.readLine();
			String [] splitline = line.split(",");
			String [] words = splitline[1].split(" ");
			Set <String>synset = new HashSet<String> ();
			for (String word: words){
				synset.add(word);
				if (!searchmap.containsKey(word)){
					Set<Integer> belongs = new HashSet<Integer> ();
					belongs.add(Integer.parseInt(splitline[0]));
					searchmap.put(word, belongs);					
				}
				else {
					Set<Integer>belongs = searchmap.get(word);
					belongs.add(Integer.parseInt(splitline[0]));
					searchmap.put(word, belongs);
				}
			}
			wordmap.put(Integer.parseInt(splitline[0]), synset);
			count++;
		}
		//digraph stuff
		digraph = new Digraph (count);
		
		//hyponym stuff
		In hypin = new In (hyp);
		while (hypin.hasNextLine()){
			String line = hypin.readLine();
			String [] splitline = line.split(",");
			if (!hypomap.containsKey(Integer.parseInt(splitline[0]))){
				Set <Integer> hyponyms = new HashSet <Integer>();
				for (int i=1;i<splitline.length;i++){
					hyponyms.add(Integer.parseInt(splitline[i]));
					digraph.addEdge(Integer.parseInt(splitline[i]), Integer.parseInt(splitline[0]));
					//have to double check direction of edge
				}
				hypomap.put(Integer.parseInt(splitline[0]), hyponyms);
			}
			else{
				Set <Integer> hyponyms = hypomap.get(Integer.parseInt(splitline[0]));
				for (int i=1;i<splitline.length;i++){
					hyponyms.add(Integer.parseInt(splitline[i]));
					digraph.addEdge(Integer.parseInt(splitline[i]), Integer.parseInt(splitline[0]));
				}
				hypomap.put(Integer.parseInt(splitline[0]), hyponyms);
			}
		}
	}

	
	public boolean isNoun (String word){
		return searchmap.containsKey(word);
	}
	
	public Set <String> nouns (){
		return searchmap.keySet();
	}
	public Set<String > hyponyms( String word){	
		Set <String> hyponyms = new HashSet<String>();
		if (isNoun(word)){
			Set <Integer> ids = searchmap.get(word);
			for (int id: ids){
				hyponyms.addAll(wordmap.get(id));
				Set <Integer> hypoids = hypomap.get(id);
				if (hypoids!= null){
					for (int hypoid:hypoids){
						hyponyms.addAll(hyponyms2(hypoid));
					}
				}
			}
		}
		return hyponyms;
	}
	
	private Set<String > hyponyms2( int id){	
		Set <String> hyponyms = new HashSet<String>();
		hyponyms.addAll(wordmap.get(id));
		Set <Integer> hypoids = hypomap.get(id);
		if (hypoids!= null){
			for (int hypoid:hypoids){
				hyponyms.addAll(hyponyms2(hypoid));
				
			}
		}
		return hyponyms;
	}
	

	private static void printWords (Map <Integer,Set<String>> wordmap){
		Set<Integer> keys = wordmap.keySet();
		for(Integer key: keys){
			Iterator<String> itr = wordmap.get(key).iterator();
			System.out.print(key+": ");
           while(itr.hasNext()){
               System.out.print( itr.next() + " ");
           }
           System.out.println();
	     }
	}
	private static void printSyn (Map <Integer,Set<Integer>> hypomap){
		Set<Integer> keys = hypomap.keySet();
		for(Integer key: keys){
			Iterator<Integer> itr = hypomap.get(key).iterator();
			System.out.print(key+": ");
           while(itr.hasNext()){
               System.out.print( itr.next() + " ");
           }
           System.out.println();
	     }
	}
}