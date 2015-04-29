def word_count(phrase):
	words = phrase.split()

	# Strip out duplicate words
	unique_words = [ ]
	for w in words:
		if w not in unique_words:
			unique_words.append(w)

	# Build a dictionary maping each word to its count
	result = {}
	for w in unique_words:
		result[w] = words.count(w)

	return result 
