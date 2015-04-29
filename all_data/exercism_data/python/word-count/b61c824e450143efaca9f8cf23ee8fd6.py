#/usr/bin/env python
def word_count(word):
	count = {}
	for mm in word.split():
		if not mm in count.keys():
			count[mm] = 1
		else:
			count[mm] +=1
	return count
