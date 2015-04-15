import re

class Phrase(object):

	def __init__(self, word_phrase):
		self.word_pattern = re.compile('[a-zA-z0-9]+')
		self.word_phrase = word_phrase
		self.word_counts = {}
		self.words = None

	def extract_words(self):
		phrase_elements = self.word_phrase.split()
		clean_elements_w_nones = [self.remove_punctuation(e) for e in phrase_elements]
		clean_elements = [element for element in clean_elements_w_nones if element != None]
		case_fixed_elements = [element.lower() for element in clean_elements]
		return case_fixed_elements

	def number_of_words(self, target_word):
		count = 0
		for word in self.words:
			if word == target_word:
				count += 1

		return count

	def remove_punctuation(self, single_word):
		clean_word = self.word_pattern.match(single_word)
		if clean_word:
			return clean_word.group()

	def word_count(self):
		self.words = self.extract_words()
		unique_words = set(self.words)
		for word in unique_words:
			how_many = self.number_of_words(word)
			self.word_counts[word] = how_many

		return self.word_counts
