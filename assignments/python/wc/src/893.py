from collections import Counter
def word_count(sentence):
	words = sentence.split()
	cnt = Counter()	
	for word in words:
		cnt[word] += 1
	return cnt
