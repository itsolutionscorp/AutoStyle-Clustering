def word_count(sentence):
	return {word: sentence.split().count(word) for word in set(sentence.split())}
