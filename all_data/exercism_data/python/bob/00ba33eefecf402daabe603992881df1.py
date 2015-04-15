import re

def hey(saying):
	# Test for all whitespace or empty string
	m = re.search('^\s*$', saying)
	if m != None:
		return "Fine. Be that way!"

	# Test for screaming
	noAlpha = re.search('[A-Z]+', saying)
	if saying.upper() == saying and noAlpha != None:
		return "Whoa, chill out!"
		
	# Test for question
	if saying[len(saying)-1] == "?":
		return "Sure."

	# Whatever.
	return "Whatever."
