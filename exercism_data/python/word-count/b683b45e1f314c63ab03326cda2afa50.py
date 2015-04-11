def word_count(phrase):
	keyList = phrase.split(' ')
	cleanKeys = keyList
	for word in keyList:
		# word = word.replace('\n', '')
		if word == '':
			cleanKeys.remove(word)
	DictWithKeys = dict((el, 0) for el in cleanKeys)
	for word in keyList:
		DictWithKeys[word] += 1
	return DictWithKeys
		



# keysx = ('hello there tiger tiger tiger'.split(' '))
# print(dict((el, 0) for el in keysx ))

# print(word_count("word"))		
# print(word_count("one of each"))		
# print(word_count("car : carpet as java : javascript!!&@$%^&"))		
# print(word_count("testing 1 2 testing"))
# print(word_count("go Go GO"))
# print(word_count("wait for       it"))
# print(word_count("rah rah ah ah ah\nroma roma ma\nga ga oh la la\nwant your bad romance"))
