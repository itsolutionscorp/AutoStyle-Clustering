#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if what == what.upper() and any(c.isalpha() for c in what):
		return "Whoa, chill out!"

	elif what.endswith('?'):
		return "Sure."

	elif not what or '\t' in what:
		return "Fine. Be that way!"

	else:
		return "Whatever."

    
#    tell_bob = raw_input()

# hey(tell_bob)
