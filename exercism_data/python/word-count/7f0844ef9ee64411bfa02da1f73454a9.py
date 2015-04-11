def word_count(phrase):
	"""Counts the occurrences of each word in the input phrase.
	"""
	counts = {}
	# strip leading/trailing whitespace and then split on whitespace
	for word in phrase.strip().split():
		# increment count in dictionary by one for each word found
		# if word has not yet been seen, start with 0
		counts[word] = counts.get(word, 0) + 1

	return counts
