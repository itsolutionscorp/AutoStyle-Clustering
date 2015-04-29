# Import the regex library
import re

# Let Phrase represent a wrapper for a phrase
class Phrase(object):
	# Initialize the instance with a string and initialize a dict
	def __init__(self, phrase):
		self.phrase = phrase
		self.dict = {}

	# Count the number of occurences of each word in the phrase
	def word_count(self):
		# Set the delimiter to detect any white-space & non alphanumeric character
		delimiter = '[\s\W]'
		# Use the regex's split()
		split_phrase = re.split(delimiter, self.phrase.lower())
		for word in split_phrase:
			if len(word) > 0:
				self.dict[word] = split_phrase.count(word)
		return self.dict
