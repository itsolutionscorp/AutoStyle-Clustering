def word_count(sentence):
	words_array = sentence.split()
	dictionary_of_words = {}
	for word in words_array:
		if word in dictionary_of_words:
			dictionary_of_words[word] = dictionary_of_words[word] + 1
		else:
			dictionary_of_words[word] = 1
	return dictionary_of_words
