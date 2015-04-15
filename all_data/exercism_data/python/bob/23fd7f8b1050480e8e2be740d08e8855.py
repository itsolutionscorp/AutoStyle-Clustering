def hey(input):
	if input.isupper():
		return 'Woah, chill out!'
	if input[-1:] == '?':
		return 'Sure.'
	if input.isspace() or not input:
		return 'Fine. Be that way!'
	return 'Whatever.'
