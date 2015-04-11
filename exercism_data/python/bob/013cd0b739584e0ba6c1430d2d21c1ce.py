#Attempt 2. I saw some good code in another sample and updated mine.
def hey(x):
	
	if len(x)==0 or x.isspace()==True:
		return "Fine. Be that way!"
	elif x.isupper()==True:
		return "Whoa, chill out!"
	elif len(x)>1 and x[-1]=='?':
		return "Sure."

	

	
	else:
		return 'Whatever.'
		
