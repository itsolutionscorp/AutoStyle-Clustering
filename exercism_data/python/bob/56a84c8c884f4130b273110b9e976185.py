""" 
Jarek Wojciechowski
12/20/14
exercism - python 
1 - bob.py 
"""

def hey(what):
	"""Checks if string is either empty or only contains some form of whitespace
	(adressing without speaking)"""
	if len(what) == 0 or what.isspace():
		return "Fine. Be that way!"
	"""Checks if string is in upper case(Yelling)"""
	if what.isupper():
		return "Whoa, chill out!"
	"""Iterates through string backwards looking for a ?, stopping if a non-
	whitespace character is found(Question)"""
	for character in reversed(what):
		if character == '?':
			return "Sure."
		if character != " ":
			break
	"""Catch all response for any other input"""
	return "Whatever."
