def word_count(text):
	import string
	text = str.lower(text)
	wordlist = str.split(text)
	dict = {}
	newlist = []
	for word in wordlist:
		word = word.strip(string.punctuation)
		if word:
			newlist.append(word)
			dict[word] = newlist.count(word)
	return dict	
