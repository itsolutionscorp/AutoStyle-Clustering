def word_count(string):
	word_counts = {}
	for word in string.split():
		word_counts[word] = word_counts.get(word, 0) +1
	return word_counts
