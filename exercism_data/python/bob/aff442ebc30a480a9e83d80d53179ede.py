#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if what == "":
		return "Fine. Be that way!"
	elif what[-1] == "?" and what.isupper():
		return "Whoa, chill out!"
	elif what[-1] == "?":
		return "Sure."
	elif what[-1] == "!" and what.isupper():
		return "Whoa, chill out!"
	elif what.isupper():
		return "Whoa, chill out!"

	return "Whatever."
