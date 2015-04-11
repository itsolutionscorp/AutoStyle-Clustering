#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.upper()==what and what.swapcase() != what:
		return "Whoa, chill out!"
	elif what.endswith('?'):
		return "Sure."
	elif what.strip()=="":
		return "Fine. Be that way!"
	else:
		return "Whatever."
