def word_count(phrase):
	words = phrase.strip().split()
	unique_words = set(words)
	return {w: words.count(w) for w in unique_words}
