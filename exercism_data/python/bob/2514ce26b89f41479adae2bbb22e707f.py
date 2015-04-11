#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isspace():
		return "Fine. Be that way!"
	elif what=='':
		return "Fine. Be that way!"
	what=what.lstrip()
	what=what.rstrip()

	if what.isupper():
		return "Whoa, chill out!"

	elif what.endswith("?")==True:
		return "Sure."

	else:
		return "Whatever."
