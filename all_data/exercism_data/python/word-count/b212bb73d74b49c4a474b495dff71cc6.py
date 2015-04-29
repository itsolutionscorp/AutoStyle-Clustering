import collections

def word_count(sentence):
	return collections.Counter(sentence.split())
