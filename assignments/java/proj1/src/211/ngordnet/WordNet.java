package ngordnet;

import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/*
 *  @author Mitchell Huang
 */

public class WordNet {

	private String sFile;
	private String hFile;

	private Digraph dData;
	private Set<String> nouns = new HashSet<String>();
	private HashMap<Integer, String> synset = new HashMap<Integer, String>();

	private int vertices = 0;

	public WordNet(String s, String h) {
		sFile = s;
		hFile = h;

		In inSFile = new In(sFile);
		In inHFile = new In(hFile);

		while (!inSFile.isEmpty()) {
			String line1 = inSFile.readLine();

			if (line1 != null) {
				String[] processSyn = line1.split(",");
				String[] processSynSpace = processSyn[1].split(" ");

				if (processSynSpace.length != 1) {
					for (String e : processSynSpace) {
						nouns.add(e);
					}
				} else {
					nouns.add(processSynSpace[0]);
				}
				synset.put(Integer.parseInt(processSyn[0]), processSyn[1]);
			}

			vertices++;
		}

		dData = new Digraph(vertices);

		while (!inHFile.isEmpty()) {
			String line2 = inHFile.readLine();
			String[] processHyp = line2.split(",");

			if (processHyp.length > 2) {
				for (int x = 0; x < processHyp.length - 1; x++) {
					dData.addEdge(Integer.parseInt(processHyp[0]), Integer.parseInt(processHyp[x + 1]));
				}
			}

			if (processHyp.length == 2) {
				dData.addEdge(Integer.parseInt(processHyp[0]), Integer.parseInt(processHyp[1]));
			}

		}

	}

	public boolean isNoun(String n) {
		if (nouns.contains(n)) {
			return true;
		} else {
			return false;
		}
	}

	public Set<String> nouns() {
		return nouns;
	}

	public Set<String> hyponyms(String word) {
		int id = 0;
		Set<Integer> ids = new TreeSet<Integer>();
		Set<String> result = new HashSet<String>();

		for (Object key : synset.keySet()) {
			String[] split = synset.get(key).split(" ");

			for (int x = 0; x < split.length; x++) {
				if (word.equals(split[x])) {
					id = (Integer) key;
					ids.add(id);
					for (String e : split) {
						result.add(e);
					}
				}
			}
		}

		Set<Integer> desc = GraphHelper.descendants(dData, ids);

		for (Integer i : desc) {
			String hy = synset.get(i);

			String[] split = hy.split(" ");

			for (int x = 0; x < split.length; x++) {
				if (!result.contains(split[x])) {
					result.add(split[x]);
				}
			}
		}

		return result;
	}

}