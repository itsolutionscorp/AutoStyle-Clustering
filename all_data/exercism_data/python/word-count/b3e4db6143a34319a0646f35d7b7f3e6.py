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


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120


# # Word Count

# Write a program that given a phrase can count the occurrences of each word in that phrase.

# For example for the input "olly olly in come free"
	# olly: 2
	# in: 1
	# come: 1
	# free: 1
