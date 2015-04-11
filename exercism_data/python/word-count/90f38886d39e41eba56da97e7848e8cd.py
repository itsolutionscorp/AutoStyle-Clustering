def word_count(sentence):
	# DICTIONARY COMPREHENSION FTW
	return {word: sentence.split().count(word) for word in set(sentence.split())}
