def word_count(phrase):
	histogram = {}
	for word in phrase.split():
		if word not in histogram:
			histogram[word] = 1
		else:
			histogram[word]+= 1
	return histogram
