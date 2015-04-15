#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):

	what = what.strip()


	if (len(what) > 0):
		
		Q = ( what[len(what)-1] == '?' ) # Check '?' 
		YELL = ( what == what.upper() ) # Is? YELL




		if Q & YELL:
			return 'Whoa, chill out!'
		elif Q:
			return 'Sure.'
		elif YELL:
			return 'Whoa, chill out!'
		##elif 
		##	return 'Fine. Be that way!'
		else:
			return 'Whatever.'
