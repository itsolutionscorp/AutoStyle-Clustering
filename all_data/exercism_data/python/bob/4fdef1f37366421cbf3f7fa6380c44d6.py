class Bob(object):

	def hey(self, something):
		if something is None or not something.strip():
			return "Fine. Be that way!"
		elif something == something.upper():
			return "Woah, chill out!"
		elif something.strip()[-1] == '?':
			return "Sure."
		else:
			return "Whatever."
