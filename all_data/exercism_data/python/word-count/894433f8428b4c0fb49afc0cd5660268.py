def word_count(phrase):

	unique_words = {word for word in phrase.split()}
	return {word: phrase.split().count(word) for word in unique_words}
