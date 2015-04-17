def word_count(wordcount):
	words = wordcount.split()
	freq = {}
	for word in words:
		if freq.has_key(word):
			freq[word] += 1
		else:
			freq[word] = 1
	return freq
