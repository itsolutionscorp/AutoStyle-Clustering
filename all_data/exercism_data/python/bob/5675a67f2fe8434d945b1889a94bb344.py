import unicodedata

def hey(string):
	string = unicodedata.normalize('NFKD', string).encode('ASCII', 'ignore')
	cleaned_string = ''.join(char for char in string if char.isalnum())
	if cleaned_string == '':
		return 'Fine. Be that way!'
	else:
		if cleaned_string.isupper():
			return 'Woah, chill out!'
		else:
			if string[-1] == '?':
				return 'Sure.'
			else:
				return 'Whatever.'
