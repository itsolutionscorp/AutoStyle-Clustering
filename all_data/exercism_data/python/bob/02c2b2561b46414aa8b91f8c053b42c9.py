#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	clean 	= what.strip()
	end 	= clean[-1:]

	response = {
		'question' 	: 'Sure.',
		'shout' 	: 'Whoa, chill out!',
		'null' 		: 'Fine. Be that way!',
		'other' 	: 'Whatever.'
	}

	return response[check_phrase(clean)]

def check_phrase(clean):
	if clean.isupper() or clean.endswith("!"): return 'shout'
	elif clean.endswith('?'): return 'question'
	elif len(clean) == 0: return 'null'
	else: return 'other'
