def hey(phrase): 
	"""Conducts a conversation with Bob.

	From README:
	Bob answers 'Sure.' if you ask him a question.

	He answers 'Whoa, chill out!' if you yell at him.

	He says 'Fine. Be that way!' if you address him without actually saying
	anything.

	He answers 'Whatever.' to anything else.
	"""
	
	phrase = phrase.strip()

	if isEmpty(phrase):
		return 'Fine. Be that way!'
	elif isYell(phrase):
		return 'Whoa, chill out!'
	elif isQuestion(phrase):
		return 'Sure.'
	else:
		return 'Whatever.'


def isQuestion(phrase):
	"""Checks to see if phrase is a question.

	Performs test by checking if last character in the phrase
	is a question mark.
	"""
	return phrase.endswith('?')

def isEmpty(phrase):
	""" Checks to see if phrase is blank.

	Performs test by using the fact that python treats an empty
	string as false.
	"""
	return not phrase

def isYell(phrase):
	"""Checks to see if phrase is a yell.

	Performs test by checking if phrase is all upper case.
	"""
	return phrase.isupper()
