def hey(texto):
	"""say to bob"""
	texto = texto.strip()
	if texto == '':
	  return 'Fine. Be that way!'
	elif texto.isupper():
	  return 'Woah, chill out!'
	elif texto.endswith('?'):
	  return 'Sure.'
	else:
	  return 'Whatever.'
