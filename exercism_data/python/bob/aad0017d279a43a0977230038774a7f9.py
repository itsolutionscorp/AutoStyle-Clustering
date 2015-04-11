def hey(input):
	input = input.strip();
	if len(input)==0:
		return 'Fine. Be that way!'
	if input.isupper():
		return 'Whoa, chill out!'
	if input[-1]=='?':
		return 'Sure.'
	if input[-1]=='!':
		if (input[:5] == "Let's") or (input[-2] == 'S'):
			return 'Whatever.'
		return 'Whoa, chill out!'
	return 'Whatever.'
