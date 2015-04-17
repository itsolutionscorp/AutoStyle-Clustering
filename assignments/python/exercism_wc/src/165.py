def word_count(sentence):
	count = {}
	words = sentence.split()
	for each in words:
		if each in count.keys():
			count[each] += 1
		else:
			count[each] = 1
	return count
