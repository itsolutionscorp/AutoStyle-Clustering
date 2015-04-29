def word_count(phrase):
	words = ' '.join(phrase.split())
	word_counts = dict()

	for word in words.split():
		if word in word_counts:
			word_counts[word] = word_counts[word] + 1
		else:
			word_counts[word] = 1

	return word_counts
