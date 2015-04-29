#
# Skeleton file for the Python "Bob" exercise.
#


def remove_white(str):
	return ''.join([char for char in str if char != ' '])

def is_nothing(str):
	return not str or str == '\t'
	
def is_shout_NRE(str):
	any_upper = False
	any_lower = False
	for char in str:
		if char.isupper():
			any_upper = True
		if char.islower():
			any_lower = True
	return any_upper and not any_lower

def is_question(str):
	return str[-1] == '?'

def hey(what):
	what = remove_white(what)
	if is_nothing(what):
		return u'Fine. Be that way!'
	elif is_shout_NRE(what):
		return u'Whoa, chill out!'
	elif is_question(what):
		return u'Sure.'
	else:
		return u'Whatever.'
