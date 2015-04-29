# Shouting is defined to be an entirely
# uppercase string
def shouting(what):
	return what.isupper()

# A question is defined to be a string
# that ends with a question mark
def questioning(what):
	return what.endswith('?')

# Saying nothing is defined to be a string
# containing no alphanumeric characters
def nothing(what):
	return not what.strip()

def hey(what):

	if nothing(what):
		return 'Fine. Be that way!'

	if shouting(what):
		return 'Whoa, chill out!'

	if questioning(what):
		return 'Sure.'
	
	return 'Whatever.'
