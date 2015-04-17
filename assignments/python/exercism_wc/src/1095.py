def word_count(phrase):
	word_dict = {}
	for i in phrase.split():
		if i in word_dict:
			word_dict[i] += 1
		else:
			word_dict[i] = 1
	return word_dict
