import re

alphabet = list('abcdefghijklmnopqrstuvwxyz')
alphabet_rev = alphabet[::-1]

def helper(fun, text):
	text = re.sub(r'[ .,]', '', text.lower())
	result = ''

	for letter in text:
		if letter in alphabet:
			result += fun(letter)
		else: result += letter
	return result

def decode(text):
	return helper(lambda letter: alphabet_rev[alphabet.index(letter)], text)

def encode(text):
	result = helper(lambda letter: alphabet[alphabet_rev.index(letter)], text) 
	return ' '.join( [ result[i:i+5] for i in range(0, len(result), 5) ] )
