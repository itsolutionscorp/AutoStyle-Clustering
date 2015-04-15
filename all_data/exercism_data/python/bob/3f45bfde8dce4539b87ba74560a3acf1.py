#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	string = what.strip()

	if string=='':
		return 'Fine. Be that way!'

	elif string.isupper()==True:
		return 'Whoa, chill out!'

	elif string[-1]=='?':
		return 'Sure.'

	else:
		return 'Whatever.'
