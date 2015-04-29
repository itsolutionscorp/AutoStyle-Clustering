def word_count(phrase):
	words = phrase.split()
	unique_words = [ ]
	for w in words:
		if w not in unique_words:
			unique_words.append(w)
	result = {}
	for w in unique_words:
		result[w] = words.count(w)
	return result 
