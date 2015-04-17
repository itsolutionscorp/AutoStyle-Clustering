def word_count(sentence):
	words = sentence.split()
	dict = {}
	keys = []
	for word in words:
		if word not in keys:
			dict[word] = 1
			keys.append(word)
		else:
			dict[word] += 1
	return dict
