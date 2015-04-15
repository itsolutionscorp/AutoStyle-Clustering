#
# Skeleton file for the Python "Bob" exercise.
#
import string
def hey(what):
	truthtest = [(' ' + ' \t' + '\r').find(x) >= 0 for x in what]
	
	if what.isupper():
		return "Whoa, chill out!"
	elif len(what) != 0 and what[-1] == '?':
		return 'Sure.'
	elif False not in truthtest:
		return 'Fine. Be that way!'
	else:
		return "Whatever."
