import string
class Bob:
	def hey(self, s):
		if '' == s.strip():
			return 'Fine. Be that way!'
		elif len(set(s) & set(string.letters)) and s == s.upper():
			return 'Woah, chill out!'
		elif s[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
