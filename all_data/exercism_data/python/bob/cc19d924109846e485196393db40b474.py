class Bob(object):
	def hey(self, s):
		if not s or s.isspace():
			return "Fine. Be that way!"
		if s.isupper():
			return "Woah, chill out!"
		if s[-1] == '?':
			return "Sure."
		return "Whatever."
