#
# Skeleton file for the Python "Bob" exercise.
#
def is_nothing(what): 
	return what.strip() == ''

def is_question(what):
	return what[-1:] == '?'
	
def is_yelling(what):
	return what.isupper()

def hey(what): 
	if is_nothing(what):
		return 'Fine. Be that way!'
	elif is_yelling(what):
		return 'Whoa, chill out!'
	elif is_question(what):
		return 'Sure.'
	else:
		return 'Whatever.'
