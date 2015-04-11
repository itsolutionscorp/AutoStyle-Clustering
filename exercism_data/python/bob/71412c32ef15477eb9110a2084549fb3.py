#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what) :
	
	# Bob is met with Silence.
	if what.strip() == '' :
		return 'Fine. Be that way!'

	# Bob is posed a question.
	elif what[-1] == '?' :

		# The question is a series of numbers.
		if what[0].isnumeric() :
			return 'Sure.'

		# The question is YELLED at Bob.
		elif what.upper() == what :
			return 'Whoa, chill out!'
		
		else :
			return 'Sure.'

	# Bob is being yelled at.
	elif what.upper() == what :

		# It's just someone yelling numbers.
		if what[-1].isnumeric() :
			return 'Whatever.'

		# Bob is really being yelled at.
		return 'Whoa, chill out!'

	# Bob is not interested.
	else :
		return 'Whatever.'
