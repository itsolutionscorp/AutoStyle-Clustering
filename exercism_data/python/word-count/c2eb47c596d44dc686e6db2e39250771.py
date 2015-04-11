def word_count(phrase):
	word_list = {}
	for word in phrase.rsplit():
		word_list[word] = word_list.get(word,0) + 1
		print word
	return word_list
