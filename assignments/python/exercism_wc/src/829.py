def word_count(sentence):
	histogram = dict()
	for word in sentence.split():
		if word in histogram:
			histogram[word] += 1
		else:
			histogram[word] = 1
	return histogram
