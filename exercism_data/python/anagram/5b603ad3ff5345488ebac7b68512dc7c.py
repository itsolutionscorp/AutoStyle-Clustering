class Anagram:
	def __init__(self,word):
		self.word=word.lower()
		self.word_list=list(self.word)
		self.word_list.sort()
		self.matches = []
	def match(self, match_list):
		for possible in match_list:
			pos_list = list(possible.lower())
			pos_list.sort()
			if self.word_list == pos_list and not(self.word == possible):
				self.matches.append(possible)
		return self.matches
