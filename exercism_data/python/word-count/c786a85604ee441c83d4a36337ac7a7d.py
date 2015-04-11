def word_count(word):
	words = word.split()
	uniqueWords = set(words)
	dict = {}
	for uniqueWord in uniqueWords:
		count = 0
		for word in words:
			if word == uniqueWord:
				 count+=1
		dict[s] = count
		
	return dict
