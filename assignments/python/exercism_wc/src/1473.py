def word_count(text):
	'''Count the number of times each word appears in a text
	Arguments:
	text - String representing the text to count words in
	Returns:
	dictioniary of type {unique word: number of occurences}
	'''
	wordcounts = {}
	for word in text.split():
		wordcounts[word] = wordcounts.get(word,0) + 1
	return wordcounts
if __name__ == '__main__':
	print(word_count('one fish two fish red fish blue fish'))