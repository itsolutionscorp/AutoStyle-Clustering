#
# The Python "Bob" exercise.
# Gary Smith 5 Nov 2014
#
def hey(what):
	# Strip any trailing whitespace (may result in empty string)
	what = what.rstrip()

	# Test whether anything was said (Rule: empty string)
	if len(what) == 0:
		return "Fine. Be that way!"
	# Test for shouting (Rule: all letters, of which there must be at least one, are capitalised)
	# (This test overrules later test, thus a shouted question is regarded as shouting)
	elif what == what.upper() and any(c.isalpha() for c in what):
		return "Whoa, chill out!"
	# Test for question (Rule: ends with ?)
	elif what.endswith('?'):
		return "Sure."
	# Drop through for any other statement
	else:
		return "Whatever."
