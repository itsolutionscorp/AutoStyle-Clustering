def word_count(strng):
	list_of_words = strng.split()
	return dict((word,list_of_words.count(word)) for word in list_of_words)
