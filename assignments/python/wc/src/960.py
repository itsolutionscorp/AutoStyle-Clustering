from collections import Counter
def word_count(string):
	words = string.split()
	return Counter(words)
