#!/usr/bin/python
import re

def hey(mesg):
	#Test for an upper case string without lower case
	if mesg.upper() == mesg and re.search(r'[A-Z]', mesg) is not None:
		return 'Whoa, chill out!'

	#Test if string is a question
	if re.search(r'\?$', mesg) is not None:
		return 'Sure.'

	#Test if string is only whitespace
	if re.search(r'^\s*$', mesg) is not None:
		return 'Fine. Be that way!'

	#Default return value
	return 'Whatever.'
