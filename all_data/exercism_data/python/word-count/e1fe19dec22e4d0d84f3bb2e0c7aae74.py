import re
class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		self.phrase = re.sub('[\W_]+', ' ', self.phrase)
		print self.phrase
		words = self.phrase.split(" ")
		count_dict = {}
		for word in words:
			if word in count_dict:
				count_dict[word] += 1
			else:
				count_dict[word] = 1

		return count_dict
