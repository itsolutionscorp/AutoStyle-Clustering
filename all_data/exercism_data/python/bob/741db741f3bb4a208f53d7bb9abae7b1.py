#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	response = ''
	if what.strip():
		if what.isupper():
			# yell => Whoa, chill out
			response = 'Whoa, chill out!'
		elif what.endswith('?'):
			# question => Sure
			response = 'Sure.'
		else:
			# everything else => Whatever.
			response = 'Whatever.'
	else:
		# nothing => Fine. Be that way!
		response = 'Fine. Be that way!'

	return response
