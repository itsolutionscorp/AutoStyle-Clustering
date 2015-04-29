from collections import Counter
def word_count(word):
	words = word.split()
	count = Counter(words)
	return count
