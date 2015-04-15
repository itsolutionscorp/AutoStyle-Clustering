def word_count(sentence):
	d = dict()
	for word in sentence.split():
		d[word] = d.get(word,0) + 1
	return d
