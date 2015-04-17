def word_count(string):
	wordlist = string.split()
	wordcount = {}
	for word in list(set(wordlist)): #so each word & count is only eval'd once
		wordcount[word] = wordlist.count(word)
	return wordcount
