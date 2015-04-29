package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class WordNet{
	public Digraph solution;
	public String[] soasar;
	public List<int[]> hypeList;
	public WordNet(String synsetFilename, String hyponymFileName){
		In hypReader = new In(hyponymFileName);
		In synReader = new In(synsetFilename);
		int sinCounter = 0;

		while (synReader.hasNextLine()){
			synReader.readLine();
			sinCounter +=1;
		}

		soasar = new String[sinCounter];
		hypeList = new ArrayList<int[]>();
		synReader = new In(synsetFilename);

		for (int x = 0; x < sinCounter; x++){
			String sinLine = synReader.readLine();
			soasar[x] = sinLine.split(",")[1];
		}

		while (hypReader.hasNextLine()){
			String line = hypReader.readLine();
			String[] hypeNumbers = line.split(",");
			int[] hypeInt = new int[hypeNumbers.length];
			for (int x = 0; x < hypeNumbers.length; x++){
				hypeInt[x] = Integer.parseInt(hypeNumbers[x]);
			}
			hypeList.add(hypeInt);
		}

		solution = new Digraph(sinCounter);
		for (int x = 0; x < hypeList.size(); x++){
			int[] helper = hypeList.get(x);
			for (int y = 1; y<helper.length; y++){
				solution.addEdge(helper[0],helper[y]);
			}
		}
	}

	public Set<String> hyponyms(String noun){
		Set<Integer> synsetID = new TreeSet<Integer>();
		if (isNoun(noun)){
			for (int x = 0; x < soasar.length; x++){
				if (soasar[x].equals(noun)){
					synsetID.add(x);
				}
				else if (soasar[x].contains(noun + " ")){
					synsetID.add(x);
				}
				else if (soasar[x].contains(" "+noun)){
					synsetID.add(x);
				}
			}

			Set<Integer> end = GraphHelper.descendants(solution, synsetID);
			Set<String> answer = new TreeSet<String>();
			for (Integer element : end){
				String[] elements = soasar[element].split(" ");
				for (String thing : elements){
					if (!answer.contains(thing)){
						answer.add(thing);
					}
				}
			} 
			return answer;
		
		}
		else{
			return null;
		}
	}
	public boolean isNoun(String noun){
		for (String element : soasar){
			if (noun.equals(element)){
				return true;
			}
			else if (element.contains(noun + " ")){
				return true;
			}
			else if (element.contains(" " + noun)){
				return true;
			}
		}
		return false;	
	}
	public Set<String> nouns(){
		Set<String> nouns = new TreeSet<String>();
		for (String element : soasar){
				String[] elements = element.split(" ");
				for (String thing : elements){
					if (!nouns.contains(thing)){
						nouns.add(thing);
					}
				}
			} 
		return nouns;
		}	
}