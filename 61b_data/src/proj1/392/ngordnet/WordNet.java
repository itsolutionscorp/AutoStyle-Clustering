package ngordnet;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class WordNet {
    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
  
    private class Node {
        private int[] hyponyms;
        private String[] nouns;
        private String description;
        public Node(String[] n, int[] h, String d) {
            this.hyponyms = h;
            this.nouns = n;
            this.description = d;
        }

        public int[] getHyped() {
            return hyponyms;
        }

        public void addHyped(int[] hyp) {
            int[] temp = new int[this.hyponyms.length + hyp.length];
            for (int i = 0; i < hyponyms.length; i++) {
                temp[i] = hyponyms[i];
            }
            for (int i = 0; i < hyp.length; i++) {
                temp[i + hyponyms.length] = hyp[i];
            }
            this.hyponyms = temp;
        }

        public String[] getNouns() {
            return nouns;
        }

        public String toString() {
            String nounStr = "";
            for (String n: nouns) {
                nounStr = nounStr + n + ", ";
            }
            String hypStr = "";
            for (int h: hyponyms) {
                hypStr = hypStr + h + ", ";
            }
            return "=========\n" + nounStr + "\n" + hypStr + "\n=========";
        }
    }
  
    public WordNet(String synsets, String hyponyms) {
        int numSyns = 0;

        File synFile = new File(synsets);
        File hypFile = new File(hyponyms);

        try {
            Scanner synScan = new Scanner(synFile);
            while (synScan.hasNextLine()) {
                String line = synScan.nextLine();
                String[] tokens = line.split(",");

                int id = Integer.parseInt(tokens[0]);
                String[] nouns = tokens[1].split(" ");
                String desc = tokens[2];

                nodes.put(id, new Node(nouns, new int[] {}, desc));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid synsets file: " + synsets);
        }


        try {
            Scanner hypScan = new Scanner(new File(hyponyms));
            while (hypScan.hasNextLine()) {
                String line = hypScan.nextLine();
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                int[] hyp = new int[tokens.length - 1];
                for (int i = 0; i < hyp.length; i++) {
                    hyp[i] = Integer.parseInt(tokens[i + 1]);
                }
                nodes.get(id).addHyped(hyp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid hyponyms file: " + hyponyms);
        }
    }
      
  
    public Set<String> hyponyms(String word) {
        Set<String> hyps = new HashSet<String>();
        for (int id: hypID(word)) {
            for (String noun: nodes.get(id).getNouns()) {
                hyps.add(noun);
            }
        }
        return hyps;
    }

    private Set<Integer> hypID(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        for (int id: nodes.keySet()) {
            //Checks if this node contains target word
            for (String noun: nodes.get(id).getNouns()) {
                if (noun.equals(word)) {
                    Set<Integer> temp = recursiveHyps(id);
                    for (int i: temp) {
                        if (!ids.contains(i)) {
                            ids.add(i);
                        }
                    }
                }
            }
        }
        return ids;
    }

    private Set<Integer> recursiveHyps(int id) {
        Set<Integer> ids = new HashSet<Integer>();
        ids.add(id);
        if (nodes.get(id).getHyped().length > 0) {
            for (int i: nodes.get(id).getHyped()) {
                Set<Integer> temp = recursiveHyps(i);
                for (int j: temp) {
                    if (!ids.contains(j)) {
                        ids.add(j);
                    }
                }
            }
        }
        return ids;
    }

    public boolean isNoun(String noun) {
        for (String n: nouns()) {
            if (n.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Node node: nodes.values()) {
            for (String s: node.getNouns()) {
                nouns.add(s);
            }
        }
        return nouns;
    }
}
