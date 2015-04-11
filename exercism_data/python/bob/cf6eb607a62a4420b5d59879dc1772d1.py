#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	clean 	= what.strip()
	end 	= clean[-1:]

	if clean.isupper(): end = '!'

	response = {
		'?' : 'Sure.',
		'!' : 'Whoa, chill out!',
		'' 	: 'Fine. Be that way!',
		'other' : 'Whatever.'
	}

	if end in response.keys():
		return response[end]
	else:
		return response['other']
