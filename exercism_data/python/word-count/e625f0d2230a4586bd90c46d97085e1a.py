def word_count(text):
	worddict = {}
	splitted = text.split()
	
	for word in splitted:
		if word not in worddict:
			worddict[word] = 1
		else:
			worddict[word] = worddict[word] + 1
	
	return worddict
