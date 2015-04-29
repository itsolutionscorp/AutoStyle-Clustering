package ngordnet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
	public ArrayList<String> synList = new ArrayList<String>();
	public ArrayList<String> hypList = new ArrayList<String>();
	public HashMap hMapWordsKtoV = new HashMap<Integer, String>();
	public HashMap hMapWordsVtoK = new HashMap<String, Integer>();
	public HashMap hMapHyponyms = new HashMap<String, Integer>();
	public WordNet(){

	}

	public WordNet(String synsetFilename, String hyponymFilename){
		In synset = new In(synsetFilename);
		In hyponym = new In(hyponymFilename);
		while(synset.hasNextLine()){
			synList.add(synset.readLine());
		}
		while (hyponym.hasNextLine()){
			hypList.add(hyponym.readLine());
		}
		createHMaps();
	}


	public boolean isNoun(String noun){
		return false;
	}

	public Set<String> nouns(){
		Set<String> nSet = new HashSet<String>();
		Iterator<Integer> keyIterator = hMapWordsKtoV.keySet().iterator();
		while(keyIterator.hasNext()){
			Integer hashKey = keyIterator.next();
			String [] vals = ((String) hMapWordsKtoV.get(hashKey)).split(" ");
			int i = 0;
			while (i < vals.length){
				nSet.add(vals[i]);
				i += 1;
			}
		}
		return nSet;
	}

	public Set<String> hyponyms(String n){ 
		int i = 0;
		Set<String> hSet = new HashSet<String>();
		Integer key = (Integer) hMapWordsVtoK.get(n);
		if (!hMapHyponyms.containsKey(key)){ //check if map has key of n
			throw new IllegalArgumentException("Word does not have hyponyms");
		}
		else{
			ArrayList hyponymsOfNoun = (ArrayList) hMapHyponyms.get(key); //arraylist of hyponyms at key of map
			while (i < hyponymsOfNoun.size()){
				Integer hID = (Integer) hyponymsOfNoun.get(i); //hyponym id of n
				String h = (String) hMapWordsKtoV.get(hID);
				String [] hSplit = h.split(" "); //nouns of each id
				int z = 0;
				while (z < hSplit.length){
					hSet.add(hSplit[z]);
					z += 1;
				}
				i += 1;
			}
			return hSet;
		}
	}

	private void createHMaps(){ //creates 2 hashmaps for the files synset and hyponym
		int i = 0, id;
		String words;
		while (i < synList.size()){
			String sLine = synList.get(i);
			String [] parts = sLine.split(",");
			id = Integer.parseInt(parts[0]);
			words = parts[1];
			hMapWordsKtoV.put(id, words);
			hMapWordsVtoK.put(words, id);
			i+= 1;
		}
		i = 0;

		while (i < hypList.size()){
			int x = 0, id2, hyponymsOfID = 0;
			ArrayList<Integer> hList = new ArrayList<Integer>(); //creates an arraylist within the hashmap
			String hLine = hypList.get(i);
			String [] parts2 = hLine.split(",");
			id2 = Integer.parseInt(parts2[0]);
			System.out.println(id2);
			while(x < parts2.length){
				hyponymsOfID = Integer.parseInt(parts2[x]);
				hList.add(hyponymsOfID);
				x += 1;
			}
			hMapHyponyms.put(id2, hList);
			i += 1;
		}
	}

	public static void main(String [] args){
		
	}
}