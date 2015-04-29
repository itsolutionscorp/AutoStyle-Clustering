from collections import Counter
def word_count(sentence):
	words = sentence.split()
	word_counts = Counter(words)
	return word_counts 
