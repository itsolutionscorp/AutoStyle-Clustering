#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.strip() == '':
		return 'Fine. Be that way!'
	elif what.upper() == what:
		for x in what:
			if x.isalpha():
				return 'Whoa, chill out!'

		return 'Whatever.'
	elif what.strip()[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
