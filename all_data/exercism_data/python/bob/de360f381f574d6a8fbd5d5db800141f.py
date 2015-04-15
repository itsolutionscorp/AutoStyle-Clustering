#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	response = {
		'standard': 'Whatever.',
		'question': 'Sure.',
		'shout'	  : 'Whoa, chill out!',
		'nothing' : 'Fine. Be that way!'
	}
	
	what = what.strip()
	type = 'standard'

	if what == '':
		type = 'nothing'
		return response[type]

	if what[-1] == '?':
		type = 'question'

	if what == what.upper() and what.upper() != what.lower():
		type = 'shout'

	return response[type]
