#
# Skeleton file for the Python "Bob" exercise.
#

#import re

def remove_white(str):
	""" Removes white space from the string """
	#TO-DO: try RegEx
	
	new = ''
	return new.join([char for char in str if char != ' '])

def is_nothing(str):
	"""Assumes no more white spaces"""
	# TO-DO: do a better job with "\t" and similar, maybe RegEx 
	return not str or str == '\t' 

#def is_shout(str):
#	""" Checks for any upper-case and no lower-cases """
#	# TO-DO: include non-ascii characters
#	return re.findall('[A-Z]', str) and not re.findall('[a-z]', str)
	
def is_shout_NRE(str):
	""" No RegEx version: 2x faster and no problems with non-ascii."""
	"""	Assumes str is not nothing and no more white spaces """	
	any_upper = False
	any_lower = False
	for char in str:
		if char.isupper():
			any_upper = True
		if char.islower():
			any_lower = True
	return any_upper and not any_lower

def is_question(str):
	""" Assumes its not nothing, not a shout and str has no more white spaces"""
	return str[-1] == '?'

def hey(what):
	what = remove_white(what)
	if is_nothing(what):
		return u'Fine. Be that way!'
	elif is_shout_NRE(what):
		return u'Whoa, chill out!'
	elif is_question(what):
		return u'Sure.'
	else:
		return u'Whatever.'
