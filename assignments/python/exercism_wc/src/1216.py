def word_count(words):
	wordsarray = words.split()
	result = {}
	while len(wordsarray)  > 0:
		word = wordsarray[0]
		result[word] = wordsarray.count(word)
		while word in wordsarray:
			wordsarray.remove(word)
	return result
