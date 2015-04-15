def word_count(phrase):
	'''
	Given a phrase can count the occurrences of each word in that phrase.
	'''
	# split string in to list of words
	phrase = phrase.split()
	
	# find unique words in list
	phraseSet = set(phrase)
	
	# create dict and set values to the count of items in phrase list
	phraseDict = {}
	for e in phraseSet:
		phraseDict[e] = phrase.count(e)

	return phraseDict
