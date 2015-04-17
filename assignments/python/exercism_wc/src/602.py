from collections import Counter
from re import split
def word_count(sentence):
	words = [word for word in split("[^a-z0-9]", sentence.lower()) if word]
	return Counter(words)
