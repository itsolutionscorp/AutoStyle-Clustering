#
# Skeleton file for the Python "Bob" exercise.
#


def hey(w):
	w = w.strip()
	if is_empty(w):
		return 'Fine. Be that way!'
	elif is_yelling(w):
		return 'Whoa, chill out!'
	elif is_question(w):
		return 'Sure.'
	return 'Whatever.'

'''
Boolean tests so inputs can be cateogirzed

'''

def is_empty(w):
	# Is empty if there is nothing to say after all whitespace is stripped away
	return not w.strip()

def is_yelling(w):
	# Is yelling if there are non-numerical chars in all caps.
	return w.isupper()

def is_question(w):
	# Is a question if the question mark is at the end
	return w.endswith('?')
