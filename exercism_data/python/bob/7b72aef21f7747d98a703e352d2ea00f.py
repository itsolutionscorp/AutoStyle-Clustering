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

	if not phrase:					# Checks to see if phrase is blank.
		return 'Fine. Be that way!'
	elif phrase.isupper():			# Checks to see if phrase is a yell.
		return 'Whoa, chill out!'
	elif phrase.endswith('?'):		# Checks to see if phrase is a question.
		return 'Sure.'
	else:
		return 'Whatever.'
