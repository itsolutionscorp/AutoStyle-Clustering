#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
	what = what.rstrip()

	if what:
		last = what[-1]

		if what.isupper():
			return 'Whoa, chill out!'

		elif last == '!':
			return 'Whatever.'

		if last == '?':
			return 'Sure.'

		return 'Whatever.'

	else:
		return 'Fine. Be that way!'
