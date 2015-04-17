def word_count(phrase):
	counts = {}
	words = phrase.split()
	for word in words:
		counts[word] = counts.get(word, 0) + 1
	return counts
