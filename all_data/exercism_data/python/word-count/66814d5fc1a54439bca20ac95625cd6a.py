def word_count(phrase=""):
	'''
	Given a phrase can count the occurrences of each word in that phrase
	'''
	words = {}
	for word in phrase.split():
		words[word] = words.get(word,0) + 1;
	return words
	
