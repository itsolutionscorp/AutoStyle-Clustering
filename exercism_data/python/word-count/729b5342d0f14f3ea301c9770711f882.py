from collections import Counter
def word_count(phrase):
	"""
	Input: String with words. Delimited by spaces, new lines, tabs.
	Output: Frequency of each word.
	"""
	return Counter(phrase.split())
