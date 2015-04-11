def word_count(word):
	arr = word.replace("\n", " ").split()
	d = dict()
	for word in arr:
		d[word] = d.get(word, 0) + 1
	return d
