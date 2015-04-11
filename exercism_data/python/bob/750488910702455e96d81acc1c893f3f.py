#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.strip()  == '' or not what:
		return 'Fine. Be that way!'

	elif what == what.upper() and what.lstrip('0123456789!?.,<> ') != '':
			return 'Whoa, chill out!'
	
	elif what.endswith('?'):
		return 'Sure.'
	
	return 'Whatever.'
