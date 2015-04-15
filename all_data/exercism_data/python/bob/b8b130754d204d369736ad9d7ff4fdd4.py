#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	response = 'Whatever.'
	noSpace = removeWhiteSpace(what)
	
	if noSpace == '' or (noSpace[0].isalpha() == False and noSpace[0].isnumeric() == False):
		response = 'Fine. Be that way!'
	elif yellCheck(what) == True:
		response = 'Whoa, chill out!'	
	elif questionCheck(noSpace) == True:
		response = 'Sure.'
	return response

def removeWhiteSpace(sentence):
	newSentence = ''		
	for s in sentence:
		if s != ' ':
			newSentence += s	
	return newSentence
	
def yellCheck(sentence):
	answer = ''
	for s in sentence:
		if s.isalpha() == True:
			answer += s		
	if answer == '':
		return False		
	return answer.upper() == answer
	
def questionCheck(question):	
	if question[-1] == '?':
		return True
	return False
