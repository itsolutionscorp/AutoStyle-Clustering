def hey(input):
	condensed = ''.join(input.split())
	if condensed == '':
		return 'Fine. Be that way!'
	if condensed.isupper():
		return 'Woah, chill out!'
	if condensed[-1] == '?':
		return 'Sure.'
	return 'Whatever.'

		
