#!/usr/bin/python
# Skeleton file for the Python "Bob" exercise.
#

import re
import unicodedata

def hey(what):

	what = what.strip()	
	ascii = unicodedata.normalize('NFD', what).encode('ASCII', 'ignore')
	
	# The following two lines remove all non-alphabet characters. I couldn't get them to work with just one line of code...	
	alpha = re.sub("[^\w]","",what)
	alpha = re.sub("[\d_]", "", alpha)

	if len(alpha) > 0 and ascii == ascii.upper():
		return "Whoa, chill out!"
	
	elif what.endswith("?"):
		return "Sure."
    	
	elif len(what) == 0:
		return "Fine. Be that way!"

	else:
		return "Whatever."
