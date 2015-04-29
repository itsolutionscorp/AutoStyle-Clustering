import collections
def word_count(test_string):
	cnt = collections.Counter()
	
	for word in test_string.split():
		cnt[word]+=1

	return cnt
