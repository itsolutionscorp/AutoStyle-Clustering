class Anagram(str):
	def __init__(self, arg):
		self.pattern = arg.lower()
		
	def match(self, words ):
		l = []
		p = ''.join(sorted(self.pattern))
		for word in words:
			w = word.lower()
			if self.pattern == w :
				continue
			if p == ''.join(sorted(w)) :
				l.append(word)
		return l

