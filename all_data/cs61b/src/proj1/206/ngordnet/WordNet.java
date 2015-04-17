package ngordnet;

import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;


public class WordNet{

	public  Digraph network;
	
	private Map<String,Integer> synsetMap;
	private Map<Integer,String> reversesynsetMap;


	public WordNet(String synsetFilename, String hyponymFilename){
		     synsetMap = new HashMap<String, Integer>();
        reversesynsetMap = new HashMap<Integer, String>();
        int countr = 0;

        In in = new In(synsetFilename);
        while(in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");      //command delimited
            Integer id = Integer.parseInt(fields[0]);
            String[] synset = fields[1].split(" "); //space delimited
            for (String aSynset : synset) {
                synsetMap.put(aSynset, id);       //lookup by Synset
            }
            reversesynsetMap.put(id, synset[0]);       //reverse lookup by ID, take the first Synset
            countr++;
        }
        in.close();

        network = new Digraph(countr);

        In in2 = new In(hyponymFilename);
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] fields = line.split(",");
            Integer id = Integer.parseInt(fields[0]);               //synset Id field is always first
            for (int i=1; i<fields.length; i++) {                   //subsequent fields are hypernym ids
                Integer hypernymId = Integer.parseInt(fields[i]);   //hypernymId fields
                network.addEdge(id, hypernymId);
            }
        }
        
        
        in2.close();

        
    }


	public Set<String> nouns(){
			return synsetMap.keySet();
	}

	public boolean isNoun(String noun){
			return synsetMap.containsKey(noun);
	}


	public Set<String> hyponyms(String word){
		//create a set a strings for all hyponyms in hyponym filename and return a set<string> with that and the fields in synset
		Set<String> hypSet = new TreeSet<String>();
		Set<Integer> instances = new TreeSet<Integer>();
		instances.add(synsetMap.get(word));
		
		Set<Integer> nodes = GraphHelper.descendants(this.network,instances);
		for (int node:nodes){
			for(String key: synsetMap.keySet()){
				if(nodes.contains(synsetMap.get(key))){
					hypSet.add(key);
				}
			}	

	}
	return hypSet;

}
}