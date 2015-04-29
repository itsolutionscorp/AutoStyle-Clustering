'''
Try using sorted lowercase word as mapping value, instead of Counter() object
'''
from collections import Counter
def detect_anagrams(n, words):
	'''
	Return sublist of words which are anagrams of `n` but not `n` itself
	'''
	counters = {}
	lowercase_words = [x.lower() for x in words]
	for word in lowercase_words:
		counters[word] = Counter(word)
	n_lower = n.lower()
	n_count = Counter(n_lower)
	result = []
	for word, word_lower in zip(words, lowercase_words):
		if counters[word_lower] == n_count and word_lower != n_lower:
			result.append(word)
	return result
