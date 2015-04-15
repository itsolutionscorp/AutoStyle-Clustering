#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# Strip whitespace
	what = what.strip()

	# Bob says 'Fine. Be that way!' if you address him without actually saying anything.
	if what == '':
		return 'Fine. Be that way!'

	# Bob answers 'Whoa, chill out!' if you yell at him.
	if what.isupper():
		return 'Whoa, chill out!'

	# Bob answers 'Sure.' if you ask him a question.
	if what.endswith('?'):
		return 'Sure.'

	# Bob answers 'Whatever.' to anything else.
	return 'Whatever.'
