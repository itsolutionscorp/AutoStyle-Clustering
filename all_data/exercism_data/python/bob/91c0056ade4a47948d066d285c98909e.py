#
# Skeleton file for the Python "Bob" exercise.
#

def is_empty(t):
	return not t

def is_shouting(t):
	return t.isupper()

def is_question(t):
	return t.endswith('?')

def hey(what):

	what = what.strip()
	if is_empty(what):
		return 'Fine. Be that way!'

	if is_shouting(what):
		return 'Whoa, chill out!'

	if is_question(what):
		return 'Sure.'

 	return 'Whatever.'
