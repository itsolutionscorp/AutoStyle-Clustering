def word_count(phrase):
	words = phrase.split()
	return {x:words.count(x) for x in words}
