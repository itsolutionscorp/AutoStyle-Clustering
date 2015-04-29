# Skeleton file for the Python "Bob" exercise.


def hey(adult_nonsense):
	if adult_nonsense is None or adult_nonsense.strip() == '':
		return 'Fine. Be that way!'
	if adult_nonsense.isupper():
		return 'Whoa, chill out!'
	if adult_nonsense.strip().endswith('?'):
		return 'Sure.'
	return 'Whatever.'
