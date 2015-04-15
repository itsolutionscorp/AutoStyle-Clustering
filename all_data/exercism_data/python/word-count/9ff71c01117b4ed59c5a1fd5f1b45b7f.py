def word_count(string):
	wordlist = string.split()
	wordcount = {}
	for x in wordlist:
		if x not in wordcount:
			wordcount[x] = wordlist.count(x)
	return wordcount
