def word_count(phrase):

	word_list = {}

	for word in phrase.split():
		if word not in word_list:
			word_list[word] = 1
		else:
			word_list[word] += 1
	return word_list
