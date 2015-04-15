class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase
		
	def word_count(self):
		import re
		from collections import Counter
		to_lower = self.phrase.lower()
		strip_non_alpha = re.sub(r'([^\s\w]+)', '', to_lower)
		return Counter(strip_non_alpha.split())
