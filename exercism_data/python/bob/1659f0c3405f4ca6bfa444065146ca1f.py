class Bob():
	def __init__(self):
		pass
	def hey(self, inpstring):
		inpstring = inpstring.strip()
		if len(inpstring) == 0:
			return "Fine. Be that way!"
		elif inpstring.endswith('?'):
			if inpstring.upper() == inpstring and any(c.isalpha() for c in inpstring):
				return "Woah, chill out!"
			return "Sure."
		elif inpstring.endswith('!'):
			if inpstring.upper() == inpstring and any(c.isalpha() for c in inpstring):
				return "Woah, chill out!"
			else:
				return "Whatever."
		if inpstring.upper() == inpstring and any(c.isalpha() for c in inpstring):
			return "Woah, chill out!"
		else:
			return "Whatever."
