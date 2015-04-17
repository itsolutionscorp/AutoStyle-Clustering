def word_count(phrase):
	words = phrase.split()
	word_dict = {}
	for word in words:
		if(word_dict.has_key(word)):
			temp = word_dict[word]
			word_dict[word] = temp + 1
		else:
			word_dict[word] = 1
	return word_dict
