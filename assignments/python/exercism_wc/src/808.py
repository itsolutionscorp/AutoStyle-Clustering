def word_count(str):
	words = str.split()
	word_counts = {}
	for word in words:
		if word in word_counts:
			word_counts[word] += 1
		else:
			word_counts[word] = 1
	return word_counts
