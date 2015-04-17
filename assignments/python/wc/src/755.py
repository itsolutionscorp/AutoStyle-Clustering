def word_count(phrase):
	"""Counts the occurrences of each word in the input phrase.
	"""
	counts = {}
	for word in phrase.strip().split():
		counts[word] = counts.get(word, 0) + 1
	return counts
