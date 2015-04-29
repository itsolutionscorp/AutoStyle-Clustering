def hey(text):
	
	if text:
		if text[-1] == '.':
			key = 'w'	
		elif (''.join(text.split(', '))).isdigit():
			key = 'w'
		elif (text[:-1]).isdigit():
			key = 's'
		elif text.isspace():
			key = 'f'	
		elif text.upper() == text:
			key = 'c'	
		elif text[-1] == '!':
			key = 'w'
		elif text[-1] == '?':
			key = 's'					
	else:
		key = 'f'


	mapping = { 'w' : 'Whatever.',
		's' : 'Sure.',
		'f' : 'Fine. Be that way!',
		'c' : 'Whoa, chill out!'}			

	return mapping[key]		
