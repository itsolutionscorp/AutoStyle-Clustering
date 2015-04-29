def word_count(text):
	'''Count the number of times each word appears in a text

	Arguments:
	text - String representing the text to count words in

	Returns:
	dictioniary of type {unique word: number of occurences}
	'''
	# Initialize empty dictionary
	wordcounts = {}
	# Use built-in function split() to seperate words
	for word in text.split():
		# Increase the number of occurences of a given word
		if word in wordcounts:
			wordcounts[word] += 1
		# First occurence of a given word
		else:
		 wordcounts[word] = 1
	return wordcounts

# Standalone debugging
if __name__ == '__main__':
	print(word_count('one fish two fish red fish blue fish'))
