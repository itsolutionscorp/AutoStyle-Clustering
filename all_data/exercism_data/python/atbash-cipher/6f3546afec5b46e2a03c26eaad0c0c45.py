''' atbash_cipher.py
	created 6 Oct 2014
	by @jestuber
	Implement an Atbash cipher  '''
import string

def encode(plain):
	encoder_dict = dict(zip(string.ascii_lowercase, string.ascii_lowercase[::-1])) #extended slice syntax
	plain = plain.lower().strip().replace(' ','')
	plain = plain.translate(None, string.punctuation)
	cipher = ''
	for c in plain:
		if c in string.digits: #let numbers go through
			cipher = ''.join([cipher,c])
		else:
			cipher = ''.join([cipher,encoder_dict[c]])
	return ' '.join(cipher[i:i+5] for i in xrange(0,len(cipher),5)) #space every 5 letters

def decode(cipher):
	decoder_dict = dict(zip(string.ascii_lowercase[::-1],string.ascii_lowercase))
	cipher = cipher.replace(' ','')
	plain = ''
	for c in cipher:
		if c in string.digits:
			plain = ''.join([plain,c])
		else:
			plain = ''.join([plain,decoder_dict[c]])
	return plain
