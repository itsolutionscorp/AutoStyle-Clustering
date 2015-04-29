#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	
	if(check_silence(what)):
		return "Fine. Be that way!"
	
	if(check_yell(what)):
		return "Whoa, chill out!"

	if(check_question(what)):
		return "Sure."
	
	return "Whatever." 

def check_question(what):
	return "?" ==  what[-1]

def check_yell(what):
	return what.isupper()
	
def check_silence(what):
	if( len(what.strip(' \t\n\r'))==0):
		return True
	
