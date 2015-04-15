# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
	what = what.strip()
	
	if what.isupper(): 
		return 'Whoa, chill out!'
	elif len(what)!=0 and what[-1] == '?':
		return 'Sure.'
	elif len(what)==0:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
    
