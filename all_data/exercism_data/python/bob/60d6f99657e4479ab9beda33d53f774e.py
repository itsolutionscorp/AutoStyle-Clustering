#test
def hey(string):
	if string.isupper():
		return 'Whoa, chill out!'
	elif len(string) > 0 and string[-1] == '?':
		return 'Sure.'		
	elif len(string.strip()) == 0:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
