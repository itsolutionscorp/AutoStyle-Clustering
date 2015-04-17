from collections import Counter
def word_count(sentence):
	words = Counter(sentence.split())
	return words
