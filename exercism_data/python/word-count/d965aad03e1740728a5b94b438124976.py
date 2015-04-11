def word_count(p):
	words = p.split()
	return {w:words.count(w) for w in set(words)}
