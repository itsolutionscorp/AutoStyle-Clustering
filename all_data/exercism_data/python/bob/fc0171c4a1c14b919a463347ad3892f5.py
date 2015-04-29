#
# Skeleton file for the Python "Bob" exercise.
#

#input user's statment ('what')
#test to see what type it is (shouting, numbers, etc)
#respond with appropriate response
def hey(what):
	if not what.strip():
		return "Fine. Be that way!"
	elif what.isupper():
	    return "Whoa, chill out!"
	elif what.strip().endswith('?'):
		return "Sure."
	else:
		return "Whatever."
