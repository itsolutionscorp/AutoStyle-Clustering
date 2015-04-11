#
# Skeleton file for the Python "Bob" exercise.
# v. 2
# Submitted by j00ce // 9 October 2014

import string

def hey(what):
	what = what.strip()
	if not what: #all whitespace characters say nothing
		return 'Fine. Be that way!'
	elif what.isupper(): #ALL UPPERCASE is YELLING
		return 'Whoa, chill out!'
	elif what[-1] == "?": #not ALL UPPERCASE and ending in "?" is a question
		return 'Sure.'
	return 'Whatever.' #everything else, including ending in "!" but not ALL UPPERCASE != YELLING
