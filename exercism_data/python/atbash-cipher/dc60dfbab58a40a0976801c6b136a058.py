from string import maketrans, translate, ascii_letters, ascii_lowercase, punctuation

atbash = maketrans(ascii_letters, ascii_lowercase[::-1] * 2)
size_of_bite = 5

def decode(coded):
	unformatted = coded.translate(atbash)
	
	decoding = ""
	while unformatted:
		decoding += unformatted[:size_of_bite]
		unformatted = unformatted[size_of_bite+1:]

	return decoding 

def encode(word):
	cipher = word.translate(atbash, " " + punctuation)

	encoded = ""
	while cipher:
		encoded += cipher[:size_of_bite]
		if len(cipher) > size_of_bite:
			encoded += " "
		cipher = cipher[size_of_bite:]

	return encoded 
