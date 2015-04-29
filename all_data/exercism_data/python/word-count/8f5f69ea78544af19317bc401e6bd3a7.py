import re
def word_count(phrase):
	keyList = re.sub('[\n]', ' ', phrase)
	keyList = keyList.split(' ')
	keyList = filter(bool, keyList)
	DictWithKeys = dict((el, 0) for el in keyList)
	for word in keyList:
		DictWithKeys[word] += 1
	return DictWithKeys
		
