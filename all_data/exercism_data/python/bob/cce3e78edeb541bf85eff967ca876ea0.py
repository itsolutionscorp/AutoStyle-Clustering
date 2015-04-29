
answers = {'question': 'Sure.',
		   'yell': 'Whoa, chill out!',
		   'address': 'Fine. Be that way!',
		   'other': 'Whatever.'}
			   
		
def hey(dialog):
	# test for each condition
	
	chars = False
	for c in dialog:
		if c.isalpha():
			chars = True
			if c == c.lower():
				break # not a yell
	else: # a yell unless there were no chars
		if chars:
			return answers['yell']
	
	# see if a question by checking end punctuation
	if (len(dialog) > 0) and (dialog[-1].strip() == '?'):
		return answers['question']
	
	# see if addressed
	if dialog.strip() == '':
		return answers['address']
		
	else:
		return answers['other']
			
	
