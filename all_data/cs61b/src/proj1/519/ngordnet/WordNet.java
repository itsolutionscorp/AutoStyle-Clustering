package ngordnet;
import java.lang.*;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet{
    private TreeMap<Integer, HashSet<String>> Dictionary;
    private TreeMap<String, HashSet<Integer>> HypoDictionary;
    private HashSet<String>  Nouns;
    private HashSet<Integer> HyponymHashSet;
    private In synsetFile;
    private In hyponymFile;
    private Digraph wordenvironment;
    private static Boolean Hasinserted = true;
    private int indexed;
    private int vertices;

    //Read the inputted files, store them into two data structures, Diagraphs and TreeMaps
    public WordNet (String synset, String hyponyms){
        //read the files in
        synsetFile = new In(synset);
        hyponymFile = new In(hyponyms);
        //create data structures
        Nouns = new HashSet<String>();
        Dictionary = new TreeMap<Integer, HashSet<String>>();
        HypoDictionary = new TreeMap<String, HashSet<Integer>>();
        HyponymHashSet = new HashSet<Integer>();
        int key;
        int index;
        int boxes;
        while (this.synsetFile.hasNextLine()){ //This while loop will store the Key, Value into the HashMap Dictionary for every line in the synset file
        String[] synonymset = synsetReader(synsetFile.readLine()); //ie. "4,jump parachuting,dummy" 
        //s[0] = 4, s[1] = jump s[2] = parachute
        key = Integer.parseInt(synonymset[0]);
        index = 1;
        while (index < synonymset.length){// will loop for every synset in the synonym set (jump(1) parachute(2))
	String synsetplaceholder = synonymset[index]; //will hold "jump" when index is 1
	Nouns.add(synsetplaceholder); //jump is addded the hashSet
	HyponymHashSet.add(key);
	/*4 Conditions
	1. if the dictionary already contains key, just add the synset one at a time
	2. if the dictionary does not contain key, create a new HashSet, add the current synset to the hashset
	then add the synset to the Dictionary using the predetermined key
	3. if the HypoDictionary contains key, add hyponym
	4. if the HypoDictionary does not contain the key, create a new HashSet, then add hyponym
	*/
	//this is condtion one
	if(Dictionary.containsKey(key)){ 
	Dictionary.get(key).add(synsetplaceholder);
	}
	//this is condition two
	else if (!Dictionary.containsKey(key)){
	HashSet<String> tempsynsetholder = new HashSet<String>();
	tempsynsetholder.add(synsetplaceholder);
	Dictionary.put(key, tempsynsetholder);
	}
	//this is condition three
	if (HypoDictionary.containsKey(synsetplaceholder)){
		HypoDictionary.get(synsetplaceholder).add(key);
	}
	//this is condition four
	else if (!HypoDictionary.containsKey(synsetplaceholder)){
	HashSet<Integer> tempintegerholder = new HashSet<Integer>();
	tempintegerholder.add(key);
	HypoDictionary.put(synsetplaceholder, tempintegerholder);
	}
	index += 1;
	}	
	}
	//create the Digraph with the size of Dictionary HashSet
	wordenvironment = new Digraph(Dictionary.size());

	while (hyponymFile.hasNextLine()) {
		String[] HyponymSubset = hyponymReader(hyponymFile.readLine());
		boxes =  Integer.parseInt(HyponymSubset[0]);
		index = 1;
		vertices += 1;
		while (index < HyponymSubset.length) {
			int temp7 = Integer.parseInt(HyponymSubset[index]);
			wordenvironment.addEdge(boxes, temp7);
			index += 1;
		}
	}
	}


	public boolean isNoun(String wd) {
		if (Nouns.contains(wd)){
			return true;
		}
		else return false;
	}

	public Set<String> nouns() {
		return Nouns;
	}


	public HashSet<String> hyponyms(String wd){ //help given by Etan Khanal
		HashSet<String> descendants = new HashSet<String>();
		Set<String> temp = new HashSet<String>();
		HashSet<Integer> synsetofword = HypoDictionary.get(wd); 
		//boolean will return true if a word as been inserted into the HashSet
		if (Hasinserted == true){
		HashSet<Integer> descendantSynsets;
		}
		else {
		//set temporary HashSet to null if a word has not been inserted into the HashSet
		temp = null;
		}	
		HashSet<Integer> temporarydescendantSynsets;
		//why does this have to be a Set<Integer>
		Set<Integer> Descendantplaceholder = ngordnet.GraphHelper.descendants(wordenvironment, synsetofword);
		int temporaryindex = 0;
		int temporarycap = Descendantplaceholder.size();
		while(temporaryindex < temporarycap){
			for( String s : Dictionary.get(temporaryindex)){
				descendants.add(s);
			}
			temporaryindex += 1;
		}
		return descendants;
	}

	private static String[] synsetReader(String s){  //"0,jump parachute,dummy" //help given by Etan Khanal
		String[] temp;
		String[] finalarray;
		//String[] finalarray;
		String tempstring = s; //0,jump parachute,dummy
		String splaceholder = tempstring; //create another pointer to leave s the same
		int f1 = splaceholder.indexOf(",") + 1;
		int f2 = s.length();
		int f3 = splaceholder.indexOf(",");
		splaceholder = splaceholder.substring(f1, f2); //jump parachute,dummy
		splaceholder= splaceholder.substring(0, f3 );//jump parachute
		temp = splaceholder.split(" "); //array with jump as 0 and parachute as 1
		int newlength = temp.length + 1;
		finalarray = new String[newlength]; //final array that will account for the Key value
		finalarray[0]  = s.substring(0,s.indexOf(",")); //key value set as first int
		int newfinalarraylength = finalarray.length-1;
		if (Hasinserted == true){
			System.arraycopy(temp, 0, finalarray, 1, newfinalarraylength); //copy the temporary array into the final array, from position 1
		}
		return finalarray;
	
	}
	//ex "0,2,3,4"
	//Split the input string into an array, parts of the array are split by commas
	private static String[] hyponymReader(String s){
		String[] product = s.split(",");
		return product;
	}

}


//Private Methods
/*
	public static String extractWord(String line){
		int tempo = 0;
		String temp = null;

		i

		while (!line.startsWith(" ")) {
			if (temp == null){
				temp = line.substring(0,1);
				line = line.substring(1);
				tempo += 1;
			}
			else {
				temp = temp + line.substring(0,1);
				line = line.substring(1);
				tempo += 1;
			}
		}
		count = count + tempo;
		return temp;
	}

	private static String nextWord(String line){
		while (!line.startsWith(" ")) {
			line = line.substring(1);
		}
		line = line.substring(1);
		count += 1;
		return line;
	}
	private static String splitString(String line){
		String[] parts = line.split(",");
		String woComma = parts[0];
		return woComma;
	}

	private static String extractInt(String line){
		int tempo = 0;
		String temp  = null;

		while(!line.startsWith(",")){
			if (temp == null){
				temp = line.substring(0,1);
				line = line.substring(1);
				tempo += 1;
			}
			else{
				temp = temp + Integer.parseInt(line.substring(0,1));
				line = line.substring(1);
				tempo += 1;
			}
		}
		count = count + tempo;
		return temp;
	}

	private static String nextInt(String line){
		while(!line.startsWith(",")){
			line = line.substring(1);
		}
		line = line.substring(1);
		count += 1;
		return line;
	}
*/
