from collections import Counter
def word_count(text):
	'''Count the number of times each word appears in a text
	Arguments:
	text - String representing the text to count words in
	Returns:
	dictioniary of type {unique word: number of occurences}
	'''
	return Counter(text.split())
if __name__ == '__main__':
	print(word_count('one fish two fish red fish blue fish'))
