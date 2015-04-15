import re

def hey(said):
	# if(any(x.isupper() for x in said[1:]) and '?' not in said):
	if said.isupper():
		return 'Whoa, chill out!'
	elif len(said) > 0 and said[len(said)-1] == '?':
		return 'Sure.'
	elif re.search('[a-zA-Z0-9]', said) is None:
		return 'Fine. Be that way!'
	elif(len(said)>0):
		return 'Whatever.'
