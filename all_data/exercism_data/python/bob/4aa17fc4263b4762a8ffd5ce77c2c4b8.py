#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()			# Strip input string of leading and trailing whitespace
    if len(what) < 1: 
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
	return 'Sure.' 
    else:
        return 'Whatever.'
    return
