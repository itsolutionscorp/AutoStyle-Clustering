from string import maketrans, translate
import string

SIZE_OF_BITE = 5

UPPERCASE = string.ascii_uppercase
LOWERCASE = string.ascii_lowercase
PUNCTUATION = string.punctuation

def decode(coded):
	atbash = maketrans(LOWERCASE[::-1] * 2, UPPERCASE + LOWERCASE)
	unformatted = coded.translate(atbash)
	
	decoding = ""
	while unformatted:
		decoding += unformatted[:SIZE_OF_BITE]
		unformatted = unformatted[SIZE_OF_BITE+1:]

	return decoding 

def encode(word):
	word = word.translate(None, " " + PUNCTUATION)
	
	atbash = maketrans(UPPERCASE + LOWERCASE, LOWERCASE[::-1] * 2)
	cipher = word.translate(atbash)

	encoded = ""
	while cipher:
		encoded += cipher[:SIZE_OF_BITE]
		if len(cipher) > SIZE_OF_BITE:
			encoded += " "
		cipher = cipher[SIZE_OF_BITE:]

	return encoded 
