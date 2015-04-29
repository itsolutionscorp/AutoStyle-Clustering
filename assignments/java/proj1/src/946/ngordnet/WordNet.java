package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;	
import ngordnet.GraphHelper;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class WordNet extends Object {
	private Set<String> words = new HashSet();
	private List<String> synset = new ArrayList();
	private Digraph d;

	public WordNet(String synsetFilename, String hyponymFilename) {
		List<String> hyponym = new ArrayList();
		In synin = new In(synsetFilename);
		In hypin = new In(hyponymFilename);
		String temp = synin.readLine();
		
		while (temp != null) {
			synset.add(temp);
			temp = synin.readLine();
		}
		
		temp = hypin.readLine();
		
		while (temp != null) {
			hyponym.add(temp);
			temp = hypin.readLine();
		}
		
		int index1 = 0, index2 = 0;
		
		for (int i = 0; i < synset.size(); i++) {
			String noun = synset.get(i);
			index1 = noun.indexOf(",");
			index2 = noun.lastIndexOf(",");
			noun = noun.substring(index1 + 1, index2);
			synset.set(i,noun);

			String[] haha = noun.split(" ");
			for (String objects : haha) {
				words.add(objects);
			}
		}
		
		d = new Digraph(synset.size());
		int num1 = 0, num2 = 0;
		
		for (String hyp : hyponym) {
			String[] entries = hyp.split(",");
			num1 = Integer.parseInt(entries[0]);
			int size = entries.length;
			for (int i = 1; i < size; i++) {
				num2 = Integer.parseInt(entries[i]);
				d.addEdge(num1, num2);
			}
		}
	}

	public boolean isNoun(String noun) {
		return words.contains(noun);
	}

	public Set<String> nouns() {
		return words;
	}

	public Set<String> hyponyms(String word) {
		Set<String> temp = new HashSet();
		Set<Integer> indexes = new HashSet();
		Set<Integer> edge = new HashSet();
		int size = synset.size();
		for (int i = 0; i < size; i++) {
			String noun = synset.get(i);
			String[] haha = noun.split(" ");
			for (String j : haha){
				if(j.equals(word)){
					indexes.add(i);
				}
			}
		}
		edge = GraphHelper.descendants(d, indexes);
		for (int index : edge) {
			String noun = synset.get(index);
			String[] haha = noun.split(" ");
			for (String objects : haha) {
				temp.add(objects);
			}
		}
		return temp;
	}
}