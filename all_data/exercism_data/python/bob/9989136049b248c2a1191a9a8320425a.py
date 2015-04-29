#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper() and what.swapcase() != what:
		return "Whoa, chill out!"
	elif what.endswith('?'):
		return "Sure."
	elif not what.strip():
		return "Fine. Be that way!"
	else:
		return "Whatever."
