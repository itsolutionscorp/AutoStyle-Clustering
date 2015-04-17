def word_count(word):
	new_word = word.split()
	words_dict = {}
	for x in new_word:
		words_dict[x] = new_word.count(x)
	return words_dict
