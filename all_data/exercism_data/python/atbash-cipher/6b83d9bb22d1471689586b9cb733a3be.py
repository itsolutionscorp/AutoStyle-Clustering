import string

def decode(enc_phrase):
	phrase = enc_phrase.replace(' ', '')
	decoded = ''
	for i in range(len(phrase)):
		letter = phrase[i]

		int_letter = ord(letter)
		ord_a = ord('a')
		ord_z = ord('z')
		#Cipher algorithm
		if int_letter >= ord_a and int_letter <= ord_z:
			decoded += chr(ord_z - (int_letter - ord_a))
		else:
			decoded += letter

	return decoded

def encode(phrase):
	punc = string.punctuation
	stripped_phrase = ''.join([o for o in list(phrase) if not o in punc]).lower().replace(' ', '')
	encoded = ''

	for i in range(len(stripped_phrase)):
		letter = stripped_phrase[i]
		if i != 0 and (i)%5 == 0:
			encoded += ' '

		int_letter = ord(letter)
		ord_a = ord('a')
		ord_z = ord('z')
		#Cipher algorithm
		if int_letter >= ord_a and int_letter <= ord_z:
			encoded += chr(ord_z - (int_letter - ord_a))
		else:
			encoded += letter

	return encoded

print decode('vcvix rhn')
