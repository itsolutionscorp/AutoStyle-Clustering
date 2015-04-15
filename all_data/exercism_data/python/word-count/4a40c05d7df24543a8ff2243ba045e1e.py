import string

class Phrase(object):
	def __init__(self, phrase):
		# Let's exclude all punctuation, it's not needed
		self.phrase = "".join(char for char in phrase if char not in string.punctuation)

	def word_count(self):
		words = self.phrase.split()			# Split by Whitespace
		count = {}

		for word in words:					# Look at each word
			if word.lower() in count:		# If the lowercase word (we're doing this case insensitively) is already in our count
				count[word.lower()] += 1	# Let's add 1 to the count of that word
			else:
				count[word.lower()] = 1		# Else we're adding a new word, and this is the first occurance of it
		
		return count
