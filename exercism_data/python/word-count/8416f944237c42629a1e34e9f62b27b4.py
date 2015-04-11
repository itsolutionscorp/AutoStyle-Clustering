import re

class Phrase:
	def __init__(self, phrase):
		self._encoding = "utf8"
		self._to_decode = False
		self._phrase = self.decodeString(phrase)

	def decodeString(self, phrase):
		if type(phrase) is not unicode:
			self._to_decode = True
			phrase = phrase.decode(self._encoding)
		return phrase

	def encodeCount(self, count):
		if self._to_decode:
			decodedCount = {}
			for word in count:
				decodedCount[word.encode(self._encoding)] = count[word]
			return decodedCount
		return count

	def word_count(self):
		# if _word_count not in self
			def addWord(count, word):
				if word not in count:
					count[word] = 0
				count[word] = count[word] + 1
				return count 

			words = re.findall("\w+",self._phrase.lower(),re.U);
			return self.encodeCount(reduce(addWord, words, {}))		
