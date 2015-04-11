# bob module


def hey(string):

	if len(string) == 0:
		return 'Fine. Be that way!'

	elif string.isupper() == True:
		return 'Whoa, chill out!'

	elif string[-1] == "?":
		return 'Sure.'

	elif string.isspace() == True:
		return 'Fine. Be that way!'

	else:
		return 'Whatever.'		
