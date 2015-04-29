class Bob():
	def __init__(self):
		pass
	def hey(self, inpstring):
		inpstring = inpstring.strip()
		if len(inpstring) == 0:
			return "Fine. Be that way!"
		elif inpstring.isUpper():
			return "Woah, chill out!"
		elif inpstring.endswith('?'):
			return "Sure."
		elif inpstring.endswith('!'):
			return "Woah, chill out!"
		else:
			return "Whatever."
