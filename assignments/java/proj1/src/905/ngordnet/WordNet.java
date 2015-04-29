package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
  	private Digraph hyponymsNet;
  	private List<String> synsetList = new ArrayList<String>();
  	public int nSynsets = 0;
  	private String[] currentLine;

  	public WordNet(String synsetsUrl, String hyponymsUrl) {
  		In synsetsFile = new In(synsetsUrl);
  		In hyponymsFile = new In(hyponymsUrl);
  		while (!synsetsFile.isEmpty()){
  			String[] currentLine = synsetsFile.readLine().split(",");
  			synsetList.add(currentLine[1]);
  			nSynsets += 1;
  		}
  		hyponymsNet = new Digraph(nSynsets);
  		while (!hyponymsFile.isEmpty()) {
  			String[] currentLine = hyponymsFile.readLine().split(",");
  			int vertex = 0;
  			for (int j = 0; j < currentLine.length; j += 1) {
  				if (j == 0) {
  					vertex = Integer.parseInt(currentLine[0]);
  				}
  				else {
  					hyponymsNet.addEdge(vertex, Integer.parseInt(currentLine[j]));
  				}
  			}
  		}
  	}

  	public boolean isNoun(String word) {
  		for (String synset: synsetList) {
  			if (synset.contains(word)) { 
  				String[] synsetWords = synset.split(" ");
  				for (int i = 0; i < synsetWords.length; i += 1) {
  					if (synsetWords[i].equals(word)) {
  						return true;
            }
          }
  			}
  		}
  		return false;
  	}

  	public Set<String> nouns() {
  		TreeSet<String> nounSet = new TreeSet<String>();
  		for (String synset: synsetList) {
  			String[] words = synset.split(" ");
  			for (String word: words) {
  				nounSet.add(word);
        }
  		}
  		return nounSet;
  	}

  	public Set<String> hyponyms(String word) {
  		TreeSet<Integer> ids = new TreeSet<Integer>();
  		for (int i = 0; i < nSynsets; i += 1) {
  			if (synsetList.get(i).contains(word)) {
  				String[] synsetWords = synsetList.get(i).split(" ");
  				for (int j = 0; j < synsetWords.length; j += 1)
  					if (synsetWords[j].equals(word))
  						ids.add(i);
  			}
  		}
  		Set<Integer> hyponymsIDSet = GraphHelper.descendants(hyponymsNet, ids);
  		TreeSet<String> hyponymsWordSet = new TreeSet<String>();
  		for (int id: hyponymsIDSet) {
  			String synset = synsetList.get(id);
  			String[] words = synset.split(" ");
  			for (String hyponym: words)
  				hyponymsWordSet.add(hyponym);
  		}
  		return hyponymsWordSet;
  	}
}
