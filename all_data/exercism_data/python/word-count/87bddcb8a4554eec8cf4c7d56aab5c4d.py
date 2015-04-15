def word_count(sentence):
	words = sentence.split()
	l = {}
	for word in words:
		if word not in l:
			l[word] = 1
		else:
			l[word] += 1
	return l
