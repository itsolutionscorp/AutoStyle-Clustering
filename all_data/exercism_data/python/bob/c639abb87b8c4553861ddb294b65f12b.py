#
# Bob's conversation responses.
#

def hey(what):
	if what.isupper():
		response = 'Whoa, chill out!'
	elif what[-1:] == '?':
		response = 'Sure.'
	elif len(what.strip()) == 0:
		response = 'Fine. Be that way!'
	else:
		response = 'Whatever.'
	return response
