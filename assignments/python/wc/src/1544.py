def word_count(words):
	'''Counts the number of occurrences of a word in a phrase'''
	countlist = {}
	if words:
		wordlist = words.split()
		for word in wordlist:
			countlist[word] = wordlist.count(word)
	return countlist
