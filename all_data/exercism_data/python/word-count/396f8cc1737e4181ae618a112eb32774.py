def word_count(input):
	counts = {}	
	for word in input.split():
		counts[word] = counts.get(word, 0) + 1

	return counts
