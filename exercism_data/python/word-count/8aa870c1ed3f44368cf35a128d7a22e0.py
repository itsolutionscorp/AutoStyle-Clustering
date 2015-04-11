from collections import defaultdict

def word_count(string):
	dict = defaultdict(int)
	for word in string.split():
		dict[word] += 1

	return dict
