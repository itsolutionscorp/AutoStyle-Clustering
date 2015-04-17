def word_count(phrase):
	words = phrase.split()
	count = {}
	for word in words:
		word_occurrence = words.count(word)
		count[word] = word_occurrence
	return count
