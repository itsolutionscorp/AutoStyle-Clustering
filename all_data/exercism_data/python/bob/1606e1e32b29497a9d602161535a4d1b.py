def hey(phrase): 
	"""Have a conversation with Bob.
    He answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without actually saying anything.
    He answers 'Whatever.' to anything else.
    """	
	
	phrase = phrase.strip()

	if not phrase:
		# is phrase blank?
		return 'Fine. Be that way!'
	elif phrase.isupper():
		# is phrase a yell?
		return 'Whoa, chill out!'
	elif phrase.endswith('?'):
		# is phrase a question?
		return 'Sure.'
	else:
		# Everything else
		return 'Whatever.'
