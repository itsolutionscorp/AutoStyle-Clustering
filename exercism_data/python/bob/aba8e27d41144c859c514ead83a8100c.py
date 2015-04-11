#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	whatever = 'Whatever.'
	chillout = 'Whoa, chill out!'
	sure = 'Sure.'
	fine = 'Fine. Be that way!'

	message = what.strip()

	if message.isupper():
		response = chillout
	elif re.match(r'[0-9][?]$', message) or message[-1:] == '?':
		response = sure
	elif len(message) == 0:
		response = fine
	else:
		response = whatever
		
	return response
