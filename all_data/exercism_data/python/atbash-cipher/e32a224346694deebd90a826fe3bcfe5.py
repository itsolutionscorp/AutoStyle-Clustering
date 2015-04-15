import string

alphabet = string.ascii_lowercase
mapping = str.maketrans(alphabet, alphabet[::-1])

def decode(ciphertext):
	return ''.join(letter for letter in ciphertext.lower() 
	               if letter in alphabet or letter.isdigit()).translate(mapping)

def encode(plaintext):
	plaintext = [letter for letter in plaintext.lower() 
	             if letter in alphabet or letter.isdigit()]
	ciphertext = ''.join(letter if i % 5 else ' ' + letter 
	                     for i, letter in enumerate(plaintext)).translate(mapping)
	return ciphertext.strip()
