#
# Bob exercise that passes bob_test.py
#

whatever = 'Whatever.'
sure = 'Sure.'
chill = 'Whoa, chill out!'
fine = 'Fine. Be that way!'

def hey(what):
	if not what or what.isspace():
		return fine
	elif what.isupper():
		return chill
	elif what.endswith('?'):
		return sure
	return whatever
