def word_count(sentence):
	d = {}
	for word in sentence.split():
		if word in d:
			d[word] += 1
		else:
			d[word] = 1
	return d
