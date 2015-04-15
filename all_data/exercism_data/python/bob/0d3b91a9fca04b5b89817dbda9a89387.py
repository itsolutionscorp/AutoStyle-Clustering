class Bob(object):
	def hey(self, s):
		# empty.
		if not s or s.isspace():
			return "Fine. Be that way!"
		# yelling.
		if s.isupper():
			return "Woah, chill out!"
		# question.
		if s[-1] == '?':
			return "Sure."
		return "Whatever."
