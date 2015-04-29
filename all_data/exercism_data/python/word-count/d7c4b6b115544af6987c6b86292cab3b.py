def word_count(phrase):
	'''
	Given a phrase can count the occurrences of each word in that phrase.
	'''
	# split string in to list of words
	phrase = phrase.split()
	
	# find unique words in list
	phraseSet = set(phrase)
	
	# create dict of unique words each with a count of zero
	phraseDict = {}
	for e in phraseSet:
		phraseDict[e] = 0
	
	# increment values in dict each time there is a match in word list
	for e in phraseSet:
		for f in phrase:
			if e == f:
				phraseDict[e] += 1
	return phraseDict
