def word_count(message):
	word_list = message.split()
	word_counts = dict()
	for word in word_list:
		if word in word_counts:
			word_counts[word] += 1
		else:
			word_counts[word] = 1
	return word_counts
