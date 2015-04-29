def distance(word1, word2):
	hamming = 0
	for i in range(0,len(word1)):
		if word1[i] != word2[i]: hamming += 1
	return hamming
