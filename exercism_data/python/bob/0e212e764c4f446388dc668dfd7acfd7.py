#
# Skeleton file for the Python "Bob" exercise.
#

# py2.7

def hey(what):
	if isinstance(what, basestring):
		what = what.strip()
	else:
		return 'I\'m sorry. I\'m not a string.'
	if not what:
		return 'Fine. Be that way!'
	elif what.endswith('?'):
		return 'Sure.'
	elif what.endswith('!'):
		return 'Whoa, chill out!'
	else:
		return 'Whatever.'
    return 'I\'m sorry. I seem to be broken.'
