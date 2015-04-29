def word_count(sentence):
	wordlist = sentence.split() 
	counts = dict((word,wordlist.count(word)) for word in wordlist) 
	return counts
