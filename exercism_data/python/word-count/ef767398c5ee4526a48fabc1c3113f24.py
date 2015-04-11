def word_count(string):
	hash = {}
	for word in string.split():
		if word not in hash:
			hash[word] = 1
		else:
			hash[word] += 1

	return hash
