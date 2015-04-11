import re

class Phrase(str):
	def word_count(self):
		word_map = {}
		split_expression = r"\W"
		for word in re.split(split_expression,self):
			if not word: #eliminates empty strings
				continue
			word = word.lower()
			word_map[word] = word_map.setdefault(word, 0) + 1
		return word_map
