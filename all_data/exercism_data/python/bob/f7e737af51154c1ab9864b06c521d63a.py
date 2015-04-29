def hey(say):
	#Nothing to say to Bob
	if not say.strip():
		return 'Fine. Be that way!'
	#Yelling at Bob
	if say.isupper():
		return 'Whoa, chill out!'
	#Asking Bob a question
	if say[-1:] == '?':
		return 'Sure.'
	#Everything else
	return 'Whatever.'
