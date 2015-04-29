from collections import defaultdict

def word_count(string):
	word_map = defaultdict(int)
	for word in string.split():
		word_map[word] += 1
	return word_map
