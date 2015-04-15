#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
	
	if (what.isspace() or what == ''):
		return "Fine. Be that way!"
	
	what = what.rstrip()
	
	if what.isupper():
		return "Whoa, chill out!"
		
	if (what[-1] == '?'):
		return "Sure."
	
	#what.translate(string.punctuation)
	
	# Everything else
	return "Whatever."
