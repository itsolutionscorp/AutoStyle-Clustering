def word_count(phrase):
	words = phrase.split()
	return dict([[x, words.count(x)] for x in set(words)])
