package ngordnet;
import java.util.*;
import edu.princeton.cs.introcs.In;

public class WordNet{
	private Map<Integer, List<String>> directory = new TreeMap<Integer, List<String>>();
    private Map<Integer, List<Integer>> h = new TreeMap<Integer, List<Integer>>();

	public WordNet(java.lang.String synsetFilename,
       java.lang.String hyponymFilename){
		In words = new In(synsetFilename);
		while(words.hasNextLine()){
			String line = words.readLine();
			String[] parts = line.split(",");
			String[] word = parts[1].split(" ");
			List<String> worddef = new ArrayList();
			for(String x: word){
				worddef.add(x);
			}
			worddef.add(parts[2]);
			directory.put(Integer.parseInt(parts[0]), worddef);
		}

		In hy = new In(hyponymFilename);
		while(hy.hasNextLine()){
			String line = hy.readLine();
			String[] parts = line.split(",");
			List<Integer> temp = new ArrayList<Integer>();
			for(int a = 1; a < parts.length; a++){
				temp.add(Integer.parseInt(parts[a]));
			}
			h.put(Integer.parseInt(parts[0]), temp);
		}
	}

	public boolean isNoun(String noun){
		for(Integer i: directory.keySet()){
			if(directory.get(i).contains(noun)){
				return true;
			}
		}
		return false;
	}

	public Set<String> nouns(){
		Set<String> set1 = new HashSet<String>();
		for(Integer i: directory.keySet()){
			for(int a = 0; a < directory.get(i).size()-1; a++){
				set1.add(directory.get(i).get(a));
			}
		}
		return set1;
	}

	public Set<String> hyponyms(String word){
		int key=-1;
		Set<String> set2 = new HashSet<String>();
		if(this.isNoun(word)){
			for(Integer i: directory.keySet()){
				if(directory.get(i).contains(word)){
					key = i;
				}
			}
			if(key<0){
				return null;
			}
			for(Integer j: h.get(key)){
				for(String k: directory.get(j)){
					set2.add(k);
				}
			}
			return set2;
		}
		return null;
		
	}

}