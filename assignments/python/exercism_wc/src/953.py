def word_count(phrase):
	"""Counts the occurrences of each word in the input phrase.
	"""
	counts = {}
	splitPhrase = phrase.strip().split()
	for word in splitPhrase:
		counts[word] = counts.get(word, 0) + 1
	return counts
