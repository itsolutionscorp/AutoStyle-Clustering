import sys
import os
import numpy as np
from collections import defaultdict
import bisect
import math

def tf(word, code):
    return (code.count(word) / float(len(code)))

def number(word, code_dict):
    return sum(1 for w in code_dict.values() if word in w)

def idf(word, code_dict):
	return math.log(len(code_dict.keys()) / float(1 + number(word, code_dict)))

def tfidf(word, code, code_dict, score):
	return tf(word, code) * idf(word, code_dict) +(float(score))

def compute_markers(code_dict, scores_reverse):
	tfidf_vals = defaultdict(int)
	for key in code_dict.keys():
		for word in code_dict[key]:
			# tfidf_vals[word] = (tfidf_vals[word] + tfidf(word, code_dict[key], code_dict, scores_reverse[key])) / 2
			tfidf_vals[word] = tfidf(word, code_dict[key], code_dict, scores_reverse[key])
	sorted_vals = sorted(tfidf_vals.iteritems(), key=lambda (k,v): v)
	print(sorted_vals)
	return sorted_vals

def compute_code_markers(code, code_dict):
	tfidf_vals = defaultdict(int)
	for word in code:
		tfidf_vals[word] = (tfidf_vals[word] + tfidf(word, code, code_dict)) / 2
	# sorted_vals = sorted(tfidf_vals.iteritems(), key=lambda (k,v): v)
	return tfidf_vals

def main():
	markers = []#"set", "intersection", "list", "get_list", "get_string", "str", "BitAnd"]
	if len(sys.argv) != 3:
		print >> sys.stderr, "Usage: python_marker_rank.py <path to home directory> <starting submission>"
		exit(-1)
	start = sys.argv[2]
	home = sys.argv[1].rstrip("/")
	directory = home+ "/ast_text/"
	#get files
	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]

	#initialize lists and dictionaries
	code = {}
	scores = defaultdict(list)
	scores_reverse = {}
	hits = defaultdict(list)
	hits_reverse = {}

	for f in files:
		lines = open(f, "r").readlines()
		lines = [i.strip("\n") for i in lines]
		lines = [n.split(":")[1] for n in lines]
		code[os.path.split(f)[1].strip(".txt")] = lines
	for i in code:
		hit = 0
		for val in code[i]:
			if val in markers:
				hit+=1 
		hits[hit].append(i)
		hits_reverse[i] = hit
	directory = home+ "/abc_score/"
	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]
	for f in files:
		score = open(f, "r").readline().rstrip("\n")
		scores[float(score)].append(os.path.split(f)[1].strip(".txt"))
		scores_reverse[os.path.split(f)[1].strip(".txt")] = float(score)
	compute_markers(code,scores_reverse)
	m = np.loadtxt(home+"/distmatrix/python_dist_matrix", delimiter=" ")
	min_abc = min(scores)
	cur = scores_reverse[start]
	soln = start
	chain = [soln]
	sortedscores = sorted(scores)
	sortedhits = sorted(hits)
	def find_min(soln_list, soln):
		i, lowest = soln_list[0], m[int(soln)][int(soln_list[0])]
		for val in soln_list:
			if int(m[int(soln)][int(val)]) < int(lowest):
				i,lowest = int(val), m[int(soln)][int(val)]
		return str(i)

	def find_lower_hit(soln, hits_subset):
		if hits_subset == []:
			return -1
		h = []
		for item in hits_subset:
			h.append(hits_reverse[item])
		h = sorted(h)
		hit = hits_reverse[soln]
		lower = bisect.bisect(h, hit) -1
		k = 0
		while lower-k>0 and hit - h[lower-k] < 1:
			k+=1
		return hits[h[lower-k]]

	def get_better(soln):
		val = scores_reverse[soln]
		j,k = 0,0
		while sortedscores.index(val)-j>0 and val - sortedscores[sortedscores.index(val)-j] < 0.8:
			j+=1
		sc = set(scores[sortedscores[sortedscores.index(val)-j]])
		# while sortedscores.index(val)-j>0 and():
		# 	j+=1
		lh = find_lower_hit(soln, list(sc.intersection(set(sortedhits))))
		if lh == -1:
			return find_min(list(sc),soln)
		else:
			union = set(lh).intersection(sc)
			return find_min(list(union), soln)
	while scores_reverse[soln] > min_abc:
		cur = scores_reverse[soln]
		cur_hit = hits_reverse[soln]
		soln = get_better(soln)
		chain.append(soln)
		if cur in sortedscores:
			sortedscores.remove(cur) 
		if cur_hit in sortedhits:
			sortedhits.remove(cur_hit)
	for i,item in enumerate(chain):
		print("[--- score: " + str(scores_reverse[item]) + "  --  markers: "+ str(hits_reverse[item]) + "  --  id: "+str(item)+ " ---]")
		print open(home+"/code/"+item + ".py", "r").read()
		# if i != len(chain)-1:
		# 	print (str(item) + ' -> '+ str(chain[i+1]) + ";")
	print(chain)
if __name__ == '__main__':
	main()
