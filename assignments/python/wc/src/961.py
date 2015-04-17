def word_count(input):
	phrase = ''.join([x if x.isalnum() else ' ' for x in input])
	histogram = {}
	for word in phrase.split():
		uncased = word.lower()
		histogram[uncased] = histogram.get(uncased,0)+1
	return histogram
