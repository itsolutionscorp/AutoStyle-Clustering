class Bob(object):
	def hey(self, s):
		# Address without saying anything.
		if not s or s.isspace():
			return "Fine. Be that way!"
		# Yelling.
		if s.isupper():
			return "Woah, chill out!"
		# Question.
		if s[-1] == '?':
			return "Sure."
		# Everything else.
		return "Whatever."
