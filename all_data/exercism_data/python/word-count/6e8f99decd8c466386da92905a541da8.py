def word_count(string):
	# This is a good use for a dictionary. The dictionary will contain each
	# word, and the definition for the word will be the count of times it
	# appears in the string.
	
	# Get the incoming string into a list of words by splitting on whitespace.
	words = string.split()
	
	# Iterate over the words in the list. For each word, check whether it
	# appears in the dictionary. If it does, increment the count. If it does
	# not, add a key to the dictionary for the word, setting the count to 1.
	word_dict = {}
	
	for word in words:
		if word in word_dict.keys():
			# Word is in dictionary. Increment the count.
			word_dict[word] += 1
		else:
			# Word is not in dictionary. Add it with a count of 1.
			word_dict[word] = 1
	
	return word_dict
