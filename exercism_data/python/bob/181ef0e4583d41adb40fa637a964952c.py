#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()			# Strip input string of leading and trailing whitespace
    
    if not what: 			# return true if what is empty
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
	return 'Sure.' 
    return 'Whatever.'
