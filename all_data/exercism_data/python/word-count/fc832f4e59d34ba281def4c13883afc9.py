def word_count(phrase):
	counts = {}
	phrase = phrase.split()
	for word in phrase:
		counts[word] = counts.get(word, 0) + 1

	return counts
