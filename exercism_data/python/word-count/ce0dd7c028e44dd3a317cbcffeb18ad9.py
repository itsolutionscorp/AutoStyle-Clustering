import string
class Phrase:
	def __init__(self, s):
		self.s = s

	def word_count(self):
		d = {}
		s = self.s.lower()
		clean_string = ''.join(c for c in s if c.isalnum() or c.isspace())
		for w in clean_string.split():
			if w in d:
				d[w] += 1
			else:
				d[w] = 1
		return d
