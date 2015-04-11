# bob module prototype
	
responses = {'Question':'Sure.',
			 'Yelling':'Whoa, chill out!',
			 'Silence':'Fine. Be that way!',
			 'Other':'Whatever.'}
				
def hey(comment):
	
	if comment.isspace() or not comment:
		return responses['Silence']
	
	elif comment.isupper():
			return responses['Yelling']
		
	elif comment[-1] == '?':
		return responses['Question']
		
	else:
		return responses['Other']
	
