def word_count(sentence):
	d = dict()
	for word in sentence.split():
		if word not in d:
			d[word] = 1
		else: 
			d[word] = d[word] + 1
	return d
