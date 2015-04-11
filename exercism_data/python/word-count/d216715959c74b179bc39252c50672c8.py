def word_count(string):
	wordlist = string.split()
	wordcount = {}
	for word in wordlist:
		wordcount[word] = wordlist.count(word)
	return wordcount
