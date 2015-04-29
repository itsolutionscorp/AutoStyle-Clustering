#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.upper() == what and any(c.isalpha() for c in what):
		return "Whoa, chill out!"
	if what != '' and what[-1] == '?':
		return "Sure."	
	if len(what) < 7:
		return "Fine. Be that way!"
	else:
		return "Whatever."
