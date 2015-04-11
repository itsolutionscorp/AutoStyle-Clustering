def word_count(input):
	counts = {}	
	for word in input.split():
		if not word in counts:
			counts[word] = 1
		else: 
			counts[word] = counts[word] + 1

	return counts
