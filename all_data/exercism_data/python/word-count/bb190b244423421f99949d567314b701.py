def word_count(sentence):
	dict = {}

	# Split sentence on whitespaces and return a list of words
	words = sentence.split()

	for word in words:
		# Word xas already found
		if word in dict.keys():
			dict[word] += 1

		# Word found for the first time
		else:
			dict[word] = 1

	return dict
