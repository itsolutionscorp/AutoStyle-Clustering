from re import findall

def hey(text):
	has_alpha_content = len(findall(r'[a-z]|[A-z]|[0-9]', text)) > 0  
	has_no_content = len(text) < 1 or not has_alpha_content
	is_yelling = text.endswith('!') or text.isupper()
	is_question = text.endswith('?')

	if (has_no_content):
		response = 'Fine. Be that way!'
	elif(is_yelling):
		response = 'Woah, chill out!'
	elif(is_question):
		response = 'Sure.'
	else:
		response = 'Whatever.'

	return response
