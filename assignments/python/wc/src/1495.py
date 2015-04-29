from collections import Counter
def word_count(phrase):
	keyList = phrase.split()
	keyList = filter(bool, keyList)
	cnt = Counter()
	for word in keyList:
		cnt[word] += 1
	return cnt
