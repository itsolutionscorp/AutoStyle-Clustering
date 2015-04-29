def word_count(word):
	wordlist = word.split()

	result = {}
	for word in wordlist:
		result[word] = wordlist.count(word)

	return result
