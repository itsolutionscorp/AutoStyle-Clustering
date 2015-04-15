import string
from collections import defaultdict

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		filtered_phrase = ''.join([c for c in self.phrase if c not in string.punctuation])
		freq_map = defaultdict(lambda:0, {})
		for word in filtered_phrase.split(' '):
			if word == '':
				# Handle case w/ more than one consecutive space
				continue
			freq_map[word.lower()] += 1
		return dict(freq_map)
