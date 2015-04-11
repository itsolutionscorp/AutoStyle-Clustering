def hey(input):
	input = input.strip();
	if not input:
		return 'Fine. Be that way!'
	if input.isupper():
		return 'Whoa, chill out!'
	if input[-1]=='?':
		return 'Sure.'
	return 'Whatever.'
