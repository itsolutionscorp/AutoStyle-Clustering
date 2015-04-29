def hey(strng):
	x = strng
	if x == '' or x.isspace() == True:
		return 'Fine. Be that way!'
	elif x.isupper():
		return 'Woah, chill out!'
	elif x[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
