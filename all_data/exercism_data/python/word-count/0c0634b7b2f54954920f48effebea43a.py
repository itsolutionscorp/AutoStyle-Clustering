import string
from collections import defaultdict

def normalize(word):
	"""
	return lower case word without punctuation
	"""
	return word.translate(None, string.punctuation).lower()


def word_count(text):
	"""
	count word occurences in text
	"""
	counts = defaultdict(int)
	for word in text.split():
		word = normalize(word)
		if not word:
			# skip empty words
			continue
			
		counts[word] += 1
	return counts
