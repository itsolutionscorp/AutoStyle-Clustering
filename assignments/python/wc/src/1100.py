def word_count(phrase):
	keyList = phrase.split(' ')
	cleanKeys = keyList
	for word in keyList:
		if word == '':
			cleanKeys.remove(word)
	DictWithKeys = dict((el, 0) for el in cleanKeys)
	for word in keyList:
		DictWithKeys[word] += 1
	return DictWithKeys
