def hey(phrase):
	"""
	Bob simulates a teenager, hey() is the function through which he responds
	"""

	if any(c.isalpha() or c.isdigit() for c in phrase):
		if phrase.isupper():
			return "Whoa, chill out!"
		elif phrase[-1] == '?':
			return "Sure."	
		else:
			return "Whatever."
	else:
		return "Fine. Be that way!"
