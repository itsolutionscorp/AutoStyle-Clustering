def word_count(text):
	import string
	wordlist = str.split(text)
	dict = {}
	newlist = []
	for word in wordlist:
		if word:
			newlist.append(word)
			dict[word] = newlist.count(word)
	return dict	
