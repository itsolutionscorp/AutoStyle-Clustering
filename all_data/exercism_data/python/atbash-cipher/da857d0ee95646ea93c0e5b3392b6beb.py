from string import ascii_lowercase, digits, maketrans, translate
from re import findall

def encode(string):
	string = ''.join(findall('[A-z0-9]',string.lower()))
	trans_table = maketrans(ascii_lowercase+digits, ''.join(reversed(ascii_lowercase))+digits)
	string = translate(string, trans_table)
	encoded = ''
	for i in xrange(len(string)):
		if i > 1 and i % 5 == 0:
			encoded += ' '
		encoded += string[i]
	return encoded
def decode(string):
	return encode(string).replace(' ','')
