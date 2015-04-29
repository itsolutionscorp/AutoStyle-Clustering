#
# Skeleton file for the Python "Bob" exercise.
#
import string
def hey(what):
	what = what.strip()
	if not what:
		return 'Fine. Be that way!'
	elif any(w for w in what if w in string.letters) and what == what.upper():
		return 'Whoa, chill out!'	
	elif what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
