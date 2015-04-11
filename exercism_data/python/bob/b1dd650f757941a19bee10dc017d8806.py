#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	response = what.strip()
	if response.isupper():
		return 'Whoa, chill out!'
	if response.endswith('?'):
		return 'Sure.'	
	if not response:
		return 'Fine. Be that way!'
	return 'Whatever.'
