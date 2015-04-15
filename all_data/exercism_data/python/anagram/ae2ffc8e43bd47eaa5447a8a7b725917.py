import re

class Anagram:

	def __init__(self,string):
		self.string = string
		self.usrLetters = list(string.lower())
		self.usrLetters.sort()

	def match(self, array):
		match = []

		for i in range(len(array)):
			letterList = list(array[i].lower());
			letterList.sort()

			if array[i] == self.string: ## if there is a same word don't count it
				continue;
			elif letterList == self.usrLetters:
				match.append(array[i])

		return match
