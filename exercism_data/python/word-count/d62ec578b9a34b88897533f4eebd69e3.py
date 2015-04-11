def word_count(sentence):
	word_dict = {}

	for word in sentence.split():
		if word in word_dict:
			word_dict[word] += 1
		else:
			word_dict[word] = 1
	
	return word_dict
