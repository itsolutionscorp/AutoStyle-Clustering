#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	
	if not what:
		x = 'Fine. Be that way!'
		return x
	if what.isupper():
		x = 'Whoa, chill out!'
		return x
	if what[-1] == '?':
		x = 'Sure.'
		return x
	else :
		x = 'Whatever.'
    	return x
