def word_count(str):
	wordcount = {}

	for word in str.split():
		if word in wordcount.keys():
			wordcount[word] = wordcount[word] + 1
		else:
			wordcount[word] = 1
	
	return wordcount
