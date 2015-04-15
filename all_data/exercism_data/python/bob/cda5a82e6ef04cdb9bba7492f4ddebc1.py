"""Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else."""

def hey(usrInput): #categorize user's input into question, yelling, nothing or neither of them all
	length = len(usrInput)
	if length != 0:
		lastchar = usrInput[length - 1]			#the last character of user's input

	if testSilence(usrInput):
		ans = "Fine. Be that way!" 		#the user didn't respond
	elif testYell(usrInput):
		ans = "Whoa, chill out!" 		#the user prompts a question
	elif lastchar == "?":
		ans = "Sure."				#the user yells at Bob
	else : 
		ans = "Whatever."			#it's neither of all of the above
	return ans

def testSilence(usrInput):			
#function to test if user is silent
#return value of 0 is not silent and 1 is silent
#user is silent if there's no alpha numeric character
	u = 0
	test = 1
	while u < len(usrInput)-1:
		if usrInput[u].isalnum():
			test = 0
			break
		u = u + 1
	return test

def testYell(usrInput):
#function to test if user is yelling
#return value 1 if yelling and 0 if not
#user yells if all characters are upper case
	if not testAllNum(usrInput) :
		if usrInput.upper() == usrInput:
			test = 1
		else :
			test = 0
	else:
		test = 0
	return test

def testAllNum(usrInput):
	u = 0
	test = 1
	while u < len(usrInput) - 1:
		if usrInput[u].isalpha():
			test = 0
			break
		u = u + 1
	return test
