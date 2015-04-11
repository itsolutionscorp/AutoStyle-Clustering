alpha = 'abcdefghijklmnopqrstuvwxyz'
numer = '1234567890'
ahpla = alpha[::-1]

def encode(plaintext):
	ret = ''.join(ahpla[alpha.index(x)] if x in alpha\
		else x if x in numer\
		else '' for x in plaintext.strip().lower())
	return ' '.join(ret[i:i+5] for i in range(0, len(ret), 5))

def decode(ciphertext):
	return ''.join(alpha[ahpla.index(x)] for x in\
		ciphertext.strip().lower().replace(' ',''))
