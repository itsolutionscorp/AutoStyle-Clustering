def word_count(sentence):
	split = sentence.split()
	di = {}
	for word in split:
		if word in di:
			di[word] += 1
		else:
			di[word] = 1
	return di
