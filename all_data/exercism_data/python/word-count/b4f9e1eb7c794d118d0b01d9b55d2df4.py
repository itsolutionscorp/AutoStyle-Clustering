from collections import Counter


def word_count(text):
	counter = Counter()
	words = text.split()
	for word in words:
		counter[word] += 1
	return counter
