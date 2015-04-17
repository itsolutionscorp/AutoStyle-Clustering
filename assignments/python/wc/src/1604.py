def word_count(string):
	words = string.split()	
	occurrences = {}
	for word in words:
		occurrences[word] = occurrences[word] +1 if word in occurrences else 1	
	return occurrences
