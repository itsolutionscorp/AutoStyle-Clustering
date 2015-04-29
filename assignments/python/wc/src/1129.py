def word_count(sentence):
	dict = {}
	words = sentence.split()
	for word in words:
		if word in dict.keys():
			dict[word] += 1
		else:
			dict[word] = 1
	return dict
