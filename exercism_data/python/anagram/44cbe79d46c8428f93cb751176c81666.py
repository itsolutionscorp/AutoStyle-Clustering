# The anagram class
class Anagram(object):
	def __init__(self, word):
		self.word = word.lower()
		self.dict = {}

	# Alphabetically sort all the letters in a word
	def normalize(self, word):
		listw = list(word)
		listw.sort()
		return ''.join(listw)

	# Check if any of the words in the list are anagrams of the current instance
	def match(self, wordlist):
		normalized_word = self.normalize(self.word)
		req_length = len(normalized_word)
		result_list = []
		for word in wordlist:
			# Check word length
			if len(word) != req_length:
				continue
			# Check for same words
			if self.word == word:
				continue
			# Check for normalized forms
			if normalized_word == self.normalize(word.lower()):
				result_list.append(word)
		return result_list
