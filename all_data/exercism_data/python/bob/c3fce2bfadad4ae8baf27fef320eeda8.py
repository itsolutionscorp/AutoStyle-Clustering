#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	trimmed       = what.rstrip()
	
	is_empty      = len(trimmed) == 0
	has_alpha     = contains_alpha(trimmed)
	all_uppercase = (trimmed == trimmed.upper())
	is_yelling    = has_alpha and all_uppercase
	is_a_question = trimmed.endswith('?')

	if is_yelling:
		return 'Whoa, chill out!'
	
	if is_a_question:
		return 'Sure.'
	
	if is_empty:
		return "Fine. Be that way!"
	
	return 'Whatever.'

def contains_alpha(str):
	return [s for s in str if s.isalpha()] != []
