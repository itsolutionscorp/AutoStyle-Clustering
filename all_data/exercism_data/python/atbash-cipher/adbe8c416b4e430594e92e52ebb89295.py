''' atbash_cipher.py
	created 6 Oct 2014
	by @jestuber
	Implement an Atbash cipher  '''
import string

REVERSE_ALPHA =  \
	string.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1]) #extended slice syntax)

def encode(plain):
	global REVERSE_ALPHA
	cipher = plain.lower().translate(REVERSE_ALPHA, string.punctuation + string.whitespace)
	return ' '.join(cipher[i:i+5] for i in xrange(0,len(cipher),5)) #space every 5 letters

def decode(cipher):
	global REVERSE_ALPHA
	assert all(char.islower() or char.isspace() for char in cipher)
	return cipher.translate(REVERSE_ALPHA, string.whitespace)
