import string

class Bob:
	def hey(self, m):
		if m == None:
			return 'Fine. Be that way!'
		m = m.strip()
		if len(m) == 0:
			return 'Fine. Be that way!'
		if m == string.upper(m):
			return 'Woah, chill out!'
		if m[-1] == '?':
			return 'Sure.'
		return 'Whatever.'
