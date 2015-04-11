#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#Strip whitespace
	what=what.strip()
	if (what==''):
		return 'Fine. Be that way!'
	elif (any(c.isalpha() for c in what) and what.upper()==what):
		#if the string contains letters, and all letters are uppercase (i.e. applying .upper() doesn't change the string)
		return 'Whoa, chill out!'
	elif (what[-1]=='?'):
		#if asked a question, return sure.
	    return 'Sure.'
	else:
		return 'Whatever.'
