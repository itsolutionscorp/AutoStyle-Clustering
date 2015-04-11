def hey(sentence):
	"""sentence said to bob"""

	sentence = sentence.strip()

	if sentence == '':
	  # it's nothing
	  return 'Fine. Be that way!'
	elif sentence.isupper():
	  # it's yelling 
	  return 'Woah, chill out!'
	elif sentence.endswith('?'):
	  # it's a question
	  return 'Sure.'
	else:
	  # anything else
	  return 'Whatever.'
