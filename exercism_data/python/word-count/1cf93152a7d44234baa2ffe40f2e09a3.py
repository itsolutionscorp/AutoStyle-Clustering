def word_count(sentence):
	word_dict = {}
	word_list = map(str, sentence.split())
	
	for word in word_list:
		if word in word_dict:
			word_dict[word] = word_dict[word] + 1
		else:
			word_dict[word] = 1
	return word_dict
