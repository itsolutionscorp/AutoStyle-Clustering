def word_count(string):
	words = string.split()	
	occurrences = {}
	for word in words:
		occurrences[word] = 0
	for word in words:
		occurrences[word] += 1		
	return occurrences
