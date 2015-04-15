def word_count(phrase):
	counts = {}
	for word in phrase.split():
		counts[word] = counts.get(word, 0) + 1
	return counts
