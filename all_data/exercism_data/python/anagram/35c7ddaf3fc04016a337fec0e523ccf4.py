class Anagram(object):
	def __init__(self, word):
		self.word = word

	def match(self, los):
		return [s for s in los if self.alphagram(s, self.word)]

	def alphagram(self, s1, s2):
		return s1 != s2 and sorted(s1.lower()) == sorted(s2.lower())
