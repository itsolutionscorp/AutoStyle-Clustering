def word_count(phrase):
	'''
	Given a phrase can count the occurrences of each word in that phrase.
	'''
	phrase = phrase.split()
	phraseSet = set(phrase)
	phraseDict = {}
	for e in phraseSet:
		phraseDict[e] = phrase.count(e)
	return phraseDict