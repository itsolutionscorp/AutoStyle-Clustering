#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip('"')

	if what[-2:] == '!' or what.isupper(): 
		return 'Whoa, chill out!'
	elif what[-1:] == '?':
		return 'Sure.'
	elif what.strip() == '':
		return 'Fine. Be that way!'
	else:
  		return 'Whatever.'
