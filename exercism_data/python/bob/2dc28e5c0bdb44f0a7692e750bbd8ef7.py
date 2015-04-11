def hey(speech):

	if not speech.strip(): 				#Tests if the speech variable is empty and removes any white space
		return 'Fine. Be that way!'
	elif speech.isupper() == True: 		#Tests if the speech variable is uppercase
		return 'Whoa, chill out!'
	elif speech[-1] == '?':				#Tests if the speech variable is a question by checking whether the last character is a question mark
		return 'Sure.'
	else:								#If the speech varible passes none of these tests it returns 'Whatever'
		return 'Whatever.'
