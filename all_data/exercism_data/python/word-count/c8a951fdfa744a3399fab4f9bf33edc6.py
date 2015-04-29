class Phrase(object):
	def __init__(self, text):
		punc = '":;,./?!@#$%^&*()-_+=}]{[|\<>\''
		self.s = text.translate(None, punc).lower().split()
	
	def word_count(self):
		d = {}
		for w in self.s:
			if not w.isalnum() and len(w) < 2:
				continue
			d[w] = d.setdefault(w, 0) + 1
		return d
