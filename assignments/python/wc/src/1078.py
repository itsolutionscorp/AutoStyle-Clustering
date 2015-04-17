import re
def word_count(phrase):
	"""Count the occurrences of each word in a phrase."""
	wordCount = {}
	phrase = re.sub(r'(\S)\s+(\S)', r'\1 \2', phrase)	# Ensure words are separated by a single space for parsing
	for word in phrase.split(' '):						# Loop through the parsed words, counting them
		if word in wordCount:
			wordCount[word] += 1
		else:
			wordCount[word] = 1
	return wordCount
