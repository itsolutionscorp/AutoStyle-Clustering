from collections import Counter
def word_count(phrase):
	keyList = phrase.split()
	keyList = filter(bool, keyList)
	return Counter(keyList)
