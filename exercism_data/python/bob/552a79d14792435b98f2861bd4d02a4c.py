from __future__ import unicode_literals

def hey(inputvar):
	if inputvar.isupper():
		return 'Whoa, chill out!'
	elif inputvar.isspace() or inputvar=='':
		return 'Fine. Be that way!'
	elif inputvar[len(inputvar)-1]=='?':
		return 'Sure.'
	else:
		return 'Whatever.'
