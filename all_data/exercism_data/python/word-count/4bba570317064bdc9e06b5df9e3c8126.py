def word_count(phrase):
	counts = {}

	# Split phrase on whitespaces and return a list of words
	words = phrase.split()

	for word in words:
		counts[word] = counts.get(word, 0) + 1

	return counts
