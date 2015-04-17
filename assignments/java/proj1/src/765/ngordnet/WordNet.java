package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import ngordnet.GraphHelper;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;


public class WordNet {
	private Digraph graph;
	private HashMap<Integer, String[]> words= new HashMap<Integer, String[]>();
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	In syn = new In(synsetFilename);
    	In hyp = new In(hyponymFilename);
    	String[] synString=syn.readAllLines();
    	String[] hypString=hyp.readAllLines();
    	// String lastSyn=synString[synString.length-1];
    	// String lastNumber="";
    	// for (int i=0;i<lastSyn.length() ; i++ ) {
    	// 	char c=lastSyn.charAt(i);
    	// 	if (c==',') {
    	// 		break;
    	// 	}
    	// 	lastNumber=lastNumber+c;
    	// }
    	// int lNum=Integer.parseInt(lastNumber);
    	graph= new Digraph(synString.length);
    	for (int i=0;i<synString.length ;i++ ) {
    		String currString=synString[i];
    		int totalSpaces=0;
    		int commaCount1=0;
    		for (int k=0;k<currString.length(); k++ ) {
    			char c=currString.charAt(k);
    			if (c==',') {
    				commaCount1+=1;
    			}
    			else if (commaCount1==2){
    				break;
    			}
    			else if (commaCount1==1 && c==' '){
    				totalSpaces+=1;
    			}  			
    		}
    		String[] w=new String[totalSpaces+1];
    		w[0]="";
    		String number="";
    		int spaceCount=0;
    		int commaCount=0;
    		for (int j=0;j<currString.length();j++ ) {
    			char c= currString.charAt(j);
    			if (c==',') {
    				commaCount+=1;
    			}
    			else if (commaCount==2) {
    				break;
    			}
    			else if (c==' ') {
    				spaceCount+=1;
    				w[spaceCount]="";
    			}
    			else if (commaCount==0){
    				number=number+c;
    			}
    			else if (commaCount==1){
    				w[spaceCount]=w[spaceCount]+c;
    			}
    					
    		}
    		Integer num=Integer.parseInt(number);
    		words.put(num,w);    		
    	}
    	for (int i=0; i<hypString.length;i++) {
    		String currString=hypString[i];
    		int totalCommas=0;
    		for (int l=0;l<currString.length(); l++ ) {
    			char c=currString.charAt(l);
    			if (c==',') {
    				totalCommas+=1;
    			}
    			
    		}
    		String edge1="";
    		String[] edge2= new String[totalCommas];
    		int commaCount=0;
    		for (int j=0;j<currString.length();j++ ) {
    			char c= currString.charAt(j);
    			if (c==',') {
    				commaCount+=1;
    				edge2[commaCount-1]="";
    			}
    			else if (commaCount==0){
    				edge1=edge1+c;
    			}
    			else {
    				edge2[commaCount-1]=edge2[commaCount-1]+c;
    			}

    		}
    		int e1=Integer.parseInt(edge1);
    		for (int k=0; k<edge2.length; k++) {
    			graph.addEdge(e1, Integer.parseInt(edge2[k]));    			
    		}
    	}


    	


    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
    	Set<String> setOfNouns=this.nouns();
    	return setOfNouns.contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	HashSet<String> out= new HashSet<String>();
    	for (String[] synset : words.values()) {
    		for (int i=0;i<synset.length ;i++ ) {
    			out.add(synset[i]);			
    		}
    	}
    	return out;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
    	if (this.isNoun(word)){
    		HashSet<Integer> keys= new HashSet<Integer>();
    		HashSet<String> out= new HashSet<String>();

    		for (Map.Entry<Integer, String[]> entry : words.entrySet()) {
			    String[] synset = entry.getValue();
			    for (int i=0; i<synset.length ;i++ ) {
			    	if (word.equals(synset[i])){
			    		keys.add(entry.getKey());
			    	}			    	
			    }			    
			}
			Set<Integer> hyponymKeys= GraphHelper.descendants(graph, keys);
			for (Integer val: keys) {
				hyponymKeys.add(val);
			}
			for (Integer val: hyponymKeys){
				String[] syn = words.get(val);
				for (int i=0; i<syn.length; i++) {
					out.add(syn[i]);
				}
			}
			return out;
    	}
    	else {throw new IllegalArgumentException();}

    }

}