#/usr/bin/env python

import re

def word_count(phrase):
	phrase = [w for w in re.split(r'[\s\W]+', phrase.lower()) if w]
	return {w:phrase.count(w) for w in phrase}
	
	# first create a list using re.split using a list comprehension
	# the raw string r'[\s\W]+' is used to define what to split on
	# \s is any white-space character, \W is any non=alphanumeric character
	# this filters the 'junk' out of my words while putting them in a list
	
	# the return statement uses a dictionary comprehension. It returns a dict
	
