import re

def hey(said):
	#check if upper case
	if said.isupper():
		return 'Whoa, chill out!'
	# check if the last character is a question mark
	elif len(said) > 0 and said[len(said)-1] == '?':
		return 'Sure.'
	# check it the string in not empty of chars or numbers
	elif re.search('[a-zA-Z0-9]', said) is None:
		return 'Fine. Be that way!'
	# check if string is not empty
	elif(len(said)>0):
		return 'Whatever.'