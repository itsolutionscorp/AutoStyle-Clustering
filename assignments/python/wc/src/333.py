def word_count(text):
	text = text.split()
	list_of_words = {}
	for i in text:
		if i in list_of_words:
			list_of_words[i] += 1
		else:
			list_of_words[i] = 1
	print list_of_words
	return list_of_words
