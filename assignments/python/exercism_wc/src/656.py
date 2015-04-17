def word_count(string):
	words_dict = {}
	words_array = string.split()
	for word in words_array:
		words_dict[word] = words_dict.get(word, 0) + 1
	return words_dict
