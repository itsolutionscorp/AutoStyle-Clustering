package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.Arrays;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.StdIn;


public class WordNet{ // COMPLETED
	private Map<Integer, Set<String>> wordMap;
	private Digraph wordGraph;
	

	public WordNet(java.lang.String synsetFilename,java.lang.String hyponymFilename) {
		
		
		try{
		wordMap = new HashMap<Integer,Set<String>>();
        BufferedReader in = new BufferedReader(new FileReader(new File(synsetFilename)));
        String line = in.readLine();
        while(line!=null){
        	String[] arrayLine = line.split(",");
        	String []arrayWords = arrayLine[1].split(" ");
        	Set<String> synset = new HashSet<String>();  //MAY POSSIBLY OVERWRITE OLD SYNSET
        	for(int i = 0;i<arrayWords.length;i++){
        		synset.add(arrayWords[i]);
        	}
        	wordMap.put(Integer.parseInt(arrayLine[0]), synset);
        	line = in.readLine();
        }
        wordGraph = new Digraph(wordMap.size());
        BufferedReader in2 = new BufferedReader(new FileReader(new File(hyponymFilename)));
        String line2 = in2.readLine();
        while(line2!=null){
        	String[] arrayints = line2.split(",");
        	for(int i = 1;i<arrayints.length;i++){
        		wordGraph.addEdge(Integer.parseInt(arrayints[0]),Integer.parseInt(arrayints[i]));
        	}
        	line2 = in2.readLine();	
        }
	}
		
	catch(IOException ex){ System.out.println("opps u dun goofed");}
	}

	
	public boolean isNoun(String word){
		for(Set<String> values: wordMap.values()){
			if(values.contains(word)){return true;}
		}
		return false;
	}
	
	public Set<String> nouns(){
		Set<String> nouns = new HashSet<String>();
		for(Set<String> values: wordMap.values()){
			for(String word:values){
				nouns.add(word);
			}
		}
		return nouns;
	}
	
	public Set<String> hyponyms(String word){
		Set<String> hyponyms = new HashSet<String>();
		Set<Integer> synsetIDs = new HashSet<Integer>(); //synonyms
		Set<Integer> descendantIDs = new HashSet<Integer>(); //hyponyms
		
		for(Integer key: wordMap.keySet()){
			if(wordMap.get(key).contains(word)){
				//System.out.println("key: "+key+" synset: "+wordMap.get(key)+" word: "+word);				
				synsetIDs.add(key);
			}
		}
		descendantIDs = GraphHelper.descendants(wordGraph,synsetIDs);
		//System.out.println("descendantIDs: "+descendantIDs);
		for(Integer key:synsetIDs){
			for(String sWord:wordMap.get(key)){
				//if(word.equals(sWord)){continue;}
					hyponyms.add(sWord);
			}
		}
		for(Integer key:descendantIDs){
			for(String dWord:wordMap.get(key)){
				hyponyms.add(dWord);
			}
		}
		return hyponyms;
		
	}	
}