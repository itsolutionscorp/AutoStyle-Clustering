from collections import defaultdict

def word_count(phrase):
	tokens = phrase.split()
	tallies = defaultdict(int)
	for t in tokens:
		tallies[t] += 1
	return tallies
