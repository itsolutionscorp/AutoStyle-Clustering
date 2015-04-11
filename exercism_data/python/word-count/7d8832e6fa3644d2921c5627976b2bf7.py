import string

class Phrase():

	def __init__(self, phrase):
		self.phrase = phrase



	def word_count(self):
		dist = {}
		phrase = self.prepare_phrase()
		tokens = phrase.split()					
		word_set = set(tokens)					# Remove duplicates
		for word in word_set:
			dist[word] = tokens.count(word)		# Get word count from original list

		return dist



	def prepare_phrase(self):
		""" Prepare phrase by converting to lowercase and remove punctuations. """
		phrase = self.phrase.lower()
		phrase = ''.join([c for c in phrase if c not in string.punctuation])

		return phrase
