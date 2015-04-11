#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	response = ''

	if what.strip().isupper():
		response = 'Whoa, chill out!'
	elif not what.strip():
		response = 'Fine. Be that way!'
	elif what.strip()[-1] is '?':
		response = 'Sure.'
	else:
		response = 'Whatever.'

	return response
