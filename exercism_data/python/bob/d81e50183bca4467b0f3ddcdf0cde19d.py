def hey(string):
	if not string.strip():
		return 'Fine. Be that way!'
	if string.isupper():
		return 'Whoa, chill out!'
	if string[-1] == '?':
		return 'Sure.'
	return 'Whatever.'

print hey("It's OK if you don't want to go to the DMV.")