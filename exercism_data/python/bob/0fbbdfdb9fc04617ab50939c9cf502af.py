#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	message = what.strip()

	if is_only_digits(message) and not message.endswith('?'):
		return 'Whatever.'
	elif message.lower() == 'bob' or message is '':
		return 'Fine. Be that way!'
	elif message.upper() == message and not is_only_digits(message):
		return 'Whoa, chill out!'
	elif message.endswith('?'):
		return 'Sure.' 
	else:
		return 'Whatever.'

def remove_punc_and_spaces(input_string):
	import string
	excluded = set(string.punctuation)
	excluded.add(' ')
	return ''.join([c for c in input_string if c not in excluded]) 

def is_only_digits(input_string):
	return remove_punc_and_spaces(input_string).isdigit()
