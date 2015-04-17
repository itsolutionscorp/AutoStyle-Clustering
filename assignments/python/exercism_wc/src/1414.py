def word_count(phrase):
	phrase = phrase.split()
	words = {w:(phrase.count(w)) for w in phrase}
	return words
