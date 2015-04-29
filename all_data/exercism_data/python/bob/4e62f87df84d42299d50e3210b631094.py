class Bob:
	def hey(self, s):
		if '' == s.strip():
			return 'Fine. Be that way!'
		elif any(c.isalpha() for c in s) and s == s.upper():
			return 'Woah, chill out!'
		elif s[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
