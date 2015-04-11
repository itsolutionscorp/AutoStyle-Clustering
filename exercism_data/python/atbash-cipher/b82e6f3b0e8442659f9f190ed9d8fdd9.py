import re

plain  = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'

def decode(ciphertext):
	ciphertext = re.sub(r'[^a-z0-9]', '', ciphertext.lower())
	return ciphertext.translate(str.maketrans(cipher, plain))

def encode(cleartext):
	cleartext = re.sub(r'[^a-z0-9]', '', cleartext.lower())
	return split5(cleartext.translate(str.maketrans(plain, cipher)))

def split5(line):
	return " ".join([line[i:i+5] for i in range(0, len(line), 5)])
