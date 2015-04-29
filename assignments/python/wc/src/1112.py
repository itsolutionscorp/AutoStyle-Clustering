import re
def word_count(phrase):
	"""Count the occurrences of each word in a phrase."""
	wordCount = {}
	for word in phrase.split():						# Loop through the parsed words, counting them
		if word in wordCount:
			wordCount[word] += 1
		else:
			wordCount[word] = 1
	return wordCount
