def word_count(phrase):
	return {word: phrase.split().count(word) for word in phrase.split()}
