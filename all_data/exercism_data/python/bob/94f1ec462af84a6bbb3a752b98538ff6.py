#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# Check if string starts with spaces, or is blank
	if what.isspace() or what == '':
		return 'Fine. Be that way!'
	# Check if ALL CAPS
	elif what.isupper():
		return 'Whoa, chill out!'
	# Check if a question, ends with ?
	elif what.endswith('?'):
		return 'Sure.'
	# Else return 'Whatever.'
	else:
		return 'Whatever.'
